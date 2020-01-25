package com.suansuan.music.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.suansuan.music.MusicApplication;
import com.suansuan.music.R;
import com.suansuan.music.fragment.UnConnectivityFragment;
import com.suansuan.music.hap.MusicCommonFeaturesUtil;
import com.suansuan.music.helper.ActivityHelper;

public class HomeActivity extends AppCompatActivity {

    private static final String FRAGMENT_UNCONNECT_TAG = "UnConnectivityFragment";

    private ActivityHelper mActivityHelper;
    private Toolbar mActionBarToolbar;
    private ActionBar mSupportActionBar;

    private UnConnectivityFragment unConnectivityFragment;

    private FragmentManager supportFragmentManager;

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
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (!MusicCommonFeaturesUtil.isConnection(this.getApplicationContext())) {
            // 判断当前的手机是否连接网络，如果没有连接网络则直接显示，为未连接网络的Fragment
            unConnectivityFragment = new UnConnectivityFragment();
            replaceFragment(R.id.fragment_container, FRAGMENT_UNCONNECT_TAG);
        }
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

    private void replaceFragment (int resourcesId, String tag) {
        if (supportFragmentManager == null) {
            supportFragmentManager = this.getSupportFragmentManager();
        }
        Fragment fragmentCacheInstance = supportFragmentManager.findFragmentByTag(tag);
        if (fragmentCacheInstance == null) {
            fragmentCacheInstance = createFragment(tag);
        }
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(resourcesId, fragmentCacheInstance, tag);
        fragmentTransaction.commit();
    }

    private Fragment createFragment(String tag) {
        switch (tag) {
            case FRAGMENT_UNCONNECT_TAG :
                return new UnConnectivityFragment();
            case "HomeFragment" :
                return new UnConnectivityFragment();

                default:
                    return null;
        }
    }

    private void adjustNavigationIcoSize(BottomNavigationView navigation){
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
    }


}
