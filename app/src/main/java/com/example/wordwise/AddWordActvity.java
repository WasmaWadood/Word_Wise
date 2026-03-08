package com.example.wordwise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddWordActvity extends AppCompatActivity {

    EditText etWord, etMeaning;
    Button btnSaveWord;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        etWord = findViewById(R.id.etWord);
        etMeaning = findViewById(R.id.etMeaning);
        btnSaveWord = findViewById(R.id.btnSaveWord);

        databaseHelper = new DatabaseHelper(this);

        btnSaveWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String word = etWord.getText().toString();
                String meaning = etMeaning.getText().toString();

                boolean inserted = databaseHelper.insertWord(word, meaning);

                if(inserted){
                    Toast.makeText(AddWordActvity.this,"Word Saved Successfully",Toast.LENGTH_SHORT).show();

                    etWord.setText("");
                    etMeaning.setText("");
                } else {
                    Toast.makeText(AddWordActvity.this,"Error Saving Word",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}