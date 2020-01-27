package com.suansuan.music.hap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.InputStreamReader;
import java.io.LineNumberReader;

import com.suansuan.music.hap.loging.MusicLog;
/**
 * 公共功能，一些三方判断都放在这里面
 *
 */
@SuppressWarnings("all")
public class MusicCommonFeaturesUtil {

    private static final String TAG = "MusicCommonFeaturesUtil";
    private static final String BAD_ANDROID_ID = "9774d56d682e549c";

    private static final String ANDROID = "Android";

    private static final String WIFI = "WIFI";
    private static final String TIMEOUT = "TimeOut";
    private static final String UN_KNOWN = "UnKnown";
    private static final String UN_CONNECT = "UnConnect";

    private static final String GET_MAC_COMMAND = "cat /sys/class/net/wlan0/address";

    private static String sMacAddress;
    private static String sAndroidIEMI;
    private static String sAndroidID;

    /**
     * 判断当前的线程是否是子线程
     *
     * @return true 代表在主线程，false代表不是在主线程
     */
    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 获取手机的 IEMI  Android Q 版本以后，获取不到，建议使用三方ID 进行拉活判断
     * 或者 Android id 进行代替 IEMI
     *
     * @param context Android 上下文
     * @return 返回手机的IEMI
     */
    @SuppressLint("MissingPermission")
    public static String getIEMI (Context context) {
        if (!TextUtils.isEmpty(sAndroidIEMI) || !sAndroidIEMI.equals(UN_KNOWN)) {
            return sAndroidIEMI;
        }
        try {
            if (context == null) {
                sAndroidIEMI = UN_KNOWN;
            } else {
                TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                sAndroidIEMI = manager.getDeviceId();
            }
        } catch (Throwable ex) {
            sAndroidIEMI = UN_KNOWN;
        }
        return sAndroidIEMI;
    }

    /**
     * 对于手机唯一标识符。 对于广告用例，
     * 请使用AdvertisingIdClient $ Info＃getId；对于分析，请使用InstanceId＃getId。 问题ID：HardwareIds
     *
     * @param context Android 上下文
     * @return 返回手机的IEMI
     */
    public static String getAndroidID (Context context) {
        if (!TextUtils.isEmpty(sAndroidID) || !sAndroidID.equals(UN_KNOWN)) {
            return sAndroidID;
        }
        try {
            sAndroidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            // 9774d56d682e549c 这个标识符为一些低端手机
            if (BAD_ANDROID_ID.equalsIgnoreCase(sAndroidID) || TextUtils.isEmpty(sAndroidID)) {
                // 如果手机获取不到相应的 Android ID 则去获取手机的IEMI
                sAndroidID = getIEMI(context);
            }
        } catch (Throwable ex) {
            sAndroidID = UN_KNOWN;
        }
        return sAndroidID;
    }

    /**
     * 获取手机的Mac地址
     * @return 当前手机的 Mac 地址
     */
    public static String getMacAddress () {
        if (!TextUtils.isEmpty(sMacAddress) || !sMacAddress.equals(UN_KNOWN)) {
            return sMacAddress;
        }
        String str = "";
        try {
            Process process = Runtime.getRuntime().exec(GET_MAC_COMMAND);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            for (; null != str; ) {
                str = lineNumberReader.readLine();
                if (str != null) {
                    sMacAddress = str.trim();// 去空格
                    break;
                }
            }
        } catch (Throwable ex) {
            // TODO 加Log
            sMacAddress = UN_KNOWN;
        }
        return sMacAddress;
    }

    /**
     * 判断当前的手机是否处于Debug模式下
     *
     * @param context Android
     * @return
     */
    public static boolean isApkInDebug (Context context) {
        try {
            if (context == null) {
                return false;
            } else {
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                return (applicationInfo.flags | ApplicationInfo.FLAG_DEBUGGABLE) != 0;
            }
        } catch (Throwable ex) {
            // TODO: 加Log
            return false;
        }
    }

    /**
     * 判断当前手机是否联网
     * @param context
     * @return
     */
    public static boolean isConnection (Context context) {
        NetworkInfo networkInfo = getNetworInfo(context);
        return networkInfo != null && networkInfo.isConnected();
    }

    public static NetworkInfo getNetworInfo (Context context) {
        try {
            if (context == null) {
                MusicLog.w(TAG, "getNetworInfo, context is null");
                return null;
            } else {
                ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                return manager.getActiveNetworkInfo();
            }
        } catch (Throwable ex) {
            MusicLog.w(TAG, "getNetworInfo, ConnectivityManager.getActiveNetworkInfo excption");
            return null;
        }
    }

}
