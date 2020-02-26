package com.suansuan.music.uicore.pic;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.suansuanliu.music.uicore.R;

public class DrawableManager {

    private static volatile DrawableManager sDrawableManager;
    private Context mContext;

    private DrawableManager (Context context) {
        mContext = context.getApplicationContext();
    }

    public static DrawableManager newInstance (Context context) {
        if (sDrawableManager == null ) {
            synchronized (DrawableManager.class) {
                if (sDrawableManager == null) {
                    sDrawableManager = new DrawableManager(context);
                }
            }
        }
        return sDrawableManager;
    }

    /**
     * 获取没有加载完成的 Drawable 的占位图片
     * @return 没有加载完成的占位图片
     */
    public Drawable getPosterPlaceholderDrawable () {
        return mContext.getResources().getDrawable(R.drawable.placeholder_background);
    }

//    /**
//     * 获取没有加载完成的 Drawable 的占位图片
//     * @return 没有加载完成的占位图片
//     */
//    public Drawable getPosterPlaceholderDrawable () {
//        return mContext.getResources().getDrawable(R.drawable.placeholder_background);
//    }
}
