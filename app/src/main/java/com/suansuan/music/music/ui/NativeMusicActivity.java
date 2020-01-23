package com.suansuan.music.music.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.suansuan.music.Base.BaseActivity;
import com.suansuan.music.Base.BaseFragment;
import com.suansuan.music.R;
import com.suansuan.music.focator.FragmentFactory;
import com.suansuan.music.music.ui.view.ViewPagerIndicate;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by suansuan on 2016/10/8.
 */

public class NativeMusicActivity extends BaseActivity {

    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    private ViewPagerIndicate mViewPagerIndicate;

    private List<String> mTitles;

    @Override
    protected int getResId() {
        return R.layout.activity_nativemusic;
    }

    @Override
    protected void initView() {
        mViewPager = (ViewPager) findViewById(R.id.native_music_content);
        mViewPagerIndicate = (ViewPagerIndicate)findViewById(R.id.vp_indicate);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_native_music_root);
        mViewPagerIndicate.setTab(mTitles);
    }

    @Override
    protected void initAdapter() {
        NativeMusicAdapter nativeMusicAdapter = new NativeMusicAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(nativeMusicAdapter);
    }

    @Override
    protected void initListener() {
        mViewPagerIndicate.setViewPager(mViewPager);
    }

    @Override
    protected void initData() {
        mTitles = Arrays.asList("单曲","歌手","专辑","文件夹");
    }

    @Override
    protected void onClick(View v, int id) {

    }


    @Override
    protected void onDestroy() {
        mLinearLayout.removeAllViews();
        super.onDestroy();
    }

    class NativeMusicAdapter extends FragmentPagerAdapter{

        public NativeMusicAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            BaseFragment baseFragment = FragmentFactory.newInstanceNativeMusicFragment(position);
            return baseFragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
