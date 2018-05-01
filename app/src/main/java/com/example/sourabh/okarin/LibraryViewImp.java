package com.example.sourabh.okarin;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

public class LibraryViewImp implements LibraryView {
    private View mRootView;
    private LibraryDataAdapter mAdapter;

    public LibraryViewImp(LayoutInflater inflater, MainActivity activity){
        mRootView = inflater.inflate(R.layout.library_layout, null, false);
        mAdapter = new LibraryDataAdapter(activity);
        RecyclerView listView = (RecyclerView)mRootView.findViewById(R.id.library_songs_list);
        listView.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
        listView.setAdapter(mAdapter);
    }

    @Override
    public void updateAdapter(ArrayList<Song> data) {
        mAdapter.updateData(data);
    }

    @Override
    public View getRootView() {
        return mRootView;
    }
}
