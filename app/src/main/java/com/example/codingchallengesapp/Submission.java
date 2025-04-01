package com.example.codingchallengesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;  // Add this import for navigation
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Submission extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submission);

        // Initialize buttons
        Button btnRetest = findViewById(R.id.btnRetest);
        Button btnBackHome = findViewById(R.id.btnBackHome);

        // Set click listeners
        btnRetest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Submission.this, "Applying for re-test...", Toast.LENGTH_SHORT).show();
                // Add your re-test functionality here
            }
        });

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Submission.this, "Going back to home...", Toast.LENGTH_SHORT).show();

                // Navigate to HomeActivity
                Intent intent = new Intent(Submission.this, HomeActivity.class);
                startActivity(intent);
                finish();  // Close the Submission activity to avoid stacking it
            }
        });
    }
}
