package com.example.wordwise;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProgressActivity extends AppCompatActivity {

    TextView tvTotalWords, tvLearnedWords, tvRemainingWords, tvProgressPercentage;
    ProgressBar progressBar;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        tvTotalWords = findViewById(R.id.tvTotalWords);
        tvLearnedWords = findViewById(R.id.tvLearnedWords);
        tvRemainingWords = findViewById(R.id.tvRemainingWords);
        tvProgressPercentage = findViewById(R.id.tvProgressPercentage);
        progressBar = findViewById(R.id.progressBar);

        databaseHelper = new DatabaseHelper(this);

        loadProgress();
    }

    private void loadProgress() {
        int totalWords = databaseHelper.getTotalWordsCount();
        int learnedWords = databaseHelper.getLearnedWordsCount();
        int remainingWords = totalWords - learnedWords;

        tvTotalWords.setText(String.valueOf(totalWords));
        tvLearnedWords.setText(String.valueOf(learnedWords));
        tvRemainingWords.setText(String.valueOf(remainingWords));

        int progress = 0;
        if (totalWords > 0) {
            progress = (learnedWords * 100) / totalWords;
        }

        progressBar.setProgress(progress);
        tvProgressPercentage.setText(progress + "% Completed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProgress();
    }
}


