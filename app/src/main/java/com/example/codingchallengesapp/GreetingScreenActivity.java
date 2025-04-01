package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class GreetingScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_greeting);

        // Get Started button to navigate to HomeActivity
        Button getStartedButton = findViewById(R.id.btnGetStarted);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GreetingScreenActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Prevent going back to the greeting screen
            }
        });

        // Edit Profile button to navigate to EditProfile activity
        Button editProfileButton = findViewById(R.id.btnEditProfile);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GreetingScreenActivity.this, EditProfile.class);
                startActivity(intent); // Open the Edit Profile screen
            }
        });
    }
}
