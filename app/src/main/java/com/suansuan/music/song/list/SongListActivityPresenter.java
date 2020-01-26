package com.suansuan.music.song.list;

import com.suansuan.music.MusicConstant;
import com.suansuan.music.activity.presenter.ActivityPresenter;
import com.suansuan.music.song.list.parser.SongListCategoryParser;
import com.suansuanliu.core.MusicNetworkCallback;
import com.suansuanliu.core.NetWorkManager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SongListActivityPresenter implements ActivityPresenter, MusicNetworkCallback {

    private SongListCategoryParser mSongListCategoryParser;

    public SongListActivityPresenter () {
        mSongListCategoryParser = new SongListCategoryParser();
    }

    @Override
    public void onCreate() {
        NetWorkManager.getInstance().requestGet(MusicConstant.URI_MUSIC_SONG_LIST_CATEGORIES, this);
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onSuccess(Call call, Response response) {
        try {
            ResponseBody body = response.body();
            if (body != null) {
                mSongListCategoryParser.parserJsonToBean(body.string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
