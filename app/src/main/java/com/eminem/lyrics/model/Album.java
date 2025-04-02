package com.eminem.lyrics.model;

public class Album {
    private String name;
    private int coverImage;

    public Album(String name, int coverImage) {
        this.name = name;
        this.coverImage = coverImage;
    }

    public String getName() {
        return name;
    }

    public int getCoverImage() {
        return coverImage;
    }
}
