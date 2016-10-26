package com.suansuan.textapplication.music.ui;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.suansuan.textapplication.Base.BaseActivity;
import com.suansuan.textapplication.music.R;
import com.suansuan.textapplication.utils.SpUtils;
import com.suansuan.textapplication.utils.key.Key;

public class SplashActivity extends BaseActivity {

    private RelativeLayout mRelativeLayout;

    private AlphaAnimation mAnimation;

    private long offsetTime = 0;
    //TODO time 应该从配置文件里面读出来
    private final long TIME = 4000;

    private final int ENTRY_HOME = 0;
    private final int ENTRY_NAVIGATION = 1;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ENTRY_HOME:
                    entry(HomeActivity.class);
                    break;
                case ENTRY_NAVIGATION:
                    entry(HomeActivity.class);
                    break;
            }
        }
    };


    @Override
    protected void onAttach() {
        //设置全屏显示
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window window = SplashActivity.this.getWindow();
        window.setFlags(flag,flag);
    }

    @Override
    protected int getResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        mRelativeLayout = (RelativeLayout)findViewById(R.id.activity_splash);
        initAnimation();
    }

    /**
     * 初始化动画效果
     */
    public void initAnimation(){
        mAnimation =  new AlphaAnimation(0.5f,1f);
        mAnimation.setDuration(2000);
        mAnimation.setFillAfter(true);
        mRelativeLayout.startAnimation(mAnimation);
        mAnimation.setAnimationListener(mMusicSplashAnimationListener);
    }

    /** 检查索要进入的Activity,和停留的时间 */
    public void checkEntryHome(){
        boolean isFirst = (boolean) SpUtils.get(this, Key.isFirstEntry,false);
        long time;
        if (TIME > offsetTime){
            time = TIME - offsetTime;
        }else{
            time = offsetTime;
        }
        if(isFirst){
            mHandler.sendEmptyMessageDelayed(ENTRY_HOME,time);
        }else {
            mHandler.sendEmptyMessageDelayed(ENTRY_NAVIGATION,time);
        }

    }

    /**
     * 开启一个新的Activity
     * @param Activity : 要开启的Activity
     */
    public void entry(Class Activity){
        startNewAticity(Activity,true);
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onClick(View v, int id) {

    }

    /**
     * 动画的监听事件
     */
    private Animation.AnimationListener mMusicSplashAnimationListener = new Animation.AnimationListener(){

        @Override
        public void onAnimationStart(Animation animation) {
            offsetTime = System.currentTimeMillis();
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            offsetTime = System.currentTimeMillis() - offsetTime;
            checkEntryHome();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    } ;
}
