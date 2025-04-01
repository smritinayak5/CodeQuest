package com.example.codingchallengesapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PremiumActivity extends AppCompatActivity {

    private static final int UPI_PAYMENT_REQUEST = 123;
    private String upiId = "yourupiid@okicici"; // Replace with your actual UPI ID
    private String payeeName = "Coding Challenges App";
    private String transactionNote = "Premium Subscription";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);

        // Back button functionality
        findViewById(R.id.back_button).setOnClickListener(view -> finish());

        // Monthly Payment Button
        Button monthlyPaymentButton = findViewById(R.id.btn_monthly_payment);
        monthlyPaymentButton.setOnClickListener(v -> makeGooglePayPayment("1200")); // ₹1200 for Monthly

        // Yearly Payment Button
        Button yearlyPaymentButton = findViewById(R.id.btn_yearly_payment);
        yearlyPaymentButton.setOnClickListener(v -> makeGooglePayPayment("10000")); // ₹10,000 for Yearly
    }

    private void makeGooglePayPayment(String amount) {
        Uri uri = Uri.parse("upi://pay")
                .buildUpon()
                .appendQueryParameter("pa", upiId) // Payee UPI ID
                .appendQueryParameter("pn", payeeName) // Payee Name
                .appendQueryParameter("tn", transactionNote) // Transaction Note
                .appendQueryParameter("am", amount) // Amount (Dynamic)
                .appendQueryParameter("cu", "INR") // Currency
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage("com.google.android.apps.nbu.paisa.user"); // Google Pay Package

        try {
            startActivityForResult(intent, UPI_PAYMENT_REQUEST);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Google Pay not installed!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPI_PAYMENT_REQUEST) {
            if (data != null) {
                String response = data.getStringExtra("response");
                if (response != null && response.contains("SUCCESS")) {
                    Toast.makeText(this, "Payment Successful!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Payment Failed! Try Again.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Payment Canceled!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
