package com.suansuan.music.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.suansuan.music.MusicApplication;
import com.suansuan.music.R;
import com.suansuan.music.helper.StatusBarHelper;

import java.lang.ref.WeakReference;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    private static final int ENTRY_HOME = 0;
    private static final int ENTRY_NAVIGATION = 1;
    private static final int TIME = 4000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarHelper.transparencyBar(this);
        setContentView(R.layout.activity_splash);
        new SplashActivityHandler(this).sendEmptyMessageDelayed(ENTRY_HOME, TIME);
    }
    /**
     * 开启一个新的Activity, 然后关闭自身
     * @param Activity : 要开启的Activity
     */
    public void entry(Class Activity){
        Intent intent = new Intent(this, Activity);
        MusicApplication.getInstance().getActivityStartHelper().startActivity(this, intent);
        finish();
    }

    public static class SplashActivityHandler extends Handler {
        private WeakReference<SplashActivity> activityWeakReference;
        private  SplashActivityHandler (SplashActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ENTRY_HOME:
                    activityWeakReference.get().entry(HomeActivity.class);
                    break;
                case ENTRY_NAVIGATION:
                    activityWeakReference.get().entry(HomeActivity.class);
                    break;
            }
        }
    }
}
