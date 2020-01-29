package com.suansuanliu.core;

import android.net.Uri;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
        requestGet(uri, null, musicNetworkCallback);
    }

    public void requestGet (String uriString, Map<String, String> bundle, final MusicNetworkCallback musicNetworkCallback) {
        Uri uri = bundle != null ? createUri(uriString, bundle) : Uri.parse(uriString);
        Request request = new Request.Builder().url(uri.toString()).get().build();
        Call call = sClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException exception) {
                musicNetworkCallback.onFailure(call, exception);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                musicNetworkCallback.onSuccess(call, responseString);
            }
        });
    }

    private Uri createUri(String uri, Map<String, String> bundle) {
        Uri.Builder builder = Uri.parse(uri).buildUpon();
        Set<Map.Entry<String, String>> entries = bundle.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        for (Iterator<Map.Entry<String, String>> it = iterator; it.hasNext(); ) {
            Map.Entry<String, String> item = it.next();
            builder.appendQueryParameter(item.getKey(), item.getValue());
        }
        return builder.build();
    }

}
