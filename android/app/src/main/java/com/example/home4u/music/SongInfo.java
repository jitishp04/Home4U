package com.example.home4u.music;

public class SongInfo {
    private final String fileName;
    private final String name;
    private final String artist;
    private final String album;
    private final String length;


    SongInfo(String fileName, String name, String artist, String album, String length) {
        this.fileName = fileName;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }


    public String getFileName() {
        return fileName;
    }

    public String getName() {
        return name;
    }
}
