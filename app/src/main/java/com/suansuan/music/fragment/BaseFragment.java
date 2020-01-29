package com.suansuan.music.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suansuan.music.activity.presenter.FragmentPresenter;

/**
 *
 */
public abstract class BaseFragment extends Fragment {

    private FragmentPresenter mPresenter;

    @Override
    public void onAttach(Context context) {
        if(mPresenter != null) {
            mPresenter.onAttach(context);
        }
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if(mPresenter != null) {
            mPresenter.onCreate(savedInstanceState, getArguments());
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mPresenter != null) {
            mPresenter.onCreateView(inflater, container, savedInstanceState);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if(mPresenter != null) {
            mPresenter.onActivityCreated(savedInstanceState);
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        if(mPresenter != null) {
            mPresenter.onStart();
        }
        super.onStart();
    }

    @Override
    public void onResume() {
        if(mPresenter != null) {
            mPresenter.onResume();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        if(mPresenter != null) {
            mPresenter.onPause();
        }
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if(mPresenter != null) {
            mPresenter.onDestroyView();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if(mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }

    public void setFragmentPresenter (FragmentPresenter presenter) {
        mPresenter = presenter;
    }

    public FragmentPresenter getFragmentPresenter () {
        return mPresenter;
    }
}
