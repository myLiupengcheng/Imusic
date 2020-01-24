package com.suansuan.music.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.suansuan.music.MusicApplication;
import com.suansuan.music.R;
import com.suansuan.music.helper.ActivityHelper;

public class HomeActivity extends AppCompatActivity {

    private ActivityHelper mActivityHelper;
    private Toolbar mActionBarToolbar;
    private ActionBar mSupportActionBar;

    private View mContainer;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mSupportActionBar.setTitle(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mSupportActionBar.setTitle(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mSupportActionBar.setTitle(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityHelper = MusicApplication.getInstance().getActivityStartHelper();
        mActivityHelper.setStatusBarTextColor(this, true);
        initHomeActivityView();
    }

    private void initHomeActivityView() {
        setContentView(R.layout.activity_home);
        setToolbar();
        mContainer = findViewById(R.id.fragment_container);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    private void setToolbar() {
        mActionBarToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(mActionBarToolbar);
        mSupportActionBar = getSupportActionBar();
        if (mSupportActionBar != null) {
            mSupportActionBar.setDisplayHomeAsUpEnabled(false);
            mSupportActionBar.setTitle(R.string.title_home);
        }
    }

}
