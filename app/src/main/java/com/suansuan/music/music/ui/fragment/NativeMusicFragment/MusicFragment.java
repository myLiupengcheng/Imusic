package com.suansuan.music.music.ui.fragment.NativeMusicFragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.suansuan.music.R;
import com.suansuan.music.bean.Music;

import java.util.Collections;
import java.util.List;

import static android.view.View.inflate;

/**
 * 单曲Fragment
 * Created by suansuan on 2016/10/8.
 */

public class MusicFragment extends AbsNativeMusicFragment {

    private List<Music> mListmusic;
    NativeMusicAdapter nativeMusicAdapter;

    @Override
    protected void initNativeMusicData() {
        List<Music> music = mMusicEngine.findAllMusic();
        if(music != null){
            mListmusic = music;
            Collections.sort(mListmusic);
        }
    }

    @Override
    protected void setListLocaltion(String letter) {
        if (mListmusic == null){
            return;
        }
        for (int i = 0; i < mListmusic.size(); i++) {
            String firstWord = mListmusic.get(i).getPinYing().charAt(0) + "";
            if (letter.equals(firstWord)) {
                mListView.setSelection(i);
                break;
            }
        }
    }



    @Override
    protected void initAdapter() {
        if (mListmusic == null){
            return;
        }
        if(nativeMusicAdapter == null){
            nativeMusicAdapter = new NativeMusicAdapter(mListmusic);
        }
        mListView.setAdapter(nativeMusicAdapter);
    }

    @Override
    protected void initNativeMusicView() {

    }

    @Override
    protected void initViewForData() {

    }

    @Override
    public void onClick(View v) {

    }

    class NativeMusicAdapter extends NativeAdapter<Music>{

        private char lastPinying ;

        public NativeMusicAdapter(List<Music> list) {
            super(list);
        }

        @Override
        protected View adapterGetView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = inflate(mActivity, R.layout.item_native_music,null);
            }
            ViewHolder viewHolder = ViewHolder.getViewHolder(convertView);
            viewHolder.native_music_title.setText(getItem(position).getTitle());
            viewHolder.native_music_desc.setText(getItem(position).getSinger()+"/"+getItem(position).getAlbum());
            return convertView;
        }
    }

    static class ViewHolder{
        TextView native_music_title;
        TextView native_music_desc;
        ImageView native_music_btn;

        ViewHolder(View view){
            native_music_title = (TextView) view.findViewById(R.id.native_music_title);
            native_music_desc = (TextView) view.findViewById(R.id.native_music_desc);
            native_music_btn = (ImageView) view.findViewById(R.id.native_music_btn);
        }

        public static ViewHolder getViewHolder(View view){
            ViewHolder holder = (ViewHolder) view.getTag();
            if(holder == null){
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            return holder;
        }
    }

}
