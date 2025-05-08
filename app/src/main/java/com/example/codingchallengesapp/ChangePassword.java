package com.example.codingchallengesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;

public class ChangePassword extends AppCompatActivity {

    private EditText emailEditText;
    private Button changePasswordButton;
    private ImageButton backButton;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_change); // Ensure XML matches

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        emailEditText = findViewById(R.id.email_edit_text);
        changePasswordButton = findViewById(R.id.change_password_button);
        backButton = findViewById(R.id.back_button);

        changePasswordButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(ChangePassword.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            } else {
                // Step 1: Check if email exists in Firestore
                db.collection("users")
                        .whereEqualTo("email", email)
                        .get()
                        .addOnSuccessListener(queryDocumentSnapshots -> {
                            if (queryDocumentSnapshots.isEmpty()) {
                                Toast.makeText(ChangePassword.this, "Email not registered. Please sign up.", Toast.LENGTH_LONG).show();
                            } else {
                                // Step 2: Send password reset email
                                mAuth.sendPasswordResetEmail(email)
                                        .addOnSuccessListener(aVoid -> {
                                            Toast.makeText(ChangePassword.this, "Password reset email sent!", Toast.LENGTH_LONG).show();
                                            emailEditText.setText("");
                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(ChangePassword.this, "Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                        });
                            }
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(ChangePassword.this, "Error checking email: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        });
            }
        });

        backButton.setOnClickListener(v -> onBackPressed());
    }
}
