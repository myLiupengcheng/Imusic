package com.suansuan.music.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.suansuan.music.utils.StatusBarUtils;

public class ActivityHelper {

    private static volatile ActivityHelper sInstance;
    private Context mContext;

    private ActivityHelper(Context context) {
        mContext = context.getApplicationContext();
    }

    public static ActivityHelper getActivityStartHelperInstance (Context context) {
        if (sInstance == null) {
            synchronized (ActivityHelper.class) {
                if (sInstance == null) {
                    sInstance = new ActivityHelper(context);
                }
            }
        }
        return sInstance;
    }

    /**
     * 设置当前的 Activity 的状态栏是透明状态
     * @param activity ：要被设置的Activity
     */
    public void setStatusBarTransparency (Activity activity) {
        StatusBarUtils.transparencyBar(activity);
    }


    /**
     * 外部启动相应的Activity
     * @param intent 跳转的intent
     */
    public void startActivity (Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    /**
     * 内部启动相应的Activity
     * @param intent 跳转的intent
     */
    public void startActivity (Activity activity, Intent intent) {
        activity.startActivity(intent);
    }
}
