package com.example.codingchallengesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.*;

import java.util.Objects;

public class EditProfile extends AppCompatActivity {

    private ImageButton backButton;
    private ImageView profileImageView;
    private EditText userIdEditText, userNameEditText, emailIdEditText, phoneNumberEditText;
    private Button updateProfileButton;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            currentUserId = currentUser.getUid();
        } else {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize views
        backButton = findViewById(R.id.back_button);
        profileImageView = findViewById(R.id.profileImage);
        userIdEditText = findViewById(R.id.user_id_edit_text);
        userNameEditText = findViewById(R.id.user_name_edit_text);
        emailIdEditText = findViewById(R.id.email_id_edit_text);
        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        updateProfileButton = findViewById(R.id.update_profile_button);

        // Load user data
        loadUserData();

        // Button: Back
        backButton.setOnClickListener(v -> finish());

        // Button: Update Profile
        updateProfileButton.setOnClickListener(v -> updateUserProfile());

        // Image click
        profileImageView.setOnClickListener(v ->
                Toast.makeText(this, "Select Profile Picture", Toast.LENGTH_SHORT).show());
    }

    private void loadUserData() {
        db.collection("users").document(currentUserId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        userIdEditText.setText(documentSnapshot.getString("user_id"));
                        userNameEditText.setText(documentSnapshot.getString("name"));
                        emailIdEditText.setText(documentSnapshot.getString("email"));
                        phoneNumberEditText.setText(documentSnapshot.getString("phone"));
                    } else {
                        Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Failed to load data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void updateUserProfile() {
        String userName = userNameEditText.getText().toString().trim();
        String email = emailIdEditText.getText().toString().trim();
        String phone = phoneNumberEditText.getText().toString().trim();

        if (userName.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check email and phone uniqueness
        db.collection("users")
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    boolean emailExists = false;
                    boolean phoneExists = false;

                    for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                        if (!Objects.equals(doc.getId(), currentUserId)) {
                            if (email.equals(doc.getString("email"))) {
                                emailExists = true;
                            }
                            if (phone.equals(doc.getString("phone"))) {
                                phoneExists = true;
                            }
                        }
                    }

                    if (emailExists) {
                        Toast.makeText(this, "Email already in use", Toast.LENGTH_SHORT).show();
                    } else if (phoneExists) {
                        Toast.makeText(this, "Phone already in use", Toast.LENGTH_SHORT).show();
                    } else {
                        saveProfileToFirestore(userName, email, phone);
                    }
                });
    }

    private void saveProfileToFirestore(String userName, String email, String phone) {
        db.collection("users").document(currentUserId)
                .update(
                        "name", userName,
                        "email", email,
                        "phone", phone
                )
                .addOnSuccessListener(aVoid ->
                        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Update failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
