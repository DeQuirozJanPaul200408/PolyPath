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

public class KoreanScoreResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korean_score_results);

        int correctAnswersCount = getIntent().getIntExtra("correctAnswersCount", 0);
        String[] userAnswers = getIntent().getStringArrayExtra("userAnswers");

        Log.d("KoreanScoreResults", "User Answers: " + java.util.Arrays.toString(userAnswers));

        // Correct answers for the quiz
        String[] correctAnswers = {
                "Hello",
                "I love you",
                "Thank you",
                "Illusion",
                "Sorry"
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

        // Return to Home Page button functionality
        Button returnButton = findViewById(R.id.button3);
        returnButton.setOnClickListener(v -> {
            Intent intent = new Intent(KoreanScoreResults.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Close this activity
        });
    }
}
