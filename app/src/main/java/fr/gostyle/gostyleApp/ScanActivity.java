package fr.gostyle.gostyleApp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.Result;

import fr.gostyle.gostyleApp.models.Promotion;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    FirebaseFirestore mPromotions = FirebaseFirestore.getInstance();
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Initialisation de la vue de scan
        setContentView(mScannerView);                // On ajoute la vue au fichier xml

        //Demander la permission pour utiliser la caméra si cela n'était pas donné

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, "La permission est nécessaire pour scanner le qrcode", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // On écoute les résultats provenant des scans
        mScannerView.startCamera();          // Démarrer la caméra
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stopper la camera
    }

    @Override
    public void handleResult(Result qrcode) {

        onBackPressed();

        // On crée une réference sur les promotions et on exécute une requête pour vérifier la correspondance du code
        //du qrcode
        CollectionReference mPromotionsRef = mPromotions.collection("promotions");

        mPromotionsRef.whereEqualTo("code", qrcode.getText()).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {

                            Toast.makeText(getApplicationContext(), "Ce code n'est pas valable", Toast.LENGTH_LONG).show();
                        } else {

                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                                Promotion promotion = documentSnapshot.toObject(Promotion.class);
                                Intent intent = new Intent(getApplicationContext(), DetailsPromotion.class);
                                intent.putExtra("description", promotion.getDescription());
                                intent.putExtra("imageUrl", promotion.getImgUrl());
                                intent.putExtra("magasin", promotion.getMagasin());
                                intent.putExtra("dateLimit", promotion.getDateLimit().toDate().toString());
                                startActivity(intent);
                                //Toast.makeText(getApplicationContext(), documentSnapshot.get("description").toString(), Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                });

    }
}
