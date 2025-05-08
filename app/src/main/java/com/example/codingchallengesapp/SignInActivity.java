package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    EditText username, password;
    Button signInButton, tvForgotPassword;
    ImageView backButton, togglePassword;

    private FirebaseAuth mAuth; // ✅ FirebaseAuth instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // ✅ Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        signInButton = findViewById(R.id.btnSignIn);
        backButton = findViewById(R.id.ivBack);
        togglePassword = findViewById(R.id.ivTogglePassword);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString().trim(); // using email for login
                String pass = password.getText().toString().trim();

                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, pass); // ✅ Call Firebase login
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        togglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(password);
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, ChangePassword.class);
                startActivity(intent);
            }
        });
    }

    private void togglePasswordVisibility(EditText editText) {
        if (editText.getInputType() == 144) {
            editText.setInputType(129);
        } else {
            editText.setInputType(144);
        }
        editText.setSelection(editText.getText().length());
    }

    // ✅ Firebase Login
    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(SignInActivity.this, "Sign-In Successful!", Toast.LENGTH_SHORT).show();

                        // Navigate to the GreetingScreen
                        Intent intent = new Intent(SignInActivity.this, GreetingScreenActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignInActivity.this, "Sign-In Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
