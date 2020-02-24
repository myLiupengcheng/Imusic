package com.suansuan.music.uicore.pic;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.suansuan.music.uicore.pic.drawable.PosterPlaceholderDrawable;
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

    public Drawable getPosterPlaceholderDrawable () {

        PosterPlaceholderDrawable posterPlaceholderDrawable = new PosterPlaceholderDrawable(mContext);
        return posterPlaceholderDrawable;
    }
}
