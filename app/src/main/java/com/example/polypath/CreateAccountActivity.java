package com.example.polypath;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    EditText fullNameField, emailField, passwordField;
    TextView resultView;
    Button createAccountButton, backButton;

    public static final String PREFS_NAME = "UserPrefs";
    public static final String KEY_FULL_NAME = "full_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        fullNameField = findViewById(R.id.etFullName);
        emailField = findViewById(R.id.etCreateEmail);
        passwordField = findViewById(R.id.etPassword);
        resultView = findViewById(R.id.tvCreateResult);
        createAccountButton = findViewById(R.id.btnCreateAccount);
        backButton = findViewById(R.id.btnBack);

        resultView.setVisibility(View.GONE);

        createAccountButton.setOnClickListener(view -> {
            String name = fullNameField.getText().toString().trim();
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            resultView.setVisibility(View.GONE);

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                resultView.setText("All fields are required.");
                resultView.setTextColor(Color.RED);
                resultView.setVisibility(View.VISIBLE);
            } else {
                createAccountOnServer(name, email, password);
            }
        });

        backButton.setOnClickListener(v -> finish());
    }

    private void createAccountOnServer(String name, String email, String password) {
        StringRequest request = new StringRequest(Request.Method.POST, Endpoints.CREATE_ACCOUNT,
                response -> {
                    Log.d("CREATE_ACCOUNT_RESPONSE", response);

                    try {
                        JSONObject json = new JSONObject(response);
                        String status = json.getString("status");
                        String message = json.getString("message");

                        if ("success".equals(status)) {
                            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString(KEY_FULL_NAME, name);
                            editor.apply();

                            Toast.makeText(this, "Sign up successful! Please log in.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(this, LoginActivity.class));
                            finish();
                        } else {
                            resultView.setText(message);
                            resultView.setTextColor(Color.RED);
                            resultView.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception e) {
                        resultView.setText("Error parsing server response.");
                        resultView.setTextColor(Color.RED);
                        resultView.setVisibility(View.VISIBLE);
                        Log.e("PARSE_ERROR", "Exception: " + e.getMessage());
                    }
                },
                error -> {
                    resultView.setText("Network error: " + error.getMessage());
                    resultView.setTextColor(Color.RED);
                    resultView.setVisibility(View.VISIBLE);
                    Log.e("VOLLEY_ERROR", error.toString());
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("USERNAME", name);
                params.put("EMAIL", email);
                params.put("PASSWORD", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }
}
