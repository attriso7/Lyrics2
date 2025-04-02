package com.eminem.lyrics.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.eminem.lyrics.adapter.SongAdapter;
import com.eminem.lyrics.database.SongDao;
import com.eminem.lyrics.model.Song;
import java.util.List;

public class SongsActivity extends AppCompatActivity {
    RecyclerView songRecyclerView;
    SongDao songDao;
    List<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        songDao = new SongDao(this);
        songRecyclerView = findViewById(R.id.songRecyclerView);
        songRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get album ID from intent
        int albumId = getIntent().getIntExtra("album_id", -1);

        // Fetch songs dynamically from DB
        songList = songDao.getSongsByAlbum(albumId);

        SongAdapter adapter = new SongAdapter(songList, this, song -> {
            Intent intent = new Intent(SongsActivity.this, LyricsActivity.class);
            intent.putExtra("song_name", song.getName());
            intent.putExtra("lyrics", song.getLyrics());
            startActivity(intent);
        });

        songRecyclerView.setAdapter(adapter);
    }
}
