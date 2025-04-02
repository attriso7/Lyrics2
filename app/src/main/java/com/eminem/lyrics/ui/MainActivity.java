package com.eminem.lyrics;

import android.os.Bundle;
import android.widget.SearchView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.eminem.lyrics.adapter.SongAdapter;
import com.eminem.lyrics.models.Song;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SongAdapter songAdapter;
    private List<Song> songList;
    private SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.albumRecyclerView);
        searchView = findViewById(R.id.searchView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load sample songs
        songList = getSampleSongs();
        songAdapter = new SongAdapter(songList);
        recyclerView.setAdapter(songAdapter);

        // Setup search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterSongs(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterSongs(newText);
                return true;
            }
        });
    }

    private List<Song> getSampleSongs() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Lose Yourself", "8 Mile", "Look, if you had one shot..."));
        songs.add(new Song("Stan", "The Marshall Mathers LP", "Dear Slim, I wrote you but still ain't calling..."));
        songs.add(new Song("Not Afraid", "Recovery", "I'm not afraid to take a stand..."));
        songs.add(new Song("Godzilla", "Music to Be Murdered By", "I can swallow a bottle of alcohol and I'll feel like Godzilla..."));
        return songs;
    }

    private void filterSongs(String query) {
        List<Song> filteredList = new ArrayList<>();
        for (Song song : songList) {
            if (song.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    song.getAlbum().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(song);
            }
        }
        songAdapter.updateList(filteredList);
    }
}
