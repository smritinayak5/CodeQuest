package com.example.codingchallengesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        // Initialize views
        ImageView backButton = findViewById(R.id.backButton);
        CardView pythonChallengeCard = findViewById(R.id.pythonChallengeCard);
        CardView javaChallengeCard = findViewById(R.id.javaChallengeCard);
        CardView cppChallengeCard = findViewById(R.id.cppChallengeCard);

        // Set click listeners for interactive elements
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to previous screen
                onBackPressed();
            }
        });

        // Set click listeners for challenge cards
        pythonChallengeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Notification.this, "Python Challenge Details", Toast.LENGTH_SHORT).show();
                // Navigate to challenge details or perform action
            }
        });

        javaChallengeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Notification.this, "Java Challenge Details", Toast.LENGTH_SHORT).show();
                // Navigate to challenge details or perform action
            }
        });

        cppChallengeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Notification.this, "C++ Challenge Details", Toast.LENGTH_SHORT).show();
                // Navigate to challenge details or perform action
            }
        });
    }
}