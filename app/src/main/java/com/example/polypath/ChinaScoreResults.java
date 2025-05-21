package com.example.polypath;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class ChinaScoreResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_china_score_results);

        // Retrieve the score and user answers
        int correctAnswersCount = getIntent().getIntExtra("correctAnswersCount", 0);
        String[] userAnswers = getIntent().getStringArrayExtra("userAnswers");

        // Log the user answers for debugging purposes
        Log.d("ChinaScoreResults", "User Answers: " + java.util.Arrays.toString(userAnswers));

        // Correct answers for the quiz
        String[] correctAnswers = {
                "You're welcome",
                "Good Morning",
                "Good Evening",
                "I'm sorry",
                "It doesn't matter"
        };

        // Display the score
        TextView scoreText = findViewById(R.id.scoreText);
        scoreText.setText("You got " + correctAnswersCount + " out of 5 correct!");

        // Set up the answers display
        TextView answersText = findViewById(R.id.answersText);
        answersText.setText(""); // Clear any existing text

        // Create a SpannableStringBuilder to build the formatted answers
        SpannableStringBuilder builder = new SpannableStringBuilder();

        // Loop through each question and display the correct answer
        for (int i = 0; i < correctAnswers.length; i++) {
            String correct = correctAnswers[i];

            // Append question number
            SpannableString question = new SpannableString((i + 1) + ". ");
            question.setSpan(new ForegroundColorSpan(0xFF000000), 0, question.length(), 0); // Default black color

            // Append "Correct answer:"
            SpannableString correctAnswer = new SpannableString("Correct answer: " + correct + "\n\n");
            correctAnswer.setSpan(new ForegroundColorSpan(0xFF000000), 0, correctAnswer.length(), 0); // Default black color

            // Add each part to the builder
            builder.append(question).append(correctAnswer);
        }

        // Set the final text in the TextView
        answersText.setText(builder);
    }
}