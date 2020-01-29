package com.suansuan.music.song.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.suansuan.music.R;
import com.suansuan.music.activity.MusicActivity;
import com.suansuan.music.focator.FragmentFactory;
import com.suansuan.music.song.list.bean.SongListCategoryGroup;
import com.suansuan.music.song.list.presenter.SongListActivityPresenter;

import java.util.List;

import static com.suansuan.music.song.list.presenter.SongListActivityPresenter.*;


public class SongListActivity extends MusicActivity implements SongListActivityPresenterLoadDataCallback{

    private static final String TAG = "SongListActivity";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    List<SongListCategoryGroup.SongListCategory> songListCategories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_song_list);
        setActivityPresenter(new SongListActivityPresenter( this));
        super.onCreate(savedInstanceState);
        setMusicActionBarTitle(R.string.app_song_list_square);
        setMusicDisplayHomeAsUpEnabled(true);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.song_list_view_pager);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void loadCallbackComplete(List<SongListCategoryGroup> songListCategoryGroupList, List<SongListCategoryGroup.SongListCategory> songListCategories) {
        if (songListCategories == null || songListCategories.size() == 0) {
            return;
        }
        this.songListCategories = songListCategories;
        mTabLayout.setVisibility(View.VISIBLE);
        SongListActivityAdapter songListActivityFragmentAdapter = new SongListActivityAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(songListActivityFragmentAdapter);
    }

    public class SongListActivityAdapter extends FragmentPagerAdapter {

        public CharSequence getPageTitle(int position) {
            return songListCategories.get(position).categoryName;
        }

        private SongListActivityAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return songListCategories.size();
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.newInstance(songListCategories.get(position).categoryId);
        }
    }
}
