package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;  // Import for CardView

public class AllChallengesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_challenges);

        // Handle back button click
        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to the previous screen
                finish();
            }
        });

        // Handle Java Challenge Card click to open Java Level Page
        CardView javaChallengeCard = findViewById(R.id.java_challenge);
        javaChallengeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open JavaLevel when the card is clicked
                Intent intent = new Intent(AllChallengesActivity.this, LevelJava.class);
                startActivity(intent);
            }
        });

        // Handle C++ Challenge Card click to open C++ Level Page
        CardView cppChallengeCard = findViewById(R.id.cpp_challenge);
        cppChallengeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open JavaLevel when the card is clicked
                Intent intent = new Intent(AllChallengesActivity.this, LevelCpp.class);
                startActivity(intent);
            }
        });

        // Handle Python Challenge Card click to open Python Level Page
        CardView pythonChallengeCard = findViewById(R.id.python_challenge_card);
        pythonChallengeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open PythonLevel when the card is clicked
                Intent intent = new Intent(AllChallengesActivity.this, LevelPython.class);
                startActivity(intent);
            }
        });
    }
}
