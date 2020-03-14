package fr.gostyle.gostyleApp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private TextView welcomText;
    private EditText editTextNom, editTextPrenom, editTextEmail, editTextPassword, editTextPassword2;
    private Button inscription, signinButton;
    private ProgressDialog mDialog;
    private DatabaseReference mDatabase;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/GreatVibesRegular.otf");
        Typeface typeEditText = Typeface.createFromAsset(getAssets(), "fonts/MavenPro-Regular.ttf");

        editTextNom = findViewById(R.id.editText_nom);
        editTextNom.setTypeface(typeEditText);
        editTextPrenom = findViewById(R.id.editText_prenom);
        editTextPrenom.setTypeface(typeEditText);
        editTextEmail = findViewById(R.id.editText_email);
        editTextEmail.setTypeface(typeEditText);
        editTextPassword = findViewById(R.id.editText_password);
        editTextPassword.setTypeface(typeEditText);
        editTextPassword2 = findViewById(R.id.editText_password2);
        editTextPassword2.setTypeface(typeEditText);

        welcomText = findViewById(R.id.textViewBienvenue);
        welcomText.setTypeface(type);

        inscription = findViewById(R.id.button_signup);
        signinButton = findViewById(R.id.btn_link_to_login);
        signinButton.setTypeface(typeEditText);

        mDialog = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Registration");
        mFirebaseAuth = FirebaseAuth.getInstance();

        //Redirige vers l'interface de connexion
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = editTextNom.getText().toString().trim();
                final String firstname = editTextPrenom.getText().toString().trim();
                final String mail = editTextEmail.getText().toString().trim();
                String motdepasse = editTextPassword.getText().toString().trim();
                String motdepasse2 = editTextPassword2.getText().toString().trim();

                registerUser(name, firstname, mail, motdepasse, motdepasse2);
            }
        });

    }

    /**
     * fonction permettant d'enregistrer un utilisateur dans firebase
     *
     * @param nom
     * @param prenom
     * @param email
     * @param password
     * @param password2
     */
    private void registerUser(final String nom, final String prenom, final String email, String password, String password2) {

        if (!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(prenom) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(password2)) {

            if (password.equals(password2)) {
                mDialog.setMessage("Inscription en cours");
                mDialog.show();
                mDialog.setCanceledOnTouchOutside(false);
                mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            DatabaseReference currentUser = mDatabase.child(user.getUid());
                            currentUser.child("editTextNom").setValue(nom);
                            currentUser.child("editTextPrenom").setValue(prenom);
                            currentUser.child("editTextEmail").setValue(email);
                            Toast.makeText(getApplicationContext(), "Inscription effectuée", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                            mDialog.dismiss();
                        } else {
                            mDialog.dismiss();
                            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                            switch (errorCode) {
                                case "ERROR_WEAK_PASSWORD":
                                    Toast.makeText(getApplicationContext(), "Votre mot de passe doit contenir au moins 6 caractères", Toast.LENGTH_LONG).show();
                                    break;
                                case "ERROR_INVALID_EMAIL":
                                    Toast.makeText(getApplicationContext(), "Le format de votre adresse mail est incorrect", Toast.LENGTH_LONG).show();

                                    editTextEmail.setError("Le format de votre adresse mail est incorrect");
                                    editTextEmail.requestFocus();
                                    break;
                                case "ERROR_EMAIL_ALREADY_IN_USE":
                                    Toast.makeText(getApplicationContext(), "Adresse mail déjà utilisé,veuillez réessayer", Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }


                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Mots de passes non identiques, veuillez réessayer", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Tous les champs doivent être remplis", Toast.LENGTH_LONG).show();
        }
    }
}



