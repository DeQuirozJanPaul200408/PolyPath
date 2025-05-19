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

public class CreateAccount extends AppCompatActivity {

    EditText fullName, email, password;
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
        password = findViewById(R.id.etPassword);
        result = findViewById(R.id.tvCreateResult);
        createAccount = findViewById(R.id.btnCreateAccount);
        backButton = findViewById(R.id.btnBack);

        createAccount.setOnClickListener(view -> {
            String name = fullName.getText().toString().trim();
            String mail = email.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (name.isEmpty() || mail.isEmpty() || pass.isEmpty()) {
                result.setText("All fields are required.");
                result.setTextColor(Color.RED);
                result.setVisibility(View.VISIBLE);
            } else {
                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(KEY_FULL_NAME, name);
                editor.putString("user_email", mail);
                editor.putString("user_password", pass); // For dev only
                editor.apply();

                createAccountOnServer(name, mail, pass);
            }
        });

        backButton.setOnClickListener(v -> finish());
    }

    private void createAccountOnServer(String name, String mail, String pass) {
        StringRequest request = new StringRequest(Request.Method.POST, Endpoints.CREATE_ACCOUNT,
                response -> {
                    Log.d("RAW_RESPONSE", response); // Debug

                    try {
                        JSONObject json = new JSONObject(response);
                        String status = json.getString("status");
                        String message = json.getString("message");

                        if (status.equals("success")) {
                            Toast.makeText(this, "Sign Up successful! Welcome " + name, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(this, LoginActivity.class));
                            finish();
                        } else {
                            result.setText(message);
                            result.setTextColor(Color.RED);
                            result.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception e) {
                        result.setText("Error parsing server response.");
                        result.setTextColor(Color.RED);
                        result.setVisibility(View.VISIBLE);
                        Log.e("PARSE_ERROR", "Exception: " + e.getMessage());
                    }
                },
                error -> {
                    result.setText("Network error: " + error.getMessage());
                    result.setTextColor(Color.RED);
                    result.setVisibility(View.VISIBLE);
                    Log.e("VOLLEY_ERROR", error.toString());
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("USERNAME", name);
                map.put("EMAIL", mail);
                map.put("PASSWORD", pass);
                return map;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }
}
