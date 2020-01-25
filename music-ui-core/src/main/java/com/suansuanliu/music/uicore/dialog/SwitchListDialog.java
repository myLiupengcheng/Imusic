package com.suansuanliu.music.uicore.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.suansuanliu.music.uicore.R;

import java.util.List;


/**
 * 带有开关选项的 List 的 Dialog
 */
public class SwitchListDialog extends DialogFragment {

    public static final String KEY_DATA_LIST = "key_data_list";

    private List<DisplayBean> mSwitchListDialogData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle arguments = this.getArguments();
        if(arguments != null){
            mSwitchListDialogData = arguments.getParcelableArrayList(KEY_DATA_LIST);
        }
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ListView listView = new ListView(getContext());
            listView.setAdapter(new SwitchListDialogAdapter());
            AlertDialog switchListDialog = new AlertDialog.Builder(getActivity()).
                    setTitle("设置网络").setView(listView).setPositiveButton("完成", null).create();
            Window window = switchListDialog.getWindow();
            if (window != null) {
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.dialogAnimation);
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);
            }
            return switchListDialog;
        }
        return super.onCreateDialog(savedInstanceState);
    }


    /**
     * 显示使用的 JavaBean
     */
    public static class DisplayBean implements Parcelable {
        public DisplayBean (String displayMessage, boolean isChecked) {
            this.displayMessage = displayMessage;
            this.isChecked = isChecked;
        }
        private String displayMessage;
        private boolean isChecked;

        public DisplayBean(Parcel in) {
            displayMessage = in.readString();
            isChecked = in.readByte() != 0;
        }

        public static final Creator<DisplayBean> CREATOR = new Creator<DisplayBean>() {
            @Override
            public DisplayBean createFromParcel(Parcel in) {
                return new DisplayBean(in);
            }

            @Override
            public DisplayBean[] newArray(int size) {
                return new DisplayBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(displayMessage);
            dest.writeByte((byte) (isChecked ? 1 : 0));
        }
    }

    /**
     * SwitchListDialog 的Adapter
     */
    private class SwitchListDialogAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mSwitchListDialogData.size();
        }

        @Override
        public DisplayBean getItem(int position) {
            return mSwitchListDialogData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_dialog_switch, parent, false);
            }
            TextView dialogMessage = convertView.findViewById(R.id.dialog_message);
            Switch dialogSwitch = convertView.findViewById(R.id.dialog_switch);
            dialogMessage.setText(getItem(position).displayMessage);
            dialogSwitch.setChecked(getItem(position).isChecked);
            return convertView;
        }
    }
}
