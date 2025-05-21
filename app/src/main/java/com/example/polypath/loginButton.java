package com.example.polypath;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class loginButton extends AppCompatActivity {

    Button loginButton, create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_button);

        loginButton = findViewById(R.id.btnlogin);
        create = findViewById(R.id.btnCreate);

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(loginButton.this, LoginActivity.class);
            startActivity(intent);
        });

        create.setOnClickListener(v -> {
            Intent intent = new Intent(loginButton.this, CreateAccountActivity.class);
            startActivity(intent);
        });
    }
}
