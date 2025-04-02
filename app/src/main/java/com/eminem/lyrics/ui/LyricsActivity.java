package com.eminem.lyrics.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.eminem.lyrics.R;

public class LyricsActivity extends AppCompatActivity {
    private TextView songTitleTextView, lyricsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);

        // Initialize UI components (Updated IDs)
        songTitleTextView = findViewById(R.id.songTitleTextView);
        lyricsTextView = findViewById(R.id.lyricsTextView);

        // Retrieve song details from intent
        String title = getIntent().getStringExtra("songTitle");
        String lyrics = getIntent().getStringExtra("lyrics");

        // Set text values
        songTitleTextView.setText(title != null ? title : "Unknown Song");
        lyricsTextView.setText(lyrics != null ? lyrics : "Lyrics not available.");

        // Copy lyrics when clicked
        lyricsTextView.setOnLongClickListener(v -> {
            copyLyricsToClipboard(title, lyrics);
            return true;
        });
    }

    private void copyLyricsToClipboard(String title, String lyrics) {
        if (lyrics == null || lyrics.isEmpty()) {
            Toast.makeText(this, "No lyrics to copy!", Toast.LENGTH_SHORT).show();
            return;
        }
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Lyrics", title + "\n\n" + lyrics);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Lyrics copied to clipboard!", Toast.LENGTH_SHORT).show();
    }
}
