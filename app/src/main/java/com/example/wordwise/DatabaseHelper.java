package com.example.wordwise;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wordwise.db";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_WORDS = "words";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_WORD = "word";
    private static final String COLUMN_MEANING = "meaning";
    private static final String COLUMN_LEARNED = "learned";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_WORDS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_WORD + " TEXT, "
                + COLUMN_MEANING + " TEXT, "
                + COLUMN_LEARNED + " INTEGER DEFAULT 0)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }


    public boolean insertWord(String word, String meaning) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_WORD, word);
        values.put(COLUMN_MEANING, meaning);
        values.put(COLUMN_LEARNED, 0);

        long result = db.insert(TABLE_WORDS, null, values);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllWords() {

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_WORDS;

        return db.rawQuery(query, null);
    }


        public void markAsLearned(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LEARNED, 1);

        db.update(TABLE_WORDS, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

}



