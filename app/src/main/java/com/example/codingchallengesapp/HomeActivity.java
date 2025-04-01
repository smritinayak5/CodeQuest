package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up menu button to open MenuActivity
        ImageButton menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        // Open Settings Activity when Settings Icon is Clicked
        ImageView settingIcon = findViewById(R.id.nav_settings);  // Icon with id 'nav_tasks'
        settingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        // Set up the test icon (nav_tasks) to open the AllChallengesActivity
        ImageView testIcon = findViewById(R.id.nav_tasks);  // Icon with id 'nav_tasks'
        testIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open All Challenges Test Page
                Intent intent = new Intent(HomeActivity.this, AllChallengesActivity.class);
                startActivity(intent);
            }
        });

        // Set up the notification icon (nav_notification) to open the Notification
        ImageView notificationIcon = findViewById(R.id.nav_notifications);  // Icon with id 'nav_notification'
        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open All Notification Page
                Intent intent = new Intent(HomeActivity.this, Notification.class);
                startActivity(intent);
            }
        });

        // Set up the profile icon (nav_profile") to open the AllChallengesActivity
        ImageView profileIcon = findViewById(R.id.nav_profile);  // Icon with id 'nav_profile'
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Edit Profile Page
                Intent intent = new Intent(HomeActivity.this, EditProfile.class);
                startActivity(intent);
            }
        });

        // Handle Java Challenge Card click to open All Java Page
        CardView javaChallengeCard = findViewById(R.id.java_challenge);
        javaChallengeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open JavaAll Activity when the card is clicked
                Intent intent = new Intent(HomeActivity.this, LevelJava.class);
                startActivity(intent);
            }
        });

        // Handle C++ Challenge Card click to open All Level Page
        CardView cppChallengeCard = findViewById(R.id.cpp_challenge);
        cppChallengeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open AllLevel Activity when the card is clicked
                Intent intent = new Intent(HomeActivity.this, LevelCpp.class);
                startActivity(intent);
            }
        });

        // Handle Python Challenge Card click to open All Level Page
        CardView pythonChallengeCard = findViewById(R.id.python_challenge_card);
        pythonChallengeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open AllLevel Activity when the card is clicked
                Intent intent = new Intent(HomeActivity.this, LevelPython.class);
                startActivity(intent);
            }
        });
    }
}
