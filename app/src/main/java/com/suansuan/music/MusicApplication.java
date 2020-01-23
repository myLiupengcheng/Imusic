package com.suansuan.music;

import android.app.Application;

import com.suansuan.music.helper.ActivityStartHelper;

public class MusicApplication extends Application {

    private static MusicApplication sInstance;
    private ActivityStartHelper mActivityStartHelper;


    @Override
    public void onCreate() {
        sInstance = this;
        mActivityStartHelper = ActivityStartHelper.getActivityStartHelperInstance(this.getApplicationContext());
        super.onCreate();
    }

    public static MusicApplication getInstance() {
        return sInstance;
    }

    public ActivityStartHelper getActivityStartHelper () {
        if (mActivityStartHelper == null) {
            mActivityStartHelper = ActivityStartHelper.getActivityStartHelperInstance(this.getApplicationContext());
        }
        return mActivityStartHelper;
    }
}
