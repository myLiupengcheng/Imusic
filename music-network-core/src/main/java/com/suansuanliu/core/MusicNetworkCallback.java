package com.suansuanliu.core;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public interface MusicNetworkCallback {

    void onFailure(Call call, IOException e);

    void onSuccess(Call call, Response response);
}
