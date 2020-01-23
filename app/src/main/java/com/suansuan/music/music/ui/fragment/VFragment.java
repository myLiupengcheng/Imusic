package com.suansuan.music.music.ui.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.suansuan.music.Base.BaseFragment;
import com.suansuan.music.R;
import com.suansuan.music.activity.HomeActivity;
import com.suansuan.music.music.ui.view.ViewPagerIndicate;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by suansuan on 2016/10/2.
 */

public class VFragment extends BaseFragment {

    private ViewPagerIndicate mViewPagerIndicate;

    private ViewPager mViewPager;

    private List<String> mTabList;

    @Override
    protected void findData() {
        mTabList = Arrays.asList("单曲榜", "专辑榜", "歌手榜");
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_v, null);
        mViewPagerIndicate = (ViewPagerIndicate) view.findViewById(R.id.vp_indicate);
        mViewPagerIndicate.setIsShowLine(false);
        mViewPager = (ViewPager) view.findViewById(R.id.native_music_content);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initTiTle();
    }

    /** 初始化标题栏 */
    private void initTiTle() {
        HomeActivity homeActivity = (HomeActivity) mActivity;
//        homeActivity.setTitleVisibility(false);
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initViewForData() {

    }

    @Override
    public void onClick(View v) {

    }
}
