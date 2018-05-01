package com.example.sourabh.okarin;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

public class SongHelper {
    static SongDetail getSongDetails(String id, Context context){
        final String[] projection = {MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.SIZE, MediaStore.Audio.Media.MIME_TYPE, MediaStore.Audio.Media.DURATION};
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, MediaStore.Audio.Media._ID + " = ?",
                new String[]{ id }, null);
        SongDetail songDetail = null;
        if(cursor != null) {
            while (cursor.moveToNext()) {
                int filePathIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
                int titleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                int sizeIndex = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE);
                int mimeTypeIndex = cursor.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE);
                int durationIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
                String filePath = cursor.getString(filePathIndex);
                String title = cursor.getString(titleIndex);
                long size = cursor.getLong(sizeIndex);
                String mimeType = cursor.getString(mimeTypeIndex);
                long duration = cursor.getLong(durationIndex);
                songDetail = new SongDetail(filePath, title, size, mimeType, duration);
                break;
            }
        }
        return songDetail;
    }
}
