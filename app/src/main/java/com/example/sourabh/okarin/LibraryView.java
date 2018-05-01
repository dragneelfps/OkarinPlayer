package com.example.sourabh.okarin;

import java.util.ArrayList;

public interface LibraryView extends RootView {
    void updateAdapter(ArrayList<Song> data);
}
