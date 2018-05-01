package com.example.sourabh.okarin;

public class Song {
    private String mID;
    private String mTitle;
    private String mArtist;

    public Song(String mID, String mTitle, String mArtist) {
        this.mID = mID;
        this.mTitle = mTitle;
        this.mArtist = mArtist;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getID() {
        return mID;
    }
}
