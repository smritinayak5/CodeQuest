package com.example.codingchallengesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;  // Import Intent
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestLive extends AppCompatActivity {

    private EditText codeEditText;
    private Button exitButton;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_test);

        // Initialize views
        codeEditText = findViewById(R.id.codeEditText);
        exitButton = findViewById(R.id.exitButton);
        submitButton = findViewById(R.id.submitButton);

        // Set the initial code text
        codeEditText.setText("import java.util.Scanner;\n\n" +
                "public class EvenOddCheck {\n" +
                "    public static void main(String[] args) {\n" +
                "        Scanner scanner = new Scanner(System.in);\n" +
                "        System.out.print(\"Enter a number: \");\n" +
                "        int num = scanner.nextInt();\n" +
                "        \n" +
                "        if (num % 2 == 0) {\n" +
                "            System.out.println(num + \" is Even.\");\n" +
                "        } else {\n" +
                "            System.out.println(num + \" is Odd.\");\n" +
                "        }\n" +
                "        \n" +
                "        scanner.close();\n" +
                "    }\n" +
                "}");


        // Set up button click listeners
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the application
                finish();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle test submission
                String code = codeEditText.getText().toString();
                if (!code.isEmpty()) {
                    Toast.makeText(TestLive.this, "Test submitted successfully!", Toast.LENGTH_SHORT).show();

                    // Launch the Submission activity
                    Intent intent = new Intent(TestLive.this, Submission.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(TestLive.this, "Please write some code before submitting", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
