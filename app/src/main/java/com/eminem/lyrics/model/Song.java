package com.eminem.lyrics.models;

public class Song {
    private String title;
    private String album;
    private String lyrics;

    public Song(String title, String album, String lyrics) {
        this.title = title;
        this.album = album;
        this.lyrics = lyrics;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getLyrics() {
        return lyrics;
    }
}
