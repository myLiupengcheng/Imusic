package com.suansuan.music.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.suansuan.music.R;

public class MusicActivity extends AppCompatActivity {

    private ActionBar mSupportActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar actionBarToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(actionBarToolbar);
        mSupportActionBar = getSupportActionBar();
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
}
