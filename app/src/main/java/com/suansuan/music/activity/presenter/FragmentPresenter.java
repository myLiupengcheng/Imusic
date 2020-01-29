package com.suansuan.music.activity.presenter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public abstract class FragmentPresenter {

    public void onAttach(Context context){}

    public void onCreate(Bundle savedInstanceState, Bundle arguments){}

    public void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){}

    public void onActivityCreated(Bundle savedInstanceState){}

    public void onStart(){}

    public void onResume(){}

    public void onPause(){}

    public void onDestroyView(){}

    public void onDestroy(){}
}
