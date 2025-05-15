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

public class CreateAccountActivity extends AppCompatActivity {

    EditText fullName, email;
    TextView result;
    Button createAccount, backButton;

    public static final String PREFS_NAME = "UserPrefs";
    public static final String KEY_FULL_NAME = "full_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        fullName = findViewById(R.id.etFullName);
        email = findViewById(R.id.etCreateEmail);
        result = findViewById(R.id.tvCreateResult);
        createAccount = findViewById(R.id.btnCreateAccount);
        backButton = findViewById(R.id.btnBack); // Make sure this ID exists in your XML

        createAccount.setOnClickListener(view -> {
            String name = fullName.getText().toString().trim();
            String mail = email.getText().toString().trim();

            if (name.isEmpty() || mail.isEmpty()) {
                result.setText("All fields are required.");
                result.setTextColor(Color.RED);
            } else {
                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(KEY_FULL_NAME, name);
                editor.apply();

                Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            result.setVisibility(View.VISIBLE);
        });

        backButton.setOnClickListener(v -> finish());
    }

    private void clearFields() {
        fullName.setText("");
        email.setText("");
        fullName.requestFocus();
    }
}
