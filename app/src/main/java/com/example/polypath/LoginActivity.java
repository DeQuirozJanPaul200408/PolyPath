package com.example.polypath;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText usernameField;
    TextView loginResult;
    Button loginButton, backButton;

    public static final String PREFS_NAME = "UserPrefs";
    public static final String KEY_FULL_NAME = "full_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = findViewById(R.id.etLoginUsername);
        loginResult = findViewById(R.id.tvLoginResult);
        loginButton = findViewById(R.id.btnLogin);
        backButton = findViewById(R.id.btnBack); // Make sure this ID exists in your XML

        loginButton.setOnClickListener(view -> {
            String enteredName = usernameField.getText().toString().trim();

            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            String savedName = prefs.getString(KEY_FULL_NAME, "").trim();

            if (enteredName.isEmpty()) {
                loginResult.setText("Please enter your full name.");
                loginResult.setTextColor(Color.RED);
            } else if (enteredName.equals(savedName)) {
                loginResult.setText("Login successful!");
                loginResult.setTextColor(Color.GREEN);

                Intent intent = new Intent(LoginActivity.this, CountryListActivity.class);
                startActivity(intent);
                finish();
            } else {
                loginResult.setText("Name not recognized.");
                loginResult.setTextColor(Color.RED);
            }

            loginResult.setVisibility(View.VISIBLE);
        });

        backButton.setOnClickListener(v -> finish());
    }
}
