package fr.gostyle.gostyleApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.gostyle.gostyleApp.preference.PrefManager;

public class Login extends AppCompatActivity {
    TextView welcomText;
    EditText email,password;
    Button  connexion,signup;
     PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefManager = new PrefManager(this);
        /*if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }*/
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

        //Lorsqu'on appuie sur ce bouton, il redirigesur l'interface d'inscription
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));

            }
        });

    }


    //Si l'utilisateur s'est déjà connecté une fois, on le redirige directement sur l'interface des promotions
    private void launchHomeScreen() {
        startActivity(new Intent(Login.this, MainActivity.class));
        prefManager.setFirstTimeLaunch(false);
        finish();
    }
}
