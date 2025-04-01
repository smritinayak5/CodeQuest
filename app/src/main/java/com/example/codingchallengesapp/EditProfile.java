package com.example.codingchallengesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {

    private ImageButton backButton;
    private ImageView profileImageView;
    private EditText userIdEditText, userNameEditText, emailIdEditText, phoneNumberEditText;
    private Button updateProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Add this in your onCreate method
//        getWindow().setStatusBarColor(getResources().getColor(R.color.purple, null));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);

        // Initialize views
        backButton = findViewById(R.id.back_button);
        profileImageView = findViewById(R.id.profileImage);
        userIdEditText = findViewById(R.id.user_id_edit_text);
        userNameEditText = findViewById(R.id.user_name_edit_text);
        emailIdEditText = findViewById(R.id.email_id_edit_text);
        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        updateProfileButton = findViewById(R.id.update_profile_button);

        // Set sample data
        userIdEditText.setText("alex123");
        userNameEditText.setText("Alex");
        emailIdEditText.setText("alex@example.com");
        phoneNumberEditText.setText("5551234567");

        // Set click listeners
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to previous screen
            }
        });

        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here you would typically save the profile data
                Toast.makeText(EditProfile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
            }
        });

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here you would typically open image selection
                Toast.makeText(EditProfile.this, "Select Profile Picture", Toast.LENGTH_SHORT).show();
            }
        });
    }
}