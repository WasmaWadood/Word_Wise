package com.example.wordwise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAddWord, btnViewWords, btnProgress, btnPractice, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddWord = findViewById(R.id.btnAddWord);
        btnViewWords = findViewById(R.id.btnViewWords);
        btnProgress = findViewById(R.id.btnProgress);
        btnPractice = findViewById(R.id.btnPractice);
        btnLogout = findViewById(R.id.btnLogout);

        btnAddWord.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddWordActvity.class);
            startActivity(intent);
        });

        btnViewWords.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WordListActivity.class);
            startActivity(intent);
        });

        btnProgress.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProgressActivity.class);
            startActivity(intent);
        });

        btnPractice.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FlashcardActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}