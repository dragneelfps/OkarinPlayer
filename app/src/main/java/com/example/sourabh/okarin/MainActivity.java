package com.example.sourabh.okarin;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AbsPermissionActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private LibraryView libraryView;
    static int SONGS_READ_ID = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        libraryView = new LibraryViewImp(getLayoutInflater(), this);
        setContentView(libraryView.getRootView());
        if(requestStoragePermission()){
            getLoaderManager().initLoader(SONGS_READ_ID, null, this);
        }
        Toast.makeText(getApplicationContext(), "GHer", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == STORAGE_READ_PERMISSION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLoaderManager().initLoader(SONGS_READ_ID, null, this);
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getApplicationContext(),
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST},
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        libraryView.updateAdapter(extractSongsList(data));
    }


    private ArrayList<Song> extractSongsList(Cursor data){
        if(data != null) {
            int idColumn = data.getColumnIndex(MediaStore.Audio.Media._ID);
            int titleColumn = data.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistColumn = data.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            ArrayList<Song> result = new ArrayList<>();
            while(data.moveToNext()){
                result.add(new Song(data.getString(idColumn),data.getString(titleColumn),
                        data.getString(artistColumn)));
            }
            return result;
        }else{
            return null;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
