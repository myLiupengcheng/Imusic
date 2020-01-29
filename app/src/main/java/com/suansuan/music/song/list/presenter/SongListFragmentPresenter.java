package com.suansuan.music.song.list.presenter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.suansuan.music.MusicConstant;
import com.suansuan.music.activity.presenter.FragmentPresenter;
import com.suansuan.music.song.list.bean.SongList;
import com.suansuan.music.song.list.parser.SongListParser;
import com.suansuanliu.core.MusicNetworkCallback;
import com.suansuanliu.core.NetWorkManager;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class SongListFragmentPresenter extends FragmentPresenter implements MusicNetworkCallback {

    public static final String KEY_ID_CATEGORY = "categoryId";

    private static final int MESSAGE_COMPLETE = 0x00002;

    private String mCategoryId;

    private SongListFragmentPresenterLoadDataCallback mCallback;
    private SongListFragmentPresenterHandler mHandler;
    private List<SongList> mSongListData;

    public SongListFragmentPresenter (SongListFragmentPresenterLoadDataCallback callback) {
        if (callback != null) {
            this.mCallback = callback;
        }
        mHandler = new SongListFragmentPresenterHandler(new WeakReference<>(this));
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Bundle arguments) {
        if(arguments != null){
            mCategoryId = arguments.getString(KEY_ID_CATEGORY, "");
        }
    }

    public void onDelayLoadingData () {
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(KEY_ID_CATEGORY, mCategoryId);
        NetWorkManager.getInstance().requestGet(MusicConstant.URI_MUSIC_SONG_LIST, objectObjectHashMap, this);
    }

    @Override
    public void onSuccess(Call call, String response) {
        if (!TextUtils.isEmpty(response)) {
            SongListParser parser = new SongListParser();
            parser.parserJsonToBean(response);
            mSongListData = parser.getParserData();
            mHandler.obtainMessage(MESSAGE_COMPLETE).sendToTarget();
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    public static class SongListFragmentPresenterHandler extends Handler {
        private WeakReference<SongListFragmentPresenter> mPresenterReference;
        public SongListFragmentPresenterHandler (WeakReference<SongListFragmentPresenter> presenterReference) {
            mPresenterReference = presenterReference;
        }
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_COMPLETE) {
                SongListFragmentPresenter songListActivityPresenter = mPresenterReference.get();
                if (songListActivityPresenter.mCallback != null) {
                    songListActivityPresenter.mCallback.loadCallbackComplete(songListActivityPresenter.mSongListData);
                    songListActivityPresenter.mSongListData = null;
                }
            }
        }
    }

    public interface SongListFragmentPresenterLoadDataCallback {
        void loadCallbackComplete (List<SongList> songListCategoryGroupList);
    }



}
