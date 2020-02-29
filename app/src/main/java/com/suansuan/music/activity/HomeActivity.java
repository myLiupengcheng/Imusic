package com.suansuan.music.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.suansuan.music.R;
import com.suansuan.music.fragment.HomeFragment;
import com.suansuan.music.fragment.UnConnectivityFragment;
import com.suansuan.music.hap.MusicCommonFeaturesUtil;
import com.suansuan.music.hap.MusicCommonFeaturesUtil.NetWorkBroadcastReceiver;

public class HomeActivity extends MusicActivity {

    private static final String FRAGMENT_UN_CONNECT_TAG = "UnConnectivityFragment";
    private static final String FRAGMENT_HOME_TAG = "HomeFragment";

    private FragmentManager supportFragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setMusicActionBarTitle(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    setMusicActionBarTitle(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    setMusicActionBarTitle(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);
        initHomeActivityView(MusicCommonFeaturesUtil.isConnection(this));
        receiverNetworkListener();
    }

    private void receiverNetworkListener() {
        NetWorkBroadcastReceiver receiver = new NetWorkBroadcastReceiver();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(receiver, filter);

        receiver.setNetWorkContectedListener(new NetWorkBroadcastReceiver.NetWorkContectedListener() {
            @Override
            public void setNetWorkContent(boolean isConnected) {
                initHomeActivityView(isConnected);
            }
        });
    }

    private void initHomeActivityView(boolean isContectedNetwork) {
        setMusicActionBarTitle(R.string.title_home);
        setMusicDisplayHomeAsUpEnabled(false);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (isContectedNetwork) {
            // 组织相应的Fragment来进行显示
            replaceFragment(FRAGMENT_HOME_TAG);
        } else {
            // 判断当前的手机是否连接网络，如果没有连接网络则直接显示，为未连接网络的Fragment
            replaceFragment(FRAGMENT_UN_CONNECT_TAG);
        }
    }

    private void replaceFragment (String tag) {
        if (supportFragmentManager == null) {
            supportFragmentManager = this.getSupportFragmentManager();
        }
        Fragment fragmentCacheInstance = supportFragmentManager.findFragmentByTag(tag);
        if (fragmentCacheInstance == null) {
            fragmentCacheInstance = createFragment(tag);
        }
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragmentCacheInstance, tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private Fragment createFragment(String tag) {
        switch (tag) {
            case FRAGMENT_UN_CONNECT_TAG:
                return new UnConnectivityFragment();
            case FRAGMENT_HOME_TAG :
                return new HomeFragment();

                default:
                    return null;
        }
    }


}
