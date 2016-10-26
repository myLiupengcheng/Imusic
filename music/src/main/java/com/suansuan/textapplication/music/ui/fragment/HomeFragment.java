package com.suansuan.textapplication.music.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.suansuan.textapplication.Base.BaseFragment;
import com.suansuan.textapplication.music.R;
import com.suansuan.textapplication.music.ui.HomeActivity;
import com.suansuan.textapplication.music.ui.NativeMusicActivity;
import com.suansuan.textapplication.music.ui.view.CombinationControl;

/**
 *
 * Created by suansuan on 2016/10/2.
 */

public class HomeFragment extends BaseFragment {

    private View mViewRoot;
    private CombinationControl mNativeMusicView , mRecentPlay;


    @Override
    protected void findData() {

    }

    @Override
    protected View initView() {
        mViewRoot = View.inflate(mActivity, R.layout.fragment_home, null);
        mNativeMusicView = (CombinationControl) mViewRoot.findViewById(R.id.native_music);
        mRecentPlay = (CombinationControl) mViewRoot.findViewById(R.id.recent_play);
        return mViewRoot;
    }

    @Override
    public void onResume() {
        super.onResume();
        initTiTle();
    }

    /** 初始化标题栏 */
    private void initTiTle() {
        HomeActivity homeActivity = (HomeActivity) mActivity;
        homeActivity.setTitleVisibility(true);
    }


    @Override
    protected void initListener() {
        mNativeMusicView.setOnClickListener(this);
    }

    @Override
    protected void initViewForData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.native_music:
                startNativeMusic();
                break;
        }
    }


    /** 开启本地音乐的Activity */
    private void startNativeMusic() {
        startActivity(new Intent(mActivity, NativeMusicActivity.class));
    }
}
