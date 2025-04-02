package com.eminem.lyrics.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.eminem.lyrics.R;
import com.eminem.lyrics.adapter.AlbumAdapter;
import com.eminem.lyrics.model.Album;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView albumRecyclerView;
    private AlbumAdapter albumAdapter;
    private List<Album> albumList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        albumRecyclerView = findViewById(R.id.albumRecyclerView);
        albumRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Load albums
        albumList = getAlbums();
        albumAdapter = new AlbumAdapter(albumList, this);
        albumRecyclerView.setAdapter(albumAdapter);

        // Search albums dynamically
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                albumAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                albumAdapter.filter(newText);
                return true;
            }
        });
    }

    private List<Album> getAlbums() {
        List<Album> albums = new ArrayList<>();
        albums.add(new Album("The Eminem Show", R.drawable.the_eminem_show));
        albums.add(new Album("Music To Be Murdered By", R.drawable.music_to_be_murdered_by));
        albums.add(new Album("Revival", R.drawable.revival));
        albums.add(new Album("Kamikaze", R.drawable.kamikaze));
        return albums;
    }
}
