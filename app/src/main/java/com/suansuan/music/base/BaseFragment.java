package com.suansuan.music.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * Created by suansuan on 2016/10/22.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    private static final int DATA_LOAD_SUCESS = 1;

    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        this.mActivity = getActivity();
//        new Thread(){
//            @Override
//            public void run() {
//                findData();
//            }
//        }.start();
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        initListener();
        super.onActivityCreated(savedInstanceState);
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onResume() {
//        initViewForData();
        super.onResume();
    }

    protected abstract void findData();

    protected abstract View initView();

    protected abstract void initListener();

    protected abstract void initViewForData();

}
