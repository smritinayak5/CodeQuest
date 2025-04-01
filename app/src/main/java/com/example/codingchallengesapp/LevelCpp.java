package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LevelCpp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpp_level);

        // Set up difficulty level click listeners
        TextView basicTab = findViewById(R.id.basic_tab);
        TextView moderateTab = findViewById(R.id.moderate_tab);
        TextView advanceTab = findViewById(R.id.advance_tab);

        basicTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Basic difficulty selection and navigate to BasicCpp
                Intent intent = new Intent(LevelCpp.this, BasicCpp.class);
                startActivity(intent);
            }
        });

        moderateTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Moderate difficulty selection and navigate to ModerateCpp
                Intent intent = new Intent(LevelCpp.this, ModerateCpp.class);
                startActivity(intent);
            }
        });

        advanceTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Advance difficulty selection and navigate to AdvanceCpp
                Intent intent = new Intent(LevelCpp.this, AdvanceCpp.class);
                startActivity(intent);
            }
        });
    }
}
