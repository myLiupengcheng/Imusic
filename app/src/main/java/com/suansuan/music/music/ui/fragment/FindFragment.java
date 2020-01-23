package com.suansuan.music.music.ui.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.suansuan.music.Base.BaseFragment;
import com.suansuan.music.activity.HomeActivity;

/**
 * Created by suansuan on 2016/10/2.
 */

public class FindFragment extends BaseFragment {


    @Override
    protected void findData() {
    }

    @Override
    protected View initView() {
        TextView textView = new TextView(mActivity);
        textView.setGravity(Gravity.CENTER);
        textView.setText("导航");
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        return textView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initTiTle();
    }

    /** 初始化标题栏 */
    private void initTiTle() {
        HomeActivity homeActivity = (HomeActivity) mActivity;
//        homeActivity.setTitleVisibility(true);
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
