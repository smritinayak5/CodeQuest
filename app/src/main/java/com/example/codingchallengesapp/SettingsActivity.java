package com.example.codingchallengesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SettingsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        findViewById(R.id.back_button).setOnClickListener(view -> finish());

        findViewById(R.id.help).setOnClickListener(view -> startActivity(new Intent(this, HelpActivity.class)));
        findViewById(R.id.notification).setOnClickListener(view -> startActivity(new Intent(this, Notification.class)));
        findViewById(R.id.terms).setOnClickListener(view -> startActivity(new Intent(this, TermsActivity.class)));
        findViewById(R.id.privacy).setOnClickListener(view -> startActivity(new Intent(this, PrivacyPolicyActivity.class)));
        findViewById(R.id.faqs).setOnClickListener(view -> startActivity(new Intent(this, FAQActivity.class)));
        findViewById(R.id.premium).setOnClickListener(view -> startActivity(new Intent(this, PremiumActivity.class)));
        findViewById(R.id.progress_report).setOnClickListener(view -> startActivity(new Intent(this, ProgressReportActivity.class)));
        findViewById(R.id.questions_add).setOnClickListener(view -> startActivity(new Intent(this, AddQuestionActivity.class)));

        // Handle Delete Account click
        findViewById(R.id.delete_account).setOnClickListener(view -> showDeleteAccountDialog());
    }

    // Confirmation dialog
    private void showDeleteAccountDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Account")
                .setMessage("Are you sure you want to delete your account? This action cannot be undone.")
                .setPositiveButton("Yes, Delete", (dialog, which) -> deleteAccountAndData())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    // Delete account from Auth and Firestore
    private void deleteAccountAndData() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();

            // Step 1: Delete user data from Firestore
            db.collection("users").document(uid).delete()
                    .addOnSuccessListener(aVoid -> {
                        // Step 2: Delete user from Firebase Auth
                        user.delete()
                                .addOnSuccessListener(aVoid2 -> showSuccessMessage())
                                .addOnFailureListener(e -> {
                                    Toast.makeText(this, "Failed to delete user account.", Toast.LENGTH_SHORT).show();
                                });
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Failed to delete user data.", Toast.LENGTH_SHORT).show();
                    });
        }
    }

    private void showSuccessMessage() {
        new AlertDialog.Builder(this)
                .setTitle("Account Deleted")
                .setMessage("Your account has been deleted successfully.")
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    navigateToWelcome();
                })
                .show();
    }

    private void navigateToWelcome() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
