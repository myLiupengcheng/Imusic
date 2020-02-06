package com.suansuan.music.song.list.presenter;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.suansuan.music.MusicConstant;
import com.suansuan.music.activity.presenter.ActivityPresenter;
import com.suansuan.music.song.list.bean.SongListCategoryGroup;
import com.suansuan.music.song.list.parser.SongListCategoryParser;
import com.suansuanliu.core.MusicNetworkCallback;
import com.suansuanliu.core.NetWorkManager;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import okhttp3.Call;

public class SongListCategoryActivityPresenter implements ActivityPresenter, MusicNetworkCallback {

    private List<SongListCategoryGroup> songListCategoryGroupList;

    private static final int MESSAGE_COMPLETE = 0x00001;

    SongListCategoryActivityPresenterHandler mHandler;

    @Override
    public void onCreate() {
        NetWorkManager.getInstance().requestGet(MusicConstant.URI_MUSIC_SONG_LIST_CATEGORIES, this);
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onSuccess(Call call, String response) {
        if (!TextUtils.isDigitsOnly(response)) {
            SongListCategoryParser songListCategoryParser = new SongListCategoryParser();
            songListCategoryParser.parserJsonToBean(response);
            songListCategoryGroupList = songListCategoryParser.getParserData();
            mHandler.obtainMessage(MESSAGE_COMPLETE).sendToTarget();
        }
    }

    public static class SongListCategoryActivityPresenterHandler extends Handler {
        private WeakReference<SongListCategoryActivityPresenter> mPresenterReference;
        public SongListCategoryActivityPresenterHandler (WeakReference<SongListCategoryActivityPresenter> presenterReference) {
            mPresenterReference = presenterReference;
        }
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_COMPLETE) {
                SongListCategoryActivityPresenter songListActivityPresenter = mPresenterReference.get();
                if (songListActivityPresenter.mCallback != null) {
                    songListActivityPresenter.mCallback.loadCallbackComplete(songListActivityPresenter.songListCategoryGroupList);
                    songListActivityPresenter.songListCategoryGroupList = null;
                }
            }
        }
    }
}
