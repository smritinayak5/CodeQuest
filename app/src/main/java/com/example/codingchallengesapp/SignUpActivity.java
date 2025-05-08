package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    EditText username, email, phone, password, confirmPassword;
    Button signUpButton;
    ImageView backButton, togglePassword, toggleConfirmPassword;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.etUsername);
        email = findViewById(R.id.etEmail);
        phone = findViewById(R.id.etPhone);
        password = findViewById(R.id.etPassword);
        confirmPassword = findViewById(R.id.etConfirmPassword);
        signUpButton = findViewById(R.id.btnSignUp);
        backButton = findViewById(R.id.ivBack);
        togglePassword = findViewById(R.id.ivTogglePassword);
        toggleConfirmPassword = findViewById(R.id.ivToggleConfirmPassword);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        signUpButton.setOnClickListener(v -> {
            String user = username.getText().toString().trim();
            String mail = email.getText().toString().trim();
            String phoneNum = phone.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String confirmPass = confirmPassword.getText().toString().trim();

            if (user.isEmpty() || mail.isEmpty() || phoneNum.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            } else if (!pass.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(user, mail, phoneNum, pass);
            }
        });

        backButton.setOnClickListener(v -> onBackPressed());

        togglePassword.setOnClickListener(v -> togglePasswordVisibility(password));
        toggleConfirmPassword.setOnClickListener(v -> togglePasswordVisibility(confirmPassword));
    }

    private void togglePasswordVisibility(EditText editText) {
        if (editText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        editText.setSelection(editText.getText().length());
    }

    private void registerUser(String name, String email, String phone, String password) {
        // Email validation
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{3,}$")) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Phone validation
        if (!phone.matches("^\\d{10}$")) {
            Toast.makeText(this, "Phone must be 10 digits", Toast.LENGTH_SHORT).show();
            return;
        }

        // Step 1: Check for duplicate email
        db.collection("users").whereEqualTo("email", email).get().addOnSuccessListener(querySnapshot -> {
            if (!querySnapshot.isEmpty()) {
                Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
                return;
            }

            // Step 2: Check for duplicate phone
            db.collection("users").whereEqualTo("phone", phone).get().addOnSuccessListener(phoneSnapshot -> {
                if (!phoneSnapshot.isEmpty()) {
                    Toast.makeText(this, "Phone number already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Step 3: Count existing user_name matches for user_id generation
                db.collection("users").whereEqualTo("name", name).get().addOnSuccessListener(nameSnapshot -> {
                    int count = nameSnapshot.size() + 1;
                    String userId = name.toLowerCase().replaceAll("\\s+", "_") + "_" + count;

                    // Step 4: Register user with Firebase Auth
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String uid = firebaseUser.getUid();

                            Map<String, Object> userMap = new HashMap<>();
                            userMap.put("uid", uid);
                            userMap.put("user_id", userId);
                            userMap.put("name", name);
                            userMap.put("email", email);
                            userMap.put("phone", phone);
                            userMap.put("password", password); // Optional: encrypt this in real apps

                            db.collection("users").document(uid).set(userMap)
                                    .addOnSuccessListener(unused -> {
                                        Toast.makeText(this, "Sign-Up Successful!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(this, SignInActivity.class));
                                        finish();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(this, "Failed to save user: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    });
                        } else {
                            Toast.makeText(this, "Auth failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                });
            });
        });
    }
}
