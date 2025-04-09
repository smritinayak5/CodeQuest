package com.example.codingchallengesapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfile extends AppCompatActivity {

    EditText userIdEditText, userNameEditText, emailEditText, phoneEditText;
    Button updateProfileButton;
    ImageButton backButton;
    DBHelper dbHelper;
    String currentUserId; // User ID passed from previous activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        // Get the userId from Intent extras
        currentUserId = getIntent().getStringExtra("user_id");

        // Link UI elements
        userIdEditText = findViewById(R.id.user_id_edit_text);
        userNameEditText = findViewById(R.id.user_name_edit_text);
        emailEditText = findViewById(R.id.email_id_edit_text);
        phoneEditText = findViewById(R.id.phone_number_edit_text);
        updateProfileButton = findViewById(R.id.update_profile_button);
        backButton = findViewById(R.id.back_button);

        // Load user data from DB
        loadUserData(currentUserId);

        // Update profile logic
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedUsername = userNameEditText.getText().toString().trim();
                String updatedEmail = emailEditText.getText().toString().trim();
                String updatedPhone = phoneEditText.getText().toString().trim();

                if (updatedUsername.isEmpty() || updatedEmail.isEmpty() || updatedPhone.isEmpty()) {
                    Toast.makeText(EditProfile.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isUpdated = dbHelper.updateUserProfile(currentUserId, updatedUsername, updatedEmail, updatedPhone);

                if (isUpdated) {
                    Toast.makeText(EditProfile.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close activity
                } else {
                    Toast.makeText(EditProfile.this, "Email or Phone already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Back button
        backButton.setOnClickListener(v -> finish());
    }

    private void loadUserData(String userId) {
        Cursor cursor = dbHelper.getUserData(userId);

        if (cursor != null && cursor.moveToFirst()) {
            String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));

            userIdEditText.setText(userId);
            userNameEditText.setText(username);
            emailEditText.setText(email);
            phoneEditText.setText(phone);

            cursor.close();
        }
    }
}
