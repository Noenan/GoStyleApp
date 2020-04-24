package fr.gostyle.gostyleApp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;


import fr.gostyle.gostyleApp.Adapters.PromotionAdapter;
import fr.gostyle.gostyleApp.models.Promotion;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mUserRecyclerView;
    private PromotionAdapter mAdapter;
    FirebaseFirestore mPromotions = FirebaseFirestore.getInstance();
    // [START declare_auth]
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener fireAuthListener;
    // [END declare_auth]




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On attache le Recyclerview xml à son instance java
        mUserRecyclerView = findViewById(R.id.fragment_main_recyclerView);
        mUserRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        // [START initialize_auth]
        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        fireAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user == null) {
                    //user not login
                    MainActivity.this.startActivity(new Intent(MainActivity.this, Login.class));
                    MainActivity.this.finish();
                }
            }
        };
        //Fonction récupérant toutes les promotions depuis firestore
        getAllPromotions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.qrcode:
                startActivity(new Intent(getApplicationContext(), ScanActivity.class));
                return true;
            case R.id.deconnexion:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Déconnexion");
                builder.setMessage("Voulez-vous vraiment vous déconnectez ?");
                // Ajout des boutons
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mFirebaseAuth.signOut();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        finish();
                    }
                });
                builder.setNegativeButton("Non", null);
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /*Exécution d'une requête pour récuper les documents de la collection promotions
     * Ensuite on peuple le recyclerview avec les données récupérées*/

    private void getAllPromotions() {
        Query query = mPromotions.collection("promotions");
        FirestoreRecyclerOptions<Promotion> options = new FirestoreRecyclerOptions.Builder<Promotion>()
                .setQuery(query, Promotion.class)
                .build();
        mAdapter = new PromotionAdapter(options);
        mUserRecyclerView.setAdapter(mAdapter);

        // Pour suivre le clic sur un élement on passe les données de l'element cliqué à l'activité affichant les détails d'une promo
        mAdapter.setOnItemClickListener(new PromotionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Promotion promotion = documentSnapshot.toObject(Promotion.class);
                // Toast.makeText(getApplicationContext(),"Promotion: "+ promotion.getDateLimit().toDate(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), DetailsPromotion.class);
                intent.putExtra("description", promotion.getDescription());
                intent.putExtra("imageUrl", promotion.getImgUrl());
                intent.putExtra("magasin", promotion.getMagasin());
                intent.putExtra("dateLimit", promotion.getDateLimit().toDate().toString());
                startActivity(intent);
            }
        });


    }


    // Affichage de l'interface plus l'écoute pour constater la présence ou non de données issues de firestore
    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
        mFirebaseAuth.addAuthStateListener(fireAuthListener);
    }

    // Au changement d'interface , on stoppe l'écoute de l'adapter
    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
        mFirebaseAuth.removeAuthStateListener(fireAuthListener);
    }


}
