package com.suansuan.music.song.list;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.suansuan.music.MusicApplication;
import com.suansuan.music.R;
import com.suansuan.music.activity.MusicActivity;
import com.suansuan.music.helper.ActivityHelper;

public class SongListActivity extends MusicActivity {

    private ActivityHelper mActivityHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_song_list);
        super.onCreate(savedInstanceState);
        setMusicActionBarTitle(R.string.app_song_list_square);
        setMusicDisplayHomeAsUpEnabled(true);

        mActivityHelper = MusicApplication.getInstance().getActivityHelper();
        mActivityHelper.setStatusBarTextColor(this, true);
    }
}
