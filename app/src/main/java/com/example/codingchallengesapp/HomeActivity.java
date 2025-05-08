package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.*;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private TextView greetingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        greetingText = findViewById(R.id.greeting_text); // ID of the TextView saying "Hi Alex"

        // Set user greeting
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            db.collection("users").document(uid).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String name = documentSnapshot.getString("name");
                            greetingText.setText("Hi " + name);
                        } else {
                            greetingText.setText("Hi there");
                        }
                    })
                    .addOnFailureListener(e -> greetingText.setText("Hi User"));
        } else {
            greetingText.setText("Hi Guest");
        }

        // Menu
        ImageButton menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MenuActivity.class);
            startActivity(intent);
        });

        // Settings
        ImageView settingIcon = findViewById(R.id.nav_settings);
        settingIcon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        // Tasks / All Challenges
        ImageView testIcon = findViewById(R.id.nav_tasks);
        testIcon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AllChallengesActivity.class);
            startActivity(intent);
        });

        // Notifications
        ImageView notificationIcon = findViewById(R.id.nav_notifications);
        notificationIcon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, Notification.class);
            startActivity(intent);
        });

        // Profile
        ImageView profileIcon = findViewById(R.id.nav_profile);
        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, EditProfile.class);
            startActivity(intent);
        });

        // Java Card
        CardView javaChallengeCard = findViewById(R.id.java_challenge);
        javaChallengeCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LevelJava.class);
            startActivity(intent);
        });

        // C++ Card
        CardView cppChallengeCard = findViewById(R.id.cpp_challenge);
        cppChallengeCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LevelCpp.class);
            startActivity(intent);
        });

        // Python Card
        CardView pythonChallengeCard = findViewById(R.id.python_challenge_card);
        pythonChallengeCard.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LevelPython.class);
            startActivity(intent);
        });
    }
}
