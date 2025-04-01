package com.example.codingchallengesapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Back button functionality
        findViewById(R.id.back_button).setOnClickListener(view -> finish());
    }
}
