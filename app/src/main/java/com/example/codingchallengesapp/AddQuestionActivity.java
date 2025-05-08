package com.example.codingchallengesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.*;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText titleEditText, questionEditText, testNumberEditText;
    private Spinner difficultySpinner;
    private EditText languageEditText, inputEditText, outputEditText;
    private Button addLanguageButton, addTestCaseButton, addQuestionButton;

    private LinearLayout languageListLayout, testCaseListLayout;
    private FirebaseFirestore db;

    private List<String> languages = new ArrayList<>();
    private List<Map<String, String>> testCases = new ArrayList<>();
    private Map<String, String> correctCode = new HashMap<>(); // You can expand this with UI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        testNumberEditText = findViewById(R.id.testNumberEditText);
        titleEditText = findViewById(R.id.titleEditText);
        questionEditText = findViewById(R.id.questionEditText);
        difficultySpinner = findViewById(R.id.difficultySpinner);
        languageEditText = findViewById(R.id.languageEditText);
        inputEditText = findViewById(R.id.inputEditText);
        outputEditText = findViewById(R.id.outputEditText);
        addLanguageButton = findViewById(R.id.addLanguageButton);
        addTestCaseButton = findViewById(R.id.addTestCaseButton);
        addQuestionButton = findViewById(R.id.addQuestionButton);

        languageListLayout = findViewById(R.id.languageListLayout);
        testCaseListLayout = findViewById(R.id.testCaseListLayout);

        db = FirebaseFirestore.getInstance();

        // Setup spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.difficulty_levels, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        addLanguageButton.setOnClickListener(v -> {
            String lang = languageEditText.getText().toString().trim();
            if (!lang.isEmpty() && !languages.contains(lang)) {
                languages.add(lang);
                TextView tv = new TextView(this);
                tv.setText(lang);
                languageListLayout.addView(tv);
                languageEditText.setText("");
            }
        });

        addTestCaseButton.setOnClickListener(v -> {
            String input = inputEditText.getText().toString().trim();
            String output = outputEditText.getText().toString().trim();
            if (!input.isEmpty() && !output.isEmpty()) {
                Map<String, String> testCase = new HashMap<>();
                testCase.put("input", input);
                testCase.put("output", output);
                testCases.add(testCase);

                TextView tv = new TextView(this);
                tv.setText("Input: " + input + " -> Output: " + output);
                testCaseListLayout.addView(tv);
                inputEditText.setText("");
                outputEditText.setText("");
            }
        });

        addQuestionButton.setOnClickListener(v -> {
            String testNumberStr = testNumberEditText.getText().toString().trim();
            String title = titleEditText.getText().toString().trim();
            String question = questionEditText.getText().toString().trim();
            String difficulty = difficultySpinner.getSelectedItem().toString();

            if (testNumberStr.isEmpty() || title.isEmpty() || question.isEmpty() || languages.isEmpty() || testCases.isEmpty()) {
                Toast.makeText(this, "Fill all fields including test number, and add at least one language and test case", Toast.LENGTH_SHORT).show();
                return;
            }

            int testNumber;
            try {
                testNumber = Integer.parseInt(testNumberStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Test number must be a valid number", Toast.LENGTH_SHORT).show();
                return;
            }

            String questionId = UUID.randomUUID().toString(); // unique ID

            Map<String, Object> questionData = new HashMap<>();
            questionData.put("question_id", questionId);
            questionData.put("test_number", testNumber);
            questionData.put("title", title);
            questionData.put("question", question);
            questionData.put("difficulty", difficulty);
            questionData.put("language", languages);
            questionData.put("test_cases", testCases);
            questionData.put("correct_code", correctCode); // You can enhance this with editor inputs

            db.collection("questions").document(questionId)
                    .set(questionData)
                    .addOnSuccessListener(unused ->
                            Toast.makeText(this, "Question added!", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e ->
                            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }
}
