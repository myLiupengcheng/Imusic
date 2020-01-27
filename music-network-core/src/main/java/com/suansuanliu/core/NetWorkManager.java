package com.suansuanliu.core;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络库的主入口, 单例模式
 */
public class NetWorkManager {

    private static final String TAG = "suansuan";

    private volatile static OkHttpClient sClient;
    private volatile static NetWorkManager sManager;

    private  NetWorkManager () {
        sClient = new OkHttpClient();
        initNetWorkManager();
    }

    /**
     * 初始化操作
     */
    private void initNetWorkManager() {

    }

    /**
     * 单例模式的主入口。
     * @return 返回 网络库的主入口
     */
    public static NetWorkManager getInstance() {
        if (sManager == null) {
            synchronized (NetWorkManager.class) {
                if (sManager == null) {
                    sManager = new NetWorkManager();
                }
            }
        }
        return sManager;
    }

    public void requestGet (String uri, final MusicNetworkCallback musicNetworkCallback) {
        Request request = new Request.Builder().url(uri).get().build();
        Call call = sClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException exception) {
                Log.d(TAG, "onFailure: " + exception.toString());
                musicNetworkCallback.onFailure(call, exception);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
//                Log.d(TAG, "onResponse: " + responseString);
                musicNetworkCallback.onSuccess(call, responseString);
            }
        });
    }
}
