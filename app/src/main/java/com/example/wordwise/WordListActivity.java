package com.example.wordwise;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WordListActivity extends AppCompatActivity {

    RecyclerView recyclerWords;
    DatabaseHelper databaseHelper;
    WordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        recyclerWords = findViewById(R.id.recyclerWords);

        databaseHelper = new DatabaseHelper(this);

        Cursor cursor = databaseHelper.getAllWords();

        adapter = new WordAdapter(this, cursor);

        recyclerWords.setLayoutManager(new LinearLayoutManager(this));
        recyclerWords.setAdapter(adapter);
    }
}