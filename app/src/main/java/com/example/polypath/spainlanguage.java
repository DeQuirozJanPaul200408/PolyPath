package com.example.polypath;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class spainlanguage extends AppCompatActivity {

    MediaPlayer mysound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spainlanguage);
        mysound = MediaPlayer.create(spainlanguage.this, R.raw.cuantos_anos_tiene);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(spainlanguage.this, CountryListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }

    public void spain(View v) {
        mysound.start();
    }
}