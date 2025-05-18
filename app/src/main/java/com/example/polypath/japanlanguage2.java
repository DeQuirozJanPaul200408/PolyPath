package com.example.polypath;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class japanlanguage2 extends AppCompatActivity {

    MediaPlayer mysound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_japanlanguage2);

        mysound = MediaPlayer.create(japanlanguage2.this, R.raw.anatanonamaewananidesuka);

        Button btnNext = findViewById(R.id.button3); // Next button
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(japanlanguage2.this, japanlanguage3.class);
            startActivity(intent);
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(japanlanguage2.this, japanlanguage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }

    public void anatanonamaewananidesuka(View v) {
        mysound.start();
    }
}
