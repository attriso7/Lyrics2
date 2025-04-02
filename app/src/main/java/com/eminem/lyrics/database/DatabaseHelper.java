package com.eminem.lyrics.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "lyrics.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createAlbumsTable = "CREATE TABLE albums (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
        String createSongsTable = "CREATE TABLE songs (id INTEGER PRIMARY KEY AUTOINCREMENT, album_id INTEGER, name TEXT, lyrics TEXT, FOREIGN KEY(album_id) REFERENCES albums(id))";

        db.execSQL(createAlbumsTable);
        db.execSQL(createSongsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS songs");
        db.execSQL("DROP TABLE IF EXISTS albums");
        onCreate(db);
    }
}
