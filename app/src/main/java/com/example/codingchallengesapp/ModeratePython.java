package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ModeratePython extends AppCompatActivity implements View.OnClickListener {

    private CardView test1, test2, test3, test4, test5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.python_moderate);  // Correct layout file
        initViews();
        setClickListeners();
    }


    private void initViews() {
        test1 = findViewById(R.id.test1);
        test2 = findViewById(R.id.test2);
        test3 = findViewById(R.id.test3);
        test4 = findViewById(R.id.test4);
        test5 = findViewById(R.id.test5);
    }

    private void setClickListeners() {
        test1.setOnClickListener(this);
        test2.setOnClickListener(this);
        test3.setOnClickListener(this);
        test4.setOnClickListener(this);
        test5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.test1) {
            showToast("Starting Test 1");
            // Launch the TestLive Activity for Test 1
            Intent intent = new Intent(ModeratePython.this, TestLive.class);
            startActivity(intent);
        } else if (id == R.id.test2) {
            showToast("Starting Test 2");
        } else if (id == R.id.test3) {
            showToast("Starting Test 3");
        } else if (id == R.id.test4) {
            showToast("Starting Test 4");
        } else if (id == R.id.test5) {
            showToast("Starting Test 5");
        }
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}