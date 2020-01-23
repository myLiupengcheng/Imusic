package com.suansuan.music.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ActivityStartHelper {

    private static volatile ActivityStartHelper sInstance;
    private Context mContext;

    private ActivityStartHelper (Context context) {
        mContext = context.getApplicationContext();
    }

    public static ActivityStartHelper getActivityStartHelperInstance (Context context) {
        if (sInstance == null) {
            synchronized (ActivityStartHelper.class) {
                if (sInstance == null) {
                    sInstance = new ActivityStartHelper(context);
                }
            }
        }
        return sInstance;
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
