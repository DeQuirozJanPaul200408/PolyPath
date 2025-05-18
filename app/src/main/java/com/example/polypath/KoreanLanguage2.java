package com.example.polypath;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class KoreanLanguage2 extends AppCompatActivity {

    MediaPlayer mysound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koreanlanguage2); // Make sure this layout exists

        mysound = MediaPlayer.create(KoreanLanguage2.this, R.raw.salanghaeyo); // Check correct file name

        Button btnNext = findViewById(R.id.button3); // Next button
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(KoreanLanguage2.this, KoreanLanguage3.class);
            startActivity(intent);
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(KoreanLanguage2.this, koreanlanguage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }

    public void salanghaeyo(View v) {
        mysound.start();
    }
}
