package com.cinemacar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cinemacar.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        goToListFilm();
    }

    private void goToListFilm() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

}
