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

public class SongListActivityPresenter implements ActivityPresenter, MusicNetworkCallback {

    private static final String TAG = "SongListActivityPresenter";

    private static final int MESSAGE_COMPLETE = 0x00001;

    private SongListActivityPresenterLoadDataCallback mCallback;
    private SongListActivityPresenterHandler mHandler;

    private List<SongListCategoryGroup> songListCategoryGroupList;
    private List<SongListCategoryGroup.SongListCategory> songListCategories;

    public SongListActivityPresenter(SongListActivityPresenterLoadDataCallback callback) {
        mCallback = callback;
        mHandler = new SongListActivityPresenterHandler(new WeakReference<>(this));
    }

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
            songListCategories = songListCategoryParser.interceptorData(songListCategoryGroupList);
            mHandler.obtainMessage(MESSAGE_COMPLETE).sendToTarget();
        }
    }

    public interface SongListActivityPresenterLoadDataCallback {
        void loadCallbackComplete (List<SongListCategoryGroup> songListCategoryGroupList, List<SongListCategoryGroup.SongListCategory> songListCategories);
    }

    public static class SongListActivityPresenterHandler extends Handler {
        private WeakReference<SongListActivityPresenter> mPresenterReference;
        public SongListActivityPresenterHandler (WeakReference<SongListActivityPresenter> presenterReference) {
            mPresenterReference = presenterReference;
        }
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_COMPLETE) {
                SongListActivityPresenter songListActivityPresenter = mPresenterReference.get();
                if (songListActivityPresenter.mCallback != null) {
                    songListActivityPresenter.mCallback.loadCallbackComplete(songListActivityPresenter.songListCategoryGroupList, songListActivityPresenter.songListCategories);
                    songListActivityPresenter.songListCategoryGroupList = null;
                    songListActivityPresenter.songListCategories = null;
                }
            }
        }
    }
}
