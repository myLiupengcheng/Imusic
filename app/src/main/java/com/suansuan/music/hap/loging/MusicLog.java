package com.suansuan.music.hap.loging;

import android.util.Log;
import java.util.Locale;

/**
 * 抽象出来相应的Log类
 *
 */
public class MusicLog    {

    private static final String TAG = "MusicLog";
    private static final String APP_TAG = "IMusic";
    private static final boolean IS_SWITCH = true;

    private boolean isDeBug;

//    public MusicLog (Context context) {
//        isDeBug = MusicCommonFeaturesUtil.isApkInDebug(context);
//    }

    public static void v(String Tag, String message) {
        if (IS_SWITCH) {
            Log.v(APP_TAG, String.format(Locale.ROOT, "%s - %s", Tag, message));
        }
    }

    public static void d(String Tag, String message) {
        if (IS_SWITCH) {
            Log.d(APP_TAG, String.format(Locale.ROOT, "%s - %s", Tag, message));
        }
    }

    public static void i(String Tag, String message) {
        if (IS_SWITCH) {
            Log.i(APP_TAG, String.format(Locale.ROOT, "%s - %s", Tag, message));
        }
    }

    public static void w(String Tag, String message) {
        if (IS_SWITCH) {
            Log.w(APP_TAG, String.format(Locale.ROOT, "%s - %s", Tag, message));
        }
    }

    public static void e(String Tag, String message) {
        if (IS_SWITCH) {
            Log.e(APP_TAG, String.format(Locale.ROOT, "%s - %s", Tag, message));
        }
    }
}
