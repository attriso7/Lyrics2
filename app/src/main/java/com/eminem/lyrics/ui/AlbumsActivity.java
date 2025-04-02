package com.eminem.lyrics.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.eminem.lyrics.adapter.AlbumAdapter;
import com.eminem.lyrics.database.DatabaseHelper;
import com.eminem.lyrics.model.Album;
import java.util.ArrayList;
import java.util.List;

public class AlbumsActivity extends AppCompatActivity {
    RecyclerView albumRecyclerView;
    List<Album> albumList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        albumRecyclerView = findViewById(R.id.albumRecyclerView);
        albumRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch albums dynamically from the database
        albumList = fetchAlbumsFromDatabase();

        AlbumAdapter adapter = new AlbumAdapter(albumList, album -> {
            Intent intent = new Intent(AlbumsActivity.this, SongsActivity.class);
            intent.putExtra("album_id", album.getId());
            startActivity(intent);
        });

        albumRecyclerView.setAdapter(adapter);
    }

    private List<Album> fetchAlbumsFromDatabase() {
        List<Album> albums = new ArrayList<>();
        // TODO: Write query to fetch albums from the database
        return albums;
    }
}
