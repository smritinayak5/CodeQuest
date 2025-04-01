package com.example.codingchallengesapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Get UI elements
        TextView titleText = findViewById(R.id.title_text);
        TextView contentText = findViewById(R.id.content_text);
        ImageView backButton = findViewById(R.id.back_button);

        // Get the title passed from SettingsActivity
        String title = getIntent().getStringExtra("PAGE_TITLE");

        if (title != null) {
            titleText.setText(title);

            // Set content based on title
            switch (title) {
                case "Help Page":
                    contentText.setText("This is the Help Page. Here you can get assistance.");
                    break;
                case "Notification Settings":
                    contentText.setText("Manage your notifications here.");
                    break;
                case "Terms & Conditions":
                    contentText.setText("These are the terms and conditions of our service.");
                    break;
                case "Privacy Policy":
                    contentText.setText("Here is our privacy policy.");
                    break;
                case "Delete Account":
                    contentText.setText("Follow the steps to delete your account.");
                    break;
                case "Premium Subscription":
                    contentText.setText("Learn about our premium features.");
                    break;
                case "Progress Report":
                    contentText.setText("View your progress report.");
                    break;
                default:
                    contentText.setText("No additional information available.");
                    break;
            }
        }

        // Back button click listener
        backButton.setOnClickListener(view -> finish());
    }
}
