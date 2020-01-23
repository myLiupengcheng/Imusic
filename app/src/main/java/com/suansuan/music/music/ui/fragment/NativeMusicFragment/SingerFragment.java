package com.suansuan.music.music.ui.fragment.NativeMusicFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;

import com.suansuan.music.R;
import com.suansuan.music.domain.Artists;

import java.util.Collections;
import java.util.List;

import static android.view.View.inflate;


/**
 *
 * Created by suansuan on 2016/10/8.
 */

public class SingerFragment extends AbsNativeMusicFragment {

    private List<Artists> mListArtists;
    private NativeArtistsAdapter nativeArtistsAdapter;

    @Override
    protected void setListLocaltion(String l) {
        if (mListArtists == null){
            return;
        }
        for (int i = 0; i < mListArtists.size(); i++) {
            String firstWord = mListArtists.get(i).getNameKey().charAt(0) + "";
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
        List<Artists> allArtists = mMusicEngine.findAllArtists();
        if(allArtists != null){
            mListArtists = allArtists;
            Collections.sort(mListArtists);
        }
    }

    @Override
    protected void initAdapter() {
        if (mListArtists == null){
            return;
        }
        if(nativeArtistsAdapter == null){
            nativeArtistsAdapter = new NativeArtistsAdapter(mListArtists);
        }
        mListView.setAdapter(nativeArtistsAdapter);
    }

    @Override
    protected void initViewForData() {

    }

    @Override
    public void onClick(View v) {

    }

    /**  */
    class NativeArtistsAdapter extends NativeAdapter<Artists>{

        NativeArtistsAdapter(List<Artists> list) {
            super(list);
        }

        @Override
        protected View adapterGetView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = inflate(mActivity, R.layout.item_native_album,null);
            }
            AlbumFragment.ViewHolder viewHolder = AlbumFragment.ViewHolder.getViewHolder(convertView);
            viewHolder.album_title.setText(getItem(position).getName());
            viewHolder.album_desc.setText("歌曲：("+getItem(position).getNumber_of_tracks()+"首)   "+"/ 专辑：("+getItem(position).getNumber_of_albums()+"个)   ");
            String imagePath = getItem(position).getArtists_art();
            if(imagePath != null){
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                viewHolder.album_icon.setImageBitmap(bitmap);
            }else{
                viewHolder.album_icon.setImageResource(R.mipmap.native_album_default);
            }
            return convertView;

        }
    }
}
