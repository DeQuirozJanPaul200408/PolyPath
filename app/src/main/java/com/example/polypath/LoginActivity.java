package com.example.polypath;

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
    Button loginButton;

    public static final String PREFS_NAME = "UserPrefs";
    public static final String KEY_FULL_NAME = "full_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = findViewById(R.id.etLoginUsername);
        loginResult = findViewById(R.id.tvLoginResult);
        loginButton = findViewById(R.id.btnLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredName = usernameField.getText().toString().trim();

                // Retrieve the saved full name from SharedPreferences
                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                String savedName = prefs.getString(KEY_FULL_NAME, "");

                // Check if the entered name matches the saved name
                if (enteredName.isEmpty()) {
                    loginResult.setText("Please enter your full name.");
                    loginResult.setTextColor(Color.RED);
                } else if (enteredName.equals(savedName)) {
                    loginResult.setText("Login successful!");
                    loginResult.setTextColor(Color.GREEN);
                } else {
                    loginResult.setText("Name not recognized.");
                    loginResult.setTextColor(Color.RED);
                }

                loginResult.setVisibility(View.VISIBLE);
            }
        });
    }
}
