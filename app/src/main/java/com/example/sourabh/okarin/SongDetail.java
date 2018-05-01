package com.example.sourabh.okarin;

public class SongDetail {

    private String mFilePath;
    private String mTitle;
    private long mSize;
    private String mMimeType;
    private long mDuration;


    public SongDetail(String mFilePath, String mTitle, long mSize, String mMimeType, long mDuration) {
        this.mFilePath = mFilePath;
        this.mTitle = mTitle;
        this.mSize = mSize;
        this.mMimeType = mMimeType;
        this.mDuration = mDuration;
    }

    public String getmFilePath() {
        return mFilePath;
    }

    public String getmTitle() {
        return mTitle;
    }

    public long getmSize() {
        return mSize;
    }

    public String getmMimeType() {
        return mMimeType;
    }

    public long getmDuration() {
        return mDuration;
    }
}
