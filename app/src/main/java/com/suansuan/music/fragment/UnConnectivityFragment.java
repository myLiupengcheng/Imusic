package com.suansuan.music.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suansuan.music.R;
import com.suansuan.music.uicore.dialog.SwitchListDialog;

import java.util.ArrayList;

public class UnConnectivityFragment extends BaseFragment implements View.OnClickListener {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_pager_un_connectivity, container, false);
        rootView.findViewById(R.id.connect_network).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        SwitchListDialog switchListDialog = new SwitchListDialog();
        Bundle bundle = new Bundle();
        ArrayList<SwitchListDialog.DisplayBean> displayBeans = new ArrayList<>();
        SwitchListDialog.DisplayBean data = new SwitchListDialog.DisplayBean("移动数据", false);
        SwitchListDialog.DisplayBean wifi = new SwitchListDialog.DisplayBean("WIFI", false);
        displayBeans.add(data);
        displayBeans.add(wifi);
        bundle.putParcelableArrayList(SwitchListDialog.KEY_DATA_LIST, displayBeans);
        switchListDialog.setArguments(bundle);
        switchListDialog.show(getFragmentManager(), "sss");
    }
}
