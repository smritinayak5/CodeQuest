package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MenuActivity extends AppCompatActivity {

    private TextView profileName, profileEmail;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Views
        profileName = findViewById(R.id.profile_name);
        profileEmail = findViewById(R.id.profile_email);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Get current user
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            profileEmail.setText(currentUser.getEmail());

            // Optionally get name from Firestore
            String uid = currentUser.getUid();
            db.collection("users").document(uid).get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String name = documentSnapshot.getString("name");  // Assuming you store name as "name"
                    profileName.setText(name != null ? name : "User");
                }
            }).addOnFailureListener(e -> {
                profileName.setText("User");
            });
        }

        // Menu actions
        LinearLayout homeLayout = findViewById(R.id.home_layout);
        LinearLayout accountLayout = findViewById(R.id.account_layout);
        LinearLayout logoutLayout = findViewById(R.id.logout_layout);

        homeLayout.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        accountLayout.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, EditProfile.class);
            startActivity(intent);
        });

        logoutLayout.setOnClickListener(view -> {
            mAuth.signOut();  // Sign out the user
            Intent intent = new Intent(MenuActivity.this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
