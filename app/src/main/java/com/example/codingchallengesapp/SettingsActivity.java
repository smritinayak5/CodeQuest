package com.example.codingchallengesapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Back button to go to MainActivity
        findViewById(R.id.back_button).setOnClickListener(view -> finish());

        // Click Listeners for settings options
        findViewById(R.id.help).setOnClickListener(view -> {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.notification).setOnClickListener(view -> {
            Intent intent = new Intent(this, Notification.class);
            startActivity(intent);
        });

        findViewById(R.id.terms).setOnClickListener(view -> {
            Intent intent = new Intent(this, TermsActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.privacy).setOnClickListener(view -> {
            Intent intent = new Intent(this, PrivacyPolicyActivity.class);
            startActivity(intent);
        });

        // Delete Account Pop-up Confirmation
        findViewById(R.id.delete_account).setOnClickListener(view -> showDeleteAccountDialog());

        findViewById(R.id.faqs).setOnClickListener(view -> {
            Intent intent = new Intent(this, FAQActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.premium).setOnClickListener(view -> {
            Intent intent = new Intent(this, PremiumActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.progress_report).setOnClickListener(view -> {
            Intent intent = new Intent(this, ProgressReportActivity.class);
            startActivity(intent);
        });
    }

    // Method to show a confirmation pop-up when "Delete Account" is clicked
    private void showDeleteAccountDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Account");
        builder.setMessage("Are you sure you want to delete your account? This action cannot be undone.");

        builder.setPositiveButton("Yes, Delete", (dialog, which) -> {
            // Code to handle account deletion
            dialog.dismiss();
            showSuccessMessage();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Show a success message after deletion and redirect to WelcomeActivity
    private void showSuccessMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Account Deleted");
        builder.setMessage("Your account has been deleted successfully.");
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
            navigateToWelcome();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Redirect to the WelcomeActivity
    private void navigateToWelcome() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clears back stack
        startActivity(intent);
        finish(); // Close the settings page
    }

    private void openPage(String title) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("PAGE_TITLE", title);
        startActivity(intent);
    }
}
