package com.example.codingchallengesapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class TermsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        // Back button functionality
        findViewById(R.id.back_button).setOnClickListener(view -> finish());
    }
}
