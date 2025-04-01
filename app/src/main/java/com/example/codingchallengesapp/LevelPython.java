package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LevelPython extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.python_level);

        // Set up difficulty level click listeners
        TextView basicTab = findViewById(R.id.basic_tab);
        TextView moderateTab = findViewById(R.id.moderate_tab);
        TextView advanceTab = findViewById(R.id.advance_tab);

        basicTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Basic difficulty selection and navigate to BasicPython
                Intent intent = new Intent(LevelPython.this, BasicPython.class);
                startActivity(intent);
            }
        });

        moderateTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Moderate difficulty selection and navigate to BasicPython
                Intent intent = new Intent(LevelPython.this, ModeratePython.class);
                startActivity(intent);
            }
        });

        advanceTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Advance difficulty selection and navigate to BasicPython
                Intent intent = new Intent(LevelPython.this, AdvancePython.class);
                startActivity(intent);
            }
        });
    }
}
