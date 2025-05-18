package com.example.polypath;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class KoreanLanguage5 extends AppCompatActivity {

    MediaPlayer mysound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korean_language5);

        mysound = MediaPlayer.create(KoreanLanguage5.this, R.raw.joesonghabnida
        ); // Ensure this file exists in res/raw

        // "Next" button -> KoreanLanguage4
        Button btnNext = findViewById(R.id.button3);
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(KoreanLanguage5.this, KoreanScoreResults.class);
            startActivity(intent);
        });

        // "Back" button -> KoreanLanguage2 (fixed from KoreanLanguage4)
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(KoreanLanguage5.this, KoreanLanguage4.class); // <- was pointing to KoreanLanguage4
            startActivity(intent);
        });
    }

    // This method name must match android:onClick in your XML
    public void joesonghabnida(View view) {
        if (mysound != null) {
            mysound.start();
        }
    }
}