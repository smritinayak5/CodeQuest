package com.example.codingchallengesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    private EditText newPasswordEditText;
    private EditText confirmNewPasswordEditText;
    private Button changePasswordButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_change); // Corrected XML filename

        // Initialize UI components
        newPasswordEditText = findViewById(R.id.new_password);
        confirmNewPasswordEditText = findViewById(R.id.confirm_new_password);
        changePasswordButton = findViewById(R.id.change_password_button);
        backButton = findViewById(R.id.back_button);

        // Set click listener for the Change Password button
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get password values
                String newPassword = newPasswordEditText.getText().toString();
                String confirmNewPassword = confirmNewPasswordEditText.getText().toString();

                // Validate passwords
                if (newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
                    Toast.makeText(ChangePassword.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!newPassword.equals(confirmNewPassword)) {
                    Toast.makeText(ChangePassword.this, "New passwords don't match", Toast.LENGTH_SHORT).show();
                } else {
                    // Passwords match, handle password change logic here
                    // For this example, just show a success message
                    Toast.makeText(ChangePassword.this, "Password changed successfully", Toast.LENGTH_SHORT).show();

                    // Clear fields
                    newPasswordEditText.setText("");
                    confirmNewPasswordEditText.setText("");
                }
            }
        });

        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to the previous screen
                onBackPressed();
            }
        });
    }
}
