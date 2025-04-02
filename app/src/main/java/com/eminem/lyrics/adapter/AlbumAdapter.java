package com.eminem.lyrics.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.eminem.lyrics.R;
import com.eminem.lyrics.model.Album;
import com.eminem.lyrics.ui.SongsActivity;
import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private List<Album> albumList, filteredList;
    private final Context context;

    public AlbumAdapter(List<Album> albumList, Context context) {
        this.albumList = albumList;
        this.filteredList = new ArrayList<>(albumList);
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = filteredList.get(position);
        holder.albumName.setText(album.getName());
        holder.albumCover.setImageResource(album.getCoverImage());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SongsActivity.class);
            intent.putExtra("album_name", album.getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(albumList);
        } else {
            for (Album album : albumList) {
                if (album.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(album);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView albumCover;
        TextView albumName;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            albumCover = itemView.findViewById(R.id.albumCover);
            albumName = itemView.findViewById(R.id.albumName);
        }
    }
}
