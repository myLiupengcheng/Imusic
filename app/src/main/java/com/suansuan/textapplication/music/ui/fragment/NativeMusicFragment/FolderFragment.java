package com.suansuan.textapplication.music.ui.fragment.NativeMusicFragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.suansuan.textapplication.domain.MusicFilePath;
import com.suansuan.textapplication.music.R;

import java.util.List;

import static android.view.View.inflate;


/**
 *
 * Created by suansuan on 2016/10/8.
 */

public class FolderFragment extends AbsNativeMusicFragment {

    private List<MusicFilePath> mFilePathMusic;
    private NativeFolderAdapter mNativeFolderAdapter;

    @Override
    protected void setListLocaltion(String l) {
        if (mFilePathMusic == null){
            return;
        }
        for (int i = 0; i < mFilePathMusic.size(); i++) {
            String firstWord = mFilePathMusic.get(i).getPathKey().charAt(0) + "";
            if (l.equals(firstWord)) {
                mListView.setSelection(i);
                break;
            }
        }
    }

    @Override
    protected void initNativeMusicView() {

    }

    @Override
    protected void initNativeMusicData() {
        List<MusicFilePath> musicFodler = mMusicEngine.getMusicFodler();
        if(musicFodler != null && musicFodler.size() > 0){
            mFilePathMusic = musicFodler;
        }
    }

    @Override
    protected void initAdapter() {
        if (mFilePathMusic == null){
            return;
        }
        if(mNativeFolderAdapter == null){
            mNativeFolderAdapter = new NativeFolderAdapter(mFilePathMusic);
        }
        mListView.setAdapter(mNativeFolderAdapter);
    }

    @Override
    protected void initViewForData() {

    }

    @Override
    public void onClick(View v) {

    }


    class NativeFolderAdapter extends NativeAdapter<MusicFilePath>{

        NativeFolderAdapter(List<MusicFilePath> list) {
            super(list);
        }

        @Override
        protected View adapterGetView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = inflate(mActivity, R.layout.item_native_album,null);
            }
            AlbumFragment.ViewHolder viewHolder = AlbumFragment.ViewHolder.getViewHolder(convertView);

            viewHolder.album_title.setText(getItem(position).getPathName());

            viewHolder.album_desc.setText(getItem(position).getSongNumber() + "首  " + getItem(position).getPath());

            viewHolder.album_icon.setImageResource(R.drawable.list_icn_folder);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewHolder.album_icon.getLayoutParams();
            layoutParams.height = 60;
            layoutParams.width = 60;

            viewHolder.album_icon.setLayoutParams(layoutParams);
            return convertView;

        }
    }
}
