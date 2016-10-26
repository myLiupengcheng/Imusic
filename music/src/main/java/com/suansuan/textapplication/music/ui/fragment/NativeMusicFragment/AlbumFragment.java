package com.suansuan.textapplication.music.ui.fragment.NativeMusicFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.suansuan.textapplication.domain.Album;
import com.suansuan.textapplication.music.R;

import java.util.Collections;
import java.util.List;

import static android.view.View.inflate;

/**
 *
 * Created by suansuan on 2016/10/8.
 */

public class AlbumFragment extends AbsNativeMusicFragment {

    private List<Album> mListAlbum;
    private NativeAlbumAdapter nativeAlbumAdapter;

    @Override
    protected void setListLocaltion(String l) {
        if (mListAlbum == null){
            return;
        }
        for (int i = 0; i < mListAlbum.size(); i++) {
            String firstWord = mListAlbum.get(i).getAlbumKey().charAt(0) + "";
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
        List<Album> album = mMusicEngine.findAllAlbum();
        if(album != null){
            mListAlbum = album;
            Collections.sort(mListAlbum);
        }
    }

    @Override
    protected void initAdapter() {
        if (mListAlbum == null){
            return;
        }
        if(nativeAlbumAdapter == null){
            nativeAlbumAdapter = new NativeAlbumAdapter(mListAlbum);
        }
        mListView.setAdapter(nativeAlbumAdapter);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initViewForData() {

    }

    /**  */
    class NativeAlbumAdapter extends NativeAdapter<Album>{

        public NativeAlbumAdapter(List<Album> list) {
            super(list);
        }

        @Override
        protected View adapterGetView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = inflate(mActivity, R.layout.item_native_album,null);
            }
            ViewHolder viewHolder = ViewHolder.getViewHolder(convertView);
            viewHolder.album_title.setText(getItem(position).getAlbumName());
            viewHolder.album_desc.setText("("+getItem(position).getNumSongs()+"首)   "+getItem(position).getAlbumArtist());
            String imagePath = getItem(position).getImagePath();
            if(imagePath != null){
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                viewHolder.album_icon.setImageBitmap(bitmap);
            }else{
                viewHolder.album_icon.setImageResource(R.mipmap.native_album_default);
            }
            return convertView;

        }
    }

    static class ViewHolder{
        TextView album_title;
        TextView album_desc;
        ImageView album_icon;
        ImageView imageView;

        ViewHolder(View view){
            album_title = (TextView) view.findViewById(R.id.album_title);
            album_desc = (TextView) view.findViewById(R.id.album_desc);
            album_icon = (ImageView) view.findViewById(R.id.album_icon);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }

        public static ViewHolder getViewHolder(View view){
            AlbumFragment.ViewHolder holder = (AlbumFragment.ViewHolder) view.getTag();
            if(holder == null){
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            return holder;
        }
    }

}
