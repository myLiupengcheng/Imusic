package com.suansuan.music.song.list.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.suansuan.music.R;
import com.suansuan.music.activity.presenter.FragmentPresenter;
import com.suansuan.music.fragment.DelayLoadingFragment;
import com.suansuan.music.song.list.bean.SongList;
import com.suansuan.music.song.list.presenter.SongListFragmentPresenter;
import com.suansuan.music.uicore.pic.DrawableManager;
import com.suansuan.music.uicore.pic.PosterRoundTransform;

import java.util.List;

public class SongListFragment extends DelayLoadingFragment
    implements SongListFragmentPresenter.SongListFragmentPresenterLoadDataCallback {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private List<SongList> mSongListCategoryGroupList;
    private PosterRoundTransform mPosterRoundTransform;

    @Override
    public void onAttach(Context context) {
        setFragmentPresenter(new SongListFragmentPresenter(this));
        mPosterRoundTransform = new PosterRoundTransform(context);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_recycler_progress, container, false);
        mRecyclerView = rootView.findViewById(R.id.song_list_recycler_view);
        mProgressBar = rootView.findViewById(R.id.song_list_progress_bar);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return rootView;
    }

    @Override
    public void loadCallbackComplete(List<SongList> songListCategoryGroupList) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mSongListCategoryGroupList = songListCategoryGroupList;
        mRecyclerView.setAdapter(new SongListFragmentAdapter());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void loadData() {
        FragmentPresenter fragmentPresenter = getFragmentPresenter();
        if (fragmentPresenter instanceof SongListFragmentPresenter) {
            ((SongListFragmentPresenter) fragmentPresenter).onDelayLoadingData();
        }
    }

    public class SongListFragmentAdapter extends RecyclerView.Adapter<SongListFragmentAdapter.ViewHolder> {

        @Override
        public int getItemCount() {
            return mSongListCategoryGroupList.size();
        }

        private SongList getItem (int position) {
            return mSongListCategoryGroupList.get(position);
        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getContext()).
                inflate(R.layout.item_pager_song_list, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            SongList item = getItem(position);
            Context context = SongListFragment.this.getContext();
            viewHolder.contentTextTitle.setText(item.dissname.trim());
            Glide.with(SongListFragment.this)
                    .load("")
                    .placeholder(DrawableManager.newInstance(context).getPosterPlaceholderDrawable())
                    .transform(new CenterCrop(context), mPosterRoundTransform)
                    .into(viewHolder.contentImageView);
        }


        private class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView contentImageView;
            private TextView contentTextTitle;
            private ViewHolder(View itemView) {
                super(itemView);
                contentImageView = itemView.findViewById(R.id.content_image);
                contentTextTitle = itemView.findViewById(R.id.content_title);
            }
        }
    }
}
