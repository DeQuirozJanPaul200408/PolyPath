package com.example.polypath;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class ChinaScoreResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_china_score_results);

        int correctAnswersCount = getIntent().getIntExtra("correctAnswersCount", 0);
        String[] userAnswers = getIntent().getStringArrayExtra("userAnswers");

        Log.d("ItalyScoreResults", "User Answers: " + java.util.Arrays.toString(userAnswers));

        // Correct answers for the quiz
        String[] correctAnswers = {
                "You're welcome",
                "Good morning",
                "Good evening",
                "I'm evening",
                "It doesn't matter"
        };

        TextView scoreText = findViewById(R.id.scoreText);
        scoreText.setText("You got " + correctAnswersCount + " out of 5 correct!");

        TextView answersText = findViewById(R.id.answersText);
        answersText.setText(""); // Clear any existing text

        SpannableStringBuilder builder = new SpannableStringBuilder();

        for (int i = 0; i < correctAnswers.length; i++) {
            String correct = correctAnswers[i];

            SpannableString question = new SpannableString((i + 1) + ". ");
            question.setSpan(new ForegroundColorSpan(0xFF000000), 0, question.length(), 0);

            SpannableString correctAnswer = new SpannableString("Correct answer: " + correct + "\n\n");
            correctAnswer.setSpan(new ForegroundColorSpan(0xFF000000), 0, correctAnswer.length(), 0);

            builder.append(question).append(correctAnswer);
        }

        answersText.setText(builder);

        // Add Return to Home Page button functionality
        Button returnButton = findViewById(R.id.button3);
        returnButton.setOnClickListener(v -> {
            Intent intent = new Intent(ChinaScoreResults.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Close this activity
        });
    }
}
