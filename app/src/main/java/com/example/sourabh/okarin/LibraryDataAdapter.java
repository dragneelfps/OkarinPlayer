package com.example.sourabh.okarin;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class LibraryDataAdapter extends RecyclerView.Adapter<LibraryDataAdapter.VH> {
    class VH extends RecyclerView.ViewHolder{
        VH(View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.song_title);
            mArtistTextView = itemView.findViewById(R.id.song_artist);
            mDetailsButton = itemView.findViewById(R.id.details);
        }
        TextView mTitleTextView;
        TextView mArtistTextView;
        ImageView mDetailsButton;
    }

    private WeakReference<MainActivity> mWeakReferenceActivity;

    private ArrayList<Song> mData;
    private Context mContext;
    public LibraryDataAdapter(MainActivity activity){
        mData = new ArrayList<>();
        mWeakReferenceActivity = new WeakReference<>(activity);
        mContext = mWeakReferenceActivity.get().getApplicationContext();
    }

    public void updateData(ArrayList<Song> data){
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(mContext).inflate(R.layout.library_item_layout, parent,false);
        return new VH(holderView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        final Song currentSong = mData.get(position);
        holder.mTitleTextView.setText(currentSong.getTitle());
        holder.mArtistTextView.setText(currentSong.getArtist());
        holder.mDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog detailsDialog = new MaterialDialog.Builder(mWeakReferenceActivity.get())
                        .title("Details")
                        .customView(R.layout.song_details_layout, true)
                        .show();
                LoadDetails loadDetailsTask = new LoadDetails(detailsDialog.getCustomView(), mContext, currentSong.getID());
                loadDetailsTask.execute();
            }
        });
    }

    class LoadDetails extends AsyncTask<Void,Void, SongDetail>{

        private View mDalogView;
        private Context mContext;
        private String mSongId;

        LoadDetails(View dialogView, Context mContext, String mSongId) {
            this.mDalogView = dialogView;
            this.mContext = mContext;
            this.mSongId = mSongId;
        }

        @Override
        protected SongDetail doInBackground(Void... voids) {
            return SongHelper.getSongDetails(mSongId, mContext);
        }

        @Override
        protected void onPostExecute(SongDetail songDetail) {
            ((TextView)mDalogView.findViewById(R.id.file_path)).setText(songDetail.getmFilePath());
            ((TextView)mDalogView.findViewById(R.id.file_name)).setText(songDetail.getmTitle());
            ((TextView)mDalogView.findViewById(R.id.file_size)).setText(songDetail.getmSize() + "");
            ((TextView)mDalogView.findViewById(R.id.file_format)).setText(songDetail.getmMimeType());
            ((TextView)mDalogView.findViewById(R.id.file_duration)).setText(songDetail.getmDuration() + "");

        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
}
