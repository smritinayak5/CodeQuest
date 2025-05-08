package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ModerateCpp extends AppCompatActivity implements View.OnClickListener {

    private CardView test1, test2, test3, test4, test5;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cpp_moderate);  // Layout file with 5 test cards

        initViews();
        setClickListeners();

        db = FirebaseFirestore.getInstance(); // Initialize Firestore
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
        int testNumber = -1;

        if (view.getId() == R.id.test1) testNumber = 1;
        else if (view.getId() == R.id.test2) testNumber = 2;
        else if (view.getId() == R.id.test3) testNumber = 3;
        else if (view.getId() == R.id.test4) testNumber = 4;
        else if (view.getId() == R.id.test5) testNumber = 5;

        if (testNumber != -1) {
            fetchQuestion(testNumber);
        }
    }

    private void fetchQuestion(int testNumber) {
        Toast.makeText(this, "Loading Test " + testNumber, Toast.LENGTH_SHORT).show();

        db.collection("questions")
                .whereEqualTo("difficulty", "Moderate")
                .whereEqualTo("test_number", testNumber)
                .whereArrayContains("language", "C++")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        DocumentSnapshot doc = queryDocumentSnapshots.getDocuments().get(0);
                        String title = doc.getString("title");
                        String question = doc.getString("question");

                        Intent intent = new Intent(ModerateCpp.this, TestLive.class);
                        intent.putExtra("title", title);
                        intent.putExtra("question", question);
                        intent.putExtra("language", "C++"); // Pass language to TestLive
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "No question found for Test " + testNumber, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(ModerateCpp.this, "Error loading question: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
    }
}
