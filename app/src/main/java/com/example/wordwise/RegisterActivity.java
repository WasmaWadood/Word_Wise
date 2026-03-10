package com.example.wordwise;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wordwise.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etRegEmail, etRegPassword, etRegConfirmPassword;
    private Button btnRegister;
    private TextView tvBackToLogin;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegEmail           = findViewById(R.id.etRegEmail);
        etRegPassword        = findViewById(R.id.etRegPassword);
        etRegConfirmPassword = findViewById(R.id.etRegConfirmPassword);
        btnRegister          = findViewById(R.id.btnRegister);
        tvBackToLogin        = findViewById(R.id.tvBackToLogin);

        databaseHelper = new DatabaseHelper(this);

        btnRegister.setOnClickListener(v -> validateAndRegister());

        tvBackToLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void validateAndRegister() {
        String email           = etRegEmail.getText().toString().trim();
        String password        = etRegPassword.getText().toString().trim();
        String confirmPassword = etRegConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            etRegEmail.setError("Email is required");
            etRegEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etRegEmail.setError("Enter a valid email address");
            etRegEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etRegPassword.setError("Password is required");
            etRegPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etRegPassword.setError("Password must be at least 6 characters");
            etRegPassword.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            etRegConfirmPassword.setError("Please confirm your password");
            etRegConfirmPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            etRegConfirmPassword.setError("Passwords do not match");
            etRegConfirmPassword.requestFocus();
            return;
        }

        if (databaseHelper.checkEmail(email)) {
            Toast.makeText(this, "User already exists! Please login.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isinserted = databaseHelper.registerUser(email, password);
        if (isinserted) {
            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
            // Navigate to LoginActivity
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Registration failed! Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}