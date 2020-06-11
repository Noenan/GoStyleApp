package fr.gostyle.gostyleApp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailsPromotion extends AppCompatActivity {
    private String descriptionPromo, imageUrlPromo, magasinPromo, dateLimitePromo;
    private TextView description, magasin, dateLimit;
    private ImageView imageviewPromotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_promotion);

        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle.containsKey("description"))
                descriptionPromo = bundle.getString("description");
            if (bundle.containsKey("imageUrl"))
                imageUrlPromo = bundle.getString("imageUrl");
            if (bundle.containsKey("magasin"))
                magasinPromo = bundle.getString("magasin");
            if (bundle.containsKey("dateLimit"))
                dateLimitePromo = bundle.getString("dateLimit");
        } catch (NullPointerException e) {
        }
        description = findViewById(R.id.textview_description);
        magasin = findViewById(R.id.textview_magasin);
        dateLimit = findViewById(R.id.textview_date_limit);
        imageviewPromotion = findViewById(R.id.imageview_promotion);

        //Affichage des différentes informations dans les composants xml
        description.setText(descriptionPromo);
        magasin.setText(magasinPromo);
        dateLimit.setText(dateLimitePromo);
        Picasso.get().load(imageUrlPromo).into(imageviewPromotion);
    }
}
