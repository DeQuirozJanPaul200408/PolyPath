package com.example.polypath;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class chinalanguage5 extends AppCompatActivity {

    MediaPlayer mysound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinalanguage5);

        mysound = MediaPlayer.create(chinalanguage5.this, R.raw.meiguanxi);

        Button btnNext = findViewById(R.id.button3); // Next button
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(chinalanguage5.this, ChinaScoreResults.class);
            startActivity(intent);
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(chinalanguage5.this, chinalanguage4.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }

    public void meiguanxi(View v) {
        mysound.start();
    }
}
