package fr.gostyle.gostyleApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {
    TextView welcomText;
    EditText nom,prenom,email,password,password2;
    Button inscription, signinButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/GreatVibesRegular.otf");
        Typeface typeEditText = Typeface.createFromAsset(getAssets(), "fonts/MavenPro-Regular.ttf");

        nom = findViewById(R.id.editText_nom);
        nom.setTypeface(typeEditText);
        prenom = findViewById(R.id.editText_prenom);
        prenom.setTypeface(typeEditText);
        email = findViewById(R.id.editText_email);
        email.setTypeface(typeEditText);
        password = findViewById(R.id.editText_password);
        password.setTypeface(typeEditText);
        password2 = findViewById(R.id.editText_password2);
        password2.setTypeface(typeEditText);

        welcomText = findViewById(R.id.textViewBienvenue);
        welcomText.setTypeface(type);

        inscription = findViewById(R.id.button_signup);
        signinButton = findViewById(R.id.btn_link_to_login);
        signinButton.setTypeface(typeEditText);

        //Redirige vers l'interface de connexion
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
