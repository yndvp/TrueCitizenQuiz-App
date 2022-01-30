package com.example.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.truecitizenquiz.databinding.ActivityMainBinding;
import com.example.truecitizenquiz.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[]{
            // Create(instantiate) question objects
            new Question(R.string.question_amendments, false),
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.textViewQuestion.setText(questionBank[currentQuestionIndex].getAnswerResId());
        
        binding.btnNext.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
            updateQuestion();
        });

        binding.btnPrev.setOnClickListener(view -> {
            if(currentQuestionIndex > 0) {
                currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                updateQuestion();
            }
        });

        binding.btnTrue.setOnClickListener(view -> {
            checkAnswer(true);
        });

        binding.btnFalse.setOnClickListener(view -> {
            checkAnswer(false);
        });
    }

    private void checkAnswer(boolean userAnswer) {
        boolean answer = questionBank[currentQuestionIndex].isAnswerTrue();
        int messageId;

        if(userAnswer == answer) {
            messageId = R.string.correct_answer;
        } else {
            messageId = R.string.wrong_answer;
        }

        Snackbar.make(binding.imageView2, messageId, Snackbar.LENGTH_SHORT)
        .show();
    }

    private void updateQuestion() {
        binding.textViewQuestion.setText(questionBank[currentQuestionIndex].getAnswerResId());

    }
}