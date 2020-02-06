package com.suansuan.music.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suansuan.music.MusicApplication;
import com.suansuan.music.R;
import com.suansuan.music.helper.ActivityHelper;
import com.suansuan.music.song.list.SongListMainActivity;

/**
 *
 * Created by suansuan on 2016/10/2.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private ActivityHelper mActivityHelper;

    @Override
    public void onAttach(Context context) {
        mActivityHelper = MusicApplication.getInstance().getActivityHelper();
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        rootView.findViewById(R.id.songList).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.songList && getActivity() != null) {
            Intent intent = new Intent(getActivity(), SongListMainActivity.class);
            mActivityHelper.startActivity(getActivity(), intent);
        }
    }
}
