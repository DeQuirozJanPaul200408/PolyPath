package com.example.polypath;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class loginButton extends AppCompatActivity {

    Button btnLogin, btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_button);

        btnLogin = findViewById(R.id.btnlogin);
        btnCreate = findViewById(R.id.btnCreate);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(loginButton.this, LoginActivity.class);
            startActivity(intent);
        });

        btnCreate.setOnClickListener(v -> {
            Intent intent = new Intent(loginButton.this, CreateAccountActivity.class);
            startActivity(intent);
        });
    }
}
