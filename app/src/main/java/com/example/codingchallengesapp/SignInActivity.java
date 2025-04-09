package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    EditText username, password;
    Button signInButton, tvForgotPassword;
    ImageView backButton, togglePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialize UI components
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        signInButton = findViewById(R.id.btnSignIn);
        backButton = findViewById(R.id.ivBack);
        togglePassword = findViewById(R.id.ivTogglePassword);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        // Sign-In Button Click
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper dbHelper = new DBHelper(SignInActivity.this);
                    boolean valid = dbHelper.checkUser(user, pass);

                    if (valid) {
                        Toast.makeText(SignInActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignInActivity.this, GreetingScreenActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignInActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Back Button Click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Toggle Password Visibility
        togglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(password);
            }
        });

        // Forgot Password Click
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, ChangePassword.class);
                startActivity(intent);
            }
        });
    }

    // Password show/hide logic
    private void togglePasswordVisibility(EditText editText) {
        if (editText.getInputType() == 144) {
            editText.setInputType(129);
        } else {
            editText.setInputType(144);
        }
        editText.setSelection(editText.getText().length());
    }
}
