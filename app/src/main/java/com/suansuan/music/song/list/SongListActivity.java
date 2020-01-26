package com.suansuan.music.song.list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.suansuan.music.MusicApplication;
import com.suansuan.music.MusicConstant;
import com.suansuan.music.R;
import com.suansuan.music.activity.MusicActivity;
import com.suansuan.music.activity.presenter.ActivityPresenter;
import com.suansuan.music.hap.loging.MusicLog;
import com.suansuan.music.helper.ActivityHelper;
import com.suansuanliu.core.MusicNetworkCallback;
import com.suansuanliu.core.NetWorkManager;

import java.io.IOException;

public class SongListActivity extends MusicActivity {

    private static final String TAG = "SongListActivity";

    private ActivityHelper mActivityHelper;
    private ActivityPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_song_list);
        super.onCreate(savedInstanceState);
        mPresenter = new SongListActivityPresenter();
        setActivityPresenter(mPresenter);
        setMusicActionBarTitle(R.string.app_song_list_square);
        setMusicDisplayHomeAsUpEnabled(true);
        mActivityHelper = MusicApplication.getInstance().getActivityHelper();
        mActivityHelper.setStatusBarTextColor(this, true);
    }

}
