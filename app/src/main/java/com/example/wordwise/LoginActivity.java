package com.example.wordwise;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Link Java to XML
        etEmail    = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin   = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        // Login button click
        btnLogin.setOnClickListener(v -> validateAndLogin());

        // Navigate to Signup screen
        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, com.example.wordwise.RegisterActivity.class))
        );
    }

    private void validateAndLogin() {
        String email    = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Check email empty
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }

        // Check email format
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter a valid email address");
            etEmail.requestFocus();
            return;
        }

        // Check password empty
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        // Check password length
        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            etPassword.requestFocus();
            return;
        }

        // All validations passed
        Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();

        // TODO: Add real authentication here (Firebase, API, etc.)
        // On success navigate to MainActivity:
        // startActivity(new Intent(LoginActivity.this, MainActivity.class));
        // finish();
    }
}