package com.suansuan.music.song.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.suansuan.music.R;
import com.suansuan.music.activity.MusicActivity;
import com.suansuan.music.song.list.bean.SongListCategoryGroup;
import com.suansuan.music.song.list.presenter.SongListCategoryActivityPresenter;

import java.util.ArrayList;
import java.util.List;

public class SongListCategoryActivity extends MusicActivity {

    public static final String KEY_ID_CATEGORY_ALL_DATA = "key_id_category_all_data";

    private static final int COUNT_RECYCLE_VIEW_SPAN = 4;

    ArrayList<SongListCategoryGroup> songListCategoryGroupList;

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityPresenter(new SongListCategoryActivityPresenter());
        setContentView(R.layout.activity_song_list_category);
        super.onCreate(savedInstanceState);
        initSongListCategoryActivityView();
    }

    private void initSongListCategoryActivityView() {
        mRecyclerView = findViewById(R.id.song_list_recycler_view);
        mLayoutManager = new GridLayoutManager(this, COUNT_RECYCLE_VIEW_SPAN, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        SongListCategoryAdapter songListCategoryAdapter = new SongListCategoryAdapter(songListCategoryGroupList);
        mRecyclerView.setAdapter(songListCategoryAdapter);
    }
}

class SongListCategoryAdapter extends RecyclerView.Adapter<SongListCategoryAdapter.ViewHolder> {

    private static final int VIEW_TYPE_TITLE= 0;
    private static final int VIEW_TYPE_ITEM = 1;

    private ArrayList<SongListCategoryGroup> songListCategoryGroupList;
    private List<Integer>  mTitlePosition;

    public SongListCategoryAdapter(ArrayList<SongListCategoryGroup> songListCategoryGroupList) {
        this.songListCategoryGroupList = songListCategoryGroupList;
        mTitlePosition = new ArrayList<>();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        int size = 0 ;
        for (SongListCategoryGroup temp : songListCategoryGroupList) {
            mTitlePosition.add(size++);
            size += temp.items.size();
        }
        Log.i("suansuan", mTitlePosition.toArray().toString() + "" );
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
//        public TextView mTitle;
//        public TextView mItem;
//        public ImageView mImageView;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//        }
    }
}
