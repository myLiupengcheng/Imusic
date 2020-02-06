package com.suansuan.music.song.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.suansuan.music.R;
import com.suansuan.music.activity.MusicActivity;
import com.suansuan.music.focator.FragmentFactory;
import com.suansuan.music.song.list.bean.SongListCategoryGroup;
import com.suansuan.music.song.list.presenter.SongListActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import static com.suansuan.music.song.list.presenter.SongListActivityPresenter.*;


public class SongListMainActivity extends MusicActivity implements SongListActivityPresenterLoadDataCallback{

    private static final String TAG = "SongListMainActivity";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    List<SongListCategoryGroup.SongListCategory> songListCategories;
    ArrayList<SongListCategoryGroup> songListCategoryGroupList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_song_list);
        setActivityPresenter(new SongListActivityPresenter( this));
        super.onCreate(savedInstanceState);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.song_list_view_pager);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.title_more) {
            Intent intent = new Intent(this.getApplicationContext(), SongListCategoryActivity.class);
            intent.putParcelableArrayListExtra(SongListCategoryActivity.KEY_ID_CATEGORY_ALL_DATA, songListCategoryGroupList);
            getActivityHelper().startActivity(this, intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadCallbackComplete(List<SongListCategoryGroup> songListCategoryGroupList,
                                     List<SongListCategoryGroup.SongListCategory> songListCategories) {
        if (songListCategories == null || songListCategories.size() == 0) {
            return;
        }
        this.songListCategories = songListCategories;
        this.songListCategoryGroupList = (ArrayList<SongListCategoryGroup>) songListCategoryGroupList;
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
