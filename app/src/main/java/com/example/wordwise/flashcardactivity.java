package com.example.wordwise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class flashcardactivity extends AppCompatActivity {

    LinearLayout cardView;
    TextView tvCardText;
    Button btnKnowIt, btnDontKnow;

    boolean isFront = true;
    int currentIndex = 0;


    String[] englishWords = {"Apple", "Book", "Cat", "Dog", "Sun"};
    String[] sinhalaMeanings = {"ඇපල්", "පොත", "බළලා", "බල්ලා", "ඉර"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);


        cardView = findViewById(R.id.cardView);
        tvCardText = findViewById(R.id.tvCardText);
        btnKnowIt = findViewById(R.id.btnKnowIt);
        btnDontKnow = findViewById(R.id.btnDontKnow);


        showWord();


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });


        btnKnowIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextWord();
            }
        });


        btnDontKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextWord();
            }
        });
    }


    private void showWord() {
        if (currentIndex < englishWords.length) {
            // Display the English word and reset card rotation to default
            tvCardText.setText(englishWords[currentIndex]);
            isFront = true;
            cardView.setRotationY(0f);
        } else {

            tvCardText.setText("Practice\nFinished! 🎉");
            btnKnowIt.setEnabled(false);
            btnDontKnow.setEnabled(false);
            Toast.makeText(this, "You have finished all words for today!", Toast.LENGTH_SHORT).show();
        }
    }

    private void nextWord() {
        currentIndex++;
        showWord();
    }


    private void flipCard() {
        if (currentIndex >= englishWords.length) return;

        cardView.animate().rotationY(90f).setDuration(200).withEndAction(new Runnable() {
            @Override
            public void run() {
                if (isFront) {
                    tvCardText.setText(sinhalaMeanings[currentIndex]);
                    isFront = false;
                } else {
                    tvCardText.setText(englishWords[currentIndex]);
                    isFront = true;
                }
                cardView.setRotationY(-90f);
                cardView.animate().rotationY(0f).setDuration(200).start();
            }
        }).start();
    }
}