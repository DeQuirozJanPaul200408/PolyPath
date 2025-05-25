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

public class LoginActivity extends AppCompatActivity {

    EditText usernameField, passwordField;
    TextView loginResult;
    Button loginButton, backButton;

    public static final String PREFS_NAME = "UserPrefs";
    public static final String KEY_FULL_NAME = "full_name";
    public static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = findViewById(R.id.etLoginUsername);
        passwordField = findViewById(R.id.etLoginPassword);
        loginResult = findViewById(R.id.tvLoginResult);
        loginButton = findViewById(R.id.btnLogin);
        backButton = findViewById(R.id.btnBack);

        loginResult.setVisibility(View.GONE);

        loginButton.setOnClickListener(view -> {
            String enteredName = usernameField.getText().toString().trim();
            String enteredPassword = passwordField.getText().toString().trim();

            loginResult.setVisibility(View.GONE);

            if (enteredName.isEmpty() || enteredPassword.isEmpty()) {
                loginResult.setText("Please enter both username and password.");
                loginResult.setTextColor(Color.RED);
                loginResult.setVisibility(View.VISIBLE);
                return;
            }

            checkUserFromServer(enteredName, enteredPassword);
        });

        backButton.setOnClickListener(v -> finish());
    }

    private void checkUserFromServer(String username, String password) {
        StringRequest request = new StringRequest(Request.Method.POST, Endpoints.LOG_IN,
                response -> {
                    Log.d("LOGIN_RESPONSE", response);
                    try {
                        JSONObject json = new JSONObject(response);
                        String status = json.getString("status");
                        String message = json.getString("message");

                        Log.d("LOGIN_STATUS", "Status: " + status);
                        Log.d("LOGIN_MESSAGE", "Message: " + message);

                        if ("success".equals(status)) {
                            String fullName = json.getString("full_name");

                            // ✅ Save full name and username to SharedPreferences
                            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString(KEY_FULL_NAME, fullName);
                            editor.putString(KEY_USERNAME, username); // ✅ Save username
                            editor.apply();

                            loginResult.setText("Login successful! Welcome " + fullName);
                            loginResult.setTextColor(Color.BLACK);
                            loginResult.setVisibility(View.VISIBLE);

                            Intent intent = new Intent(LoginActivity.this, CountryListActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            loginResult.setText(message);
                            loginResult.setTextColor(Color.RED);
                            loginResult.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception e) {
                        loginResult.setText("Error parsing server response.");
                        loginResult.setTextColor(Color.RED);
                        loginResult.setVisibility(View.VISIBLE);
                        Log.e("PARSE_ERROR", "Exception: " + e.getMessage());
                    }
                },
                error -> {
                    loginResult.setText("Network error: " + error.getMessage());
                    loginResult.setTextColor(Color.RED);
                    loginResult.setVisibility(View.VISIBLE);
                    Log.e("VOLLEY_ERROR", error.toString());
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("USERNAME", username);
                params.put("PASSWORD", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }
}
