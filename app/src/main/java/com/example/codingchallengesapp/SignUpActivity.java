package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    EditText username, email, phone, password, confirmPassword;
    Button signUpButton;
    ImageView backButton, togglePassword, toggleConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize views
        username = findViewById(R.id.etUsername);
        email = findViewById(R.id.etEmail);
        phone = findViewById(R.id.etPhone);
        password = findViewById(R.id.etPassword);
        confirmPassword = findViewById(R.id.etConfirmPassword);
        signUpButton = findViewById(R.id.btnSignUp);
        backButton = findViewById(R.id.ivBack);
        togglePassword = findViewById(R.id.ivTogglePassword);
        toggleConfirmPassword = findViewById(R.id.ivToggleConfirmPassword);

        // Sign-Up button click listener
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String phoneNum = phone.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String confirmPass = confirmPassword.getText().toString().trim();

                if (user.isEmpty() || mail.isEmpty() || phoneNum.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(confirmPass)) {
                    Toast.makeText(SignUpActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper dbHelper = new DBHelper(SignUpActivity.this);

                    boolean inserted = dbHelper.insertUser(user, mail, phoneNum, pass);

                    if (inserted) {
                        Toast.makeText(SignUpActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Registration Failed! Email or Phone already exists.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Back button click listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Password visibility toggle
        togglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(password);
            }
        });

        toggleConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(confirmPassword);
            }
        });
    }

    // Show/hide password
    private void togglePasswordVisibility(EditText editText) {
        if (editText.getInputType() == 144) { // TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            editText.setInputType(129);       // TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD
        } else {
            editText.setInputType(144);
        }
        editText.setSelection(editText.getText().length()); // Keep cursor at end
    }
}
