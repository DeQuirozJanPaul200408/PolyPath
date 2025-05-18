package com.example.polypath;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class chinalanguage4 extends AppCompatActivity {

    MediaPlayer mysound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinalanguage4);

        mysound = MediaPlayer.create(chinalanguage4.this, R.raw.duibuqi);

        Button btnNext = findViewById(R.id.button3); // Next button
        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(chinalanguage4.this, chinalanguage5.class);
            startActivity(intent);
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(chinalanguage4.this, chinalanguage3.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }

    public void duibuqi(View v) {
        mysound.start();
    }
}
