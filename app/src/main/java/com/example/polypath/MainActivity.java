package com.example.polypath;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Ensure correct layout is set

        // Initialize both buttons using the correct IDs
        btnStart = findViewById(R.id.btnStart);

        // Check if buttons are not null before setting onClickListeners

            // Set click listeners for both buttons
            btnStart.setOnClickListener(v ->
                    startActivity(new Intent(this, CreateAccountActivity.class)));

    }
}
