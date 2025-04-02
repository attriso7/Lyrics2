package com.eminem.lyrics.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.eminem.lyrics.R;

public class LyricsActivity extends AppCompatActivity {
    private TextView songTitle, lyricsText;
    private Button copyButton, shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);

        // Initialize UI components
        songTitle = findViewById(R.id.songTitle);
        lyricsText = findViewById(R.id.lyricsText);
        copyButton = findViewById(R.id.copyButton);
        shareButton = findViewById(R.id.shareButton);

        // Retrieve song details from intent
        String title = getIntent().getStringExtra("song_name");
        String lyrics = getIntent().getStringExtra("lyrics");

        // Set text values
        songTitle.setText(title != null ? title : "Unknown Song");
        lyricsText.setText(lyrics != null ? lyrics : "Lyrics not available.");

        // Copy Lyrics to Clipboard
        copyButton.setOnClickListener(v -> copyLyricsToClipboard(title, lyrics));

        // Share Lyrics
        shareButton.setOnClickListener(v -> shareLyrics(title, lyrics));
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

    private void shareLyrics(String title, String lyrics) {
        if (lyrics == null || lyrics.isEmpty()) {
            Toast.makeText(this, "No lyrics to share!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, title + "\n\n" + lyrics);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
}
