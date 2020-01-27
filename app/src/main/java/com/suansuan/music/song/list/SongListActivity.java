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
import com.suansuan.music.song.list.bean.SongListCategoryGroup;

import java.util.List;

import static com.suansuan.music.song.list.SongListActivityPresenter.*;


public class SongListActivity extends MusicActivity implements SongListActivityPresenterLoadDataCallback{

    private static final String TAG = "SongListActivity";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private SongListActivityFragmentAdapter songListActivityFragmentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_song_list);
        setActivityPresenter(new SongListActivityPresenter( this));
        super.onCreate(savedInstanceState);
        setMusicActionBarTitle(R.string.app_song_list_square);
        setMusicDisplayHomeAsUpEnabled(true);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.song_list_view_pager);
        songListActivityFragmentAdapter = new SongListActivityFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(songListActivityFragmentAdapter);
    }

    @Override
    public void loadCallbackComplete(List<SongListCategoryGroup> songListCategoryGroupList, List<SongListCategoryGroup.SongListCategory> songListCategories) {
        if (songListCategories == null || songListCategories.size() == 0) {
            return;
        }
        mTabLayout.setVisibility(View.VISIBLE);
        for (int i = 0; i < songListCategories.size(); i++) {
            SongListCategoryGroup.SongListCategory songListCategory = songListCategories.get(i);
            TabLayout.Tab tab = mTabLayout.newTab();
            tab.setTag(songListCategory);
            tab.setText(songListCategory.categoryName);
            mTabLayout.addTab(tab);
        }
        songListActivityFragmentAdapter.notifyDataSetChanged();
    }

    public class SongListActivityFragmentAdapter extends FragmentPagerAdapter {

        public SongListActivityFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mTabLayout.getTabCount();
        }

        @Override
        public Fragment getItem(int position) {

            return null;
        }
    }
}
