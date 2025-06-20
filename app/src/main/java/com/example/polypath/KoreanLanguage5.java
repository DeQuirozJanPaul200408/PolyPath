package com.example.polypath;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

public class KoreanLanguage5 extends AppCompatActivity {

    private int correctAnswersCount;
    private MediaPlayer mysound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korean_language5);

        correctAnswersCount = getIntent().getIntExtra("correctAnswersCount", 0);
        mysound = MediaPlayer.create(this, R.raw.joesonghabnida);

        Button btnPlay = findViewById(R.id.btnPlaySound1);
        btnPlay.setOnClickListener(v -> mysound.start());

        Button btnSubmit = findViewById(R.id.button3);
        btnSubmit.setOnClickListener(v -> {
            RadioButton correctOption = findViewById(R.id.radioButton);
            if (correctOption.isChecked()) {
                correctAnswersCount++;
            }
            Intent intent = new Intent(this, KoreanScoreResults.class);
            intent.putExtra("correctAnswersCount", correctAnswersCount);
            startActivity(intent);
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }
}
