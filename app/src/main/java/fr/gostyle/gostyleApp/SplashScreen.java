package fr.gostyle.gostyleApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchMainScreen();
            }
        }, SPLASH_TIME_OUT);
    }


    // Launch Main Screen
    private void launchMainScreen() {
        startActivity(new Intent(SplashScreen.this, Login.class));
        finish();
    }
}
