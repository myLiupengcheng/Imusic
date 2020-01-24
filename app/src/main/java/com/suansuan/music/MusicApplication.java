package com.suansuan.music;

import android.app.Application;

import com.suansuan.music.helper.ActivityHelper;

public class MusicApplication extends Application {

    private static MusicApplication sInstance;
    private ActivityHelper mActivityHelper;


    @Override
    public void onCreate() {
        sInstance = this;
        mActivityHelper = ActivityHelper.getActivityStartHelperInstance(this.getApplicationContext());
        super.onCreate();
    }

    public static MusicApplication getInstance() {
        return sInstance;
    }

    public ActivityHelper getActivityStartHelper () {
        if (mActivityHelper == null) {
            mActivityHelper = ActivityHelper.getActivityStartHelperInstance(this.getApplicationContext());
        }
        return mActivityHelper;
    }
}
