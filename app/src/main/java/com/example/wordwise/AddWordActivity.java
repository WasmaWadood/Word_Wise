package com.example.wordwise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddWordActivity extends AppCompatActivity {


    EditText etEnglishWord, etSinhalaMeaning;
    Button btnSaveWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        // Important: Ensure these IDs match the ones in activity_add_word.xml
        etEnglishWord = findViewById(R.id.etEnglishWord);
        etSinhalaMeaning = findViewById(R.id.etSinhalaMeaning);
        btnSaveWord = findViewById(R.id.btnSaveWord);


        btnSaveWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWordToDatabase();
            }
        });
    }

    private void saveWordToDatabase() {

        String word = etEnglishWord.getText().toString().trim();
        String meaning = etSinhalaMeaning.getText().toString().trim();

        if (word.isEmpty() || meaning.isEmpty()) {
            Toast.makeText(this, "Please enter both word and meaning!", Toast.LENGTH_SHORT).show();
            return;
        }


        Toast.makeText(this, "Word added successfully: " + word, Toast.LENGTH_LONG).show();


        etEnglishWord.setText("");
        etSinhalaMeaning.setText("");
    }
}