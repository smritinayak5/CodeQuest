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

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        signInButton = findViewById(R.id.btnSignIn);
        backButton = findViewById(R.id.ivBack);
        togglePassword = findViewById(R.id.ivTogglePassword);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignInActivity.this, "Sign-In Successful!", Toast.LENGTH_SHORT).show();
                    // Navigate to Sign-In Page after successful registration
                    Intent intent = new Intent(SignInActivity.this, GreetingScreenActivity.class);
                    startActivity(intent);
                    finish();
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

        // Navigate to ChangePassword activity when "Forgot Password?" is clicked
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, ChangePassword.class);
                startActivity(intent); // Open the Change Password screen
            }
        });

    }

    private void togglePasswordVisibility(EditText editText) {
        if (editText.getInputType() == 144) { // 144 = TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            editText.setInputType(129); // 129 = TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD
        } else {
            editText.setInputType(144);
        }
        editText.setSelection(editText.getText().length()); // Keep cursor at the end
    }
}
