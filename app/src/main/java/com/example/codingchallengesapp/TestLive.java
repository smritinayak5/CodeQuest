package com.example.codingchallengesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;

import java.util.*;

public class TestLive extends AppCompatActivity {

    private EditText codeEditText;
    private Button exitButton, submitButton;
    private TextView titleTextView, descriptionTextView, languageTitle;

    private String title, question, language;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_test);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        // Initialize views
        titleTextView = findViewById(R.id.questionTitleTextView);
        descriptionTextView = findViewById(R.id.questionDescriptionTextView);
        codeEditText = findViewById(R.id.codeEditText);
        exitButton = findViewById(R.id.exitButton);
        submitButton = findViewById(R.id.submitButton);
        languageTitle = findViewById(R.id.languageTitleTextView); // Add this TextView to your XML

        // Retrieve data from intent
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        question = intent.getStringExtra("question");
        language = intent.getStringExtra("language");

        titleTextView.setText(title != null ? title : "No Title Available");
        descriptionTextView.setText(question != null ? question : "No Question Available");

        setCodeTemplate(language);

        exitButton.setOnClickListener(v -> finish());

        submitButton.setOnClickListener(v -> {
            String code = codeEditText.getText().toString().trim();
            if (code.isEmpty()) {
                Toast.makeText(TestLive.this, "Please write some code before submitting.", Toast.LENGTH_SHORT).show();
            } else {
                saveSubmission(code);
            }
        });
    }

    private void setCodeTemplate(String lang) {
        if (lang == null) lang = "Java"; // Default

        languageTitle.setText(lang + " Program");

        switch (lang) {
            case "Python":
                codeEditText.setText(
                        "def main():\n" +
                                "    # Your code here\n" +
                                "\n" +
                                "if __name__ == '__main__':\n" +
                                "    main()"
                );
                break;
            case "C++":
                codeEditText.setText(
                        "#include <iostream>\n" +
                                "using namespace std;\n\n" +
                                "int main() {\n" +
                                "    // Your code here\n" +
                                "    return 0;\n" +
                                "}"
                );
                break;
            case "Java":
            default:
                codeEditText.setText(
                        "import java.util.Scanner;\n\n" +
                                "public class Main {\n" +
                                "    public static void main(String[] args) {\n" +
                                "        Scanner scanner = new Scanner(System.in);\n" +
                                "        // Your code here\n" +
                                "        scanner.close();\n" +
                                "    }\n" +
                                "}"
                );
                break;
        }
    }

    private void saveSubmission(String code) {
        String userEmail = auth.getCurrentUser().getEmail();

        db.collection("users")
                .whereEqualTo("email", userEmail)
                .get()
                .addOnSuccessListener(userQuery -> {
                    if (!userQuery.isEmpty()) {
                        String userId = userQuery.getDocuments().get(0).getString("user_id");

                        db.collection("questions")
                                .whereEqualTo("title", title)
                                .get()
                                .addOnSuccessListener(questionQuery -> {
                                    if (!questionQuery.isEmpty()) {
                                        String questionId = questionQuery.getDocuments().get(0).getString("question_id");

                                        String submissionId = UUID.randomUUID().toString();

                                        Map<String, Object> submissionData = new HashMap<>();
                                        submissionData.put("submission_id", submissionId);
                                        submissionData.put("user_id", userId);
                                        submissionData.put("question_id", questionId);
                                        submissionData.put("language", language);
                                        submissionData.put("code", code);
                                        submissionData.put("score", 0); // You can add logic later
                                        submissionData.put("status", "Success"); // For now, always Success
                                        submissionData.put("submitted_at", Timestamp.now());

                                        db.collection("submissions").document(submissionId)
                                                .set(submissionData)
                                                .addOnSuccessListener(unused -> {
                                                    Toast.makeText(this, "Submission successful!", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(TestLive.this, Submission.class);
                                                    startActivity(intent);
                                                    finish();
                                                })
                                                .addOnFailureListener(e ->
                                                        Toast.makeText(this, "Failed to save submission: " + e.getMessage(), Toast.LENGTH_LONG).show());
                                    } else {
                                        Toast.makeText(this, "Question not found!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(e ->
                                        Toast.makeText(this, "Error retrieving question: " + e.getMessage(), Toast.LENGTH_LONG).show());
                    } else {
                        Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error retrieving user: " + e.getMessage(), Toast.LENGTH_LONG).show());
    }
}
