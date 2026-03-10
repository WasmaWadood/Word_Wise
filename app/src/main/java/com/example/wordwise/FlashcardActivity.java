package com.example.wordwise;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FlashcardActivity extends AppCompatActivity {

    private TextView tvCardText;
    private Button btnDontKnow, btnKnowIt;
    private LinearLayout cardView;
    private DatabaseHelper databaseHelper;
    private Cursor cursor;
    private boolean isShowingMeaning = false;
    private String currentWord, currentMeaning;
    private int currentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        tvCardText = findViewById(R.id.tvCardText);
        btnDontKnow = findViewById(R.id.btnDontKnow);
        btnKnowIt = findViewById(R.id.btnKnowIt);
        cardView = findViewById(R.id.cardView);

        databaseHelper = new DatabaseHelper(this);
        loadWords();

        cardView.setOnClickListener(v -> flipCard());

        btnDontKnow.setOnClickListener(v -> {
            flipCard();
            Toast.makeText(this, "Keep practicing this word!", Toast.LENGTH_SHORT).show();
        });

        btnKnowIt.setOnClickListener(v -> {
            if (cursor != null && !cursor.isAfterLast()) {
                databaseHelper.markAsLearned(currentId);
                Toast.makeText(this, "Marked as Learned!", Toast.LENGTH_SHORT).show();
                showNextWord();
            }
        });
    }

    private void loadWords() {
        if (cursor != null) cursor.close();

        // First try to load unlearned words
        cursor = databaseHelper.getUnlearnedWords();

        // If no unlearned words found, load ALL words for revision
        if (cursor == null || cursor.getCount() == 0) {
            if (cursor != null) cursor.close();
            cursor = databaseHelper.getAllWords();

            if (cursor != null && cursor.getCount() > 0) {
                Toast.makeText(this, "All caught up! Reviewing all words.", Toast.LENGTH_SHORT).show();
            }
        }

        if (cursor != null && cursor.moveToFirst()) {
            displayCurrentWord();
            // Ensure UI is enabled
            btnDontKnow.setEnabled(true);
            btnKnowIt.setEnabled(true);
            cardView.setEnabled(true);
        } else {
            showEmptyState();
        }
    }

    private void displayCurrentWord() {
        // Cursor columns: 0=id, 1=word, 2=meaning, 3=learned
        currentId = cursor.getInt(0);
        currentWord = cursor.getString(1);
        currentMeaning = cursor.getString(2);

        // Reset state
        isShowingMeaning = false;
        cardView.setRotationY(0f);
        cardView.animate().cancel();
        tvCardText.setText(currentWord);
    }

    private void flipCard() {
        cardView.animate().rotationY(90f).setDuration(150).withEndAction(() -> {
            if (isShowingMeaning) {
                tvCardText.setText(currentWord);
            } else {
                tvCardText.setText(currentMeaning);
            }
            isShowingMeaning = !isShowingMeaning;
            cardView.setRotationY(-90f);
            cardView.animate().rotationY(0f).setDuration(150).start();
        }).start();
    }

    private void showNextWord() {
        if (cursor.moveToNext()) {
            displayCurrentWord();
        } else {
            // End of list, reload words to cycle again
            Toast.makeText(this, "Restarting list...", Toast.LENGTH_SHORT).show();
            loadWords();
        }
    }

    private void showEmptyState() {
        tvCardText.setText("No words to review!");
        btnDontKnow.setEnabled(false);
        btnKnowIt.setEnabled(false);
        cardView.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cursor != null) cursor.close();
    }
}


