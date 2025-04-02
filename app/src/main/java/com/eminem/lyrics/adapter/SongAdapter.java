package com.eminem.lyrics.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.eminem.lyrics.R;
import com.eminem.lyrics.models.Song;
import com.eminem.lyrics.ui.LyricsActivity;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private List<Song> songList;

    public SongAdapter(List<Song> songList) {
        this.songList = songList;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.songTitle.setText(song.getTitle());
        holder.albumName.setText(song.getAlbum());

        // Open LyricsActivity when an item is clicked
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), LyricsActivity.class);
            intent.putExtra("song_name", song.getTitle());  // Ensure key matches LyricsActivity.java
            intent.putExtra("lyrics", song.getLyrics());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    // Method to update the song list dynamically for search filtering
    public void updateList(List<Song> newList) {
        songList = newList;
        notifyDataSetChanged();
    }

    static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView songTitle, albumName;

        public SongViewHolder(View itemView) {
            super(itemView);
            songTitle = itemView.findViewById(R.id.songTitle);
            albumName = itemView.findViewById(R.id.albumName);
        }
    }
}
