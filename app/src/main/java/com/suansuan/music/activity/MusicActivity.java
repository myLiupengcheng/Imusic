package com.suansuan.music.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.suansuan.music.MusicApplication;
import com.suansuan.music.R;
import com.suansuan.music.activity.presenter.ActivityPresenter;
import com.suansuan.music.helper.ActivityHelper;

public abstract class MusicActivity extends AppCompatActivity {

    private ActionBar mSupportActionBar;
    private ActivityPresenter mActivityPresenter;
    private ActivityHelper mActivityHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityHelper = MusicApplication.getInstance().getActivityHelper();
        mActivityHelper.setStatusBarTextColor(this, true);
        if (mActivityPresenter != null) {
            mActivityPresenter.onCreate();
        }
        Toolbar actionBarToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(actionBarToolbar);
        mSupportActionBar = getSupportActionBar();
        if (mSupportActionBar != null) {
            CharSequence title = getTitle();
            mSupportActionBar.setTitle(title);
        }

    }

    protected void setMusicDisplayHomeAsUpEnabled (boolean displayHomeAsUpEnabled) {
        if (mSupportActionBar != null) {
            mSupportActionBar.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
        }
    }

    protected void setMusicActionBarTitle (int titleRes) {
        if (mSupportActionBar != null) {
            mSupportActionBar.setTitle(titleRes);
        }
    }

    protected void setActivityPresenter (ActivityPresenter activityPresenter) {
        mActivityPresenter = activityPresenter;
    }

    protected ActivityPresenter getActivityPresenter(){
        return mActivityPresenter;
    }

    protected ActivityHelper getActivityHelper () {
        return mActivityHelper;
    }
}
