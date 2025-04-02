package com.eminem.lyrics.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.eminem.lyrics.model.Song;
import java.util.ArrayList;
import java.util.List;

public class SongDao {
    private SQLiteDatabase db;

    public SongDao(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void insertSong(int albumId, String name, String lyrics) {
        ContentValues values = new ContentValues();
        values.put("album_id", albumId);
        values.put("name", name);
        values.put("lyrics", lyrics);
        db.insert("songs", null, values);
    }

    public List<Song> getSongsByAlbum(int albumId) {
        List<Song> songs = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM songs WHERE album_id = ?", new String[]{String.valueOf(albumId)});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(2);
            String lyrics = cursor.getString(3);
            songs.add(new Song(id, name, lyrics));
        }
        cursor.close();
        return songs;
    }
}
