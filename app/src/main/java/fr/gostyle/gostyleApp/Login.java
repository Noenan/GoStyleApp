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

import fr.gostyle.gostyleApp.models.User;

public class Login extends AppCompatActivity {
    TextView welcomText;
    EditText email, password;
    Button connexion, signup;
    private ProgressDialog mDialog;
    // [START declare_auth]
    private FirebaseAuth mFirebaseAuth;
    // [END declare_auth]


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/GreatVibesRegular.otf");
        Typeface typeEditText = Typeface.createFromAsset(getAssets(), "fonts/MavenPro-Regular.ttf");
        welcomText = findViewById(R.id.textViewBienvenue);
        welcomText.setTypeface(type);
        email = findViewById(R.id.editText_email);
        email.setTypeface(typeEditText);
        password = findViewById(R.id.editText_password);
        password.setTypeface(typeEditText);
        connexion = findViewById(R.id.button_login);
        connexion.setTypeface(typeEditText);
        signup = findViewById(R.id.btnLinkToRegisterScreen);
        signup.setTypeface(typeEditText);

        mDialog = new ProgressDialog(this);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]


        //fonction permettant de vérifier si l'utilisateur est déjà connecté pour le rediriger vers la bonne interface
        launchHomeScreen();


        //Lorsqu'on appuie sur ce bouton, il redirigesur l'interface d'inscription
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));

            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User userToSignIn = new User();
                userToSignIn.setEmail(email.getText().toString().trim());
                userToSignIn.setPassword(password.getText().toString().trim());
                loginUser(userToSignIn);
            }
        });

    }


    //Si l'utilisateur s'est déjà connecté une fois, on le redirige directement sur l'interface des promotions
    private void launchHomeScreen() {
        if (mFirebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
    }

    /**
     * Login User
     *
     * @param userToLogin
     */
    private void loginUser(User userToLogin) {
        if (!TextUtils.isEmpty(userToLogin.getEmail()) && !TextUtils.isEmpty(userToLogin.getPassword())) {
            mDialog.setMessage("Connexion en cours");
            mDialog.show();
            mDialog.setCanceledOnTouchOutside(false);
            mFirebaseAuth.signInWithEmailAndPassword(userToLogin.getEmail(), userToLogin.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Connexion avec succès", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        mDialog.dismiss();
                        finish();
                    } else {
                        // Cas où les données de l'utilisateur ne sont pas correctes
                        mDialog.dismiss();
                        String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                        switch (errorCode) {
                            case "ERROR_WRONG_PASSWORD":
                                Toast.makeText(getApplicationContext(), "Mot de passe incorrect, veuillez réessayer", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "Tous les champs doivent être remplis", Toast.LENGTH_LONG).show();
        }
    }
}