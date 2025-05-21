package com.example.polypath;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

public class chinalanguage extends AppCompatActivity {

    private int correctAnswersCount = 0;
    private MediaPlayer mysound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinalanguage);

        mysound = MediaPlayer.create(this, R.raw.bukeqi);

        Button btnPlay = findViewById(R.id.btnPlaySound1);
        btnPlay.setOnClickListener(v -> mysound.start());

        Button btnSubmit = findViewById(R.id.button3);
        btnSubmit.setOnClickListener(v -> {
            RadioButton correctOption = findViewById(R.id.radioButton);
            if (correctOption.isChecked()) {
                correctAnswersCount++;
            }
            Intent intent = new Intent(this, chinalanguage2.class);
            intent.putExtra("correctAnswersCount", correctAnswersCount);
            startActivity(intent);
        });

        // First screen - back to language list
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(chinalanguage.this, CountryListActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
