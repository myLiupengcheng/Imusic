package com.suansuan.music.focator;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.suansuan.music.Base.BaseFragment;
import com.suansuan.music.music.ui.fragment.NativeMusicFragment.AlbumFragment;
import com.suansuan.music.music.ui.fragment.NativeMusicFragment.FolderFragment;
import com.suansuan.music.music.ui.fragment.NativeMusicFragment.MusicFragment;
import com.suansuan.music.music.ui.fragment.NativeMusicFragment.SingerFragment;
import com.suansuan.music.song.list.Fragment.SongListFragment;
import com.suansuan.music.song.list.presenter.SongListFragmentPresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Fragment的工厂类
 * Created by suansuan on 2016/10/2.
 */

public class FragmentFactory {

    private static Map<Integer ,BaseFragment> map = new HashMap<>();
    private static Map<String , Fragment> songList = new HashMap<>();

    /** 获得大分类的Fragment */
    public static Fragment newInstance(String id){
        if (songList.containsKey(id)){
            return songList.get(id);
        }else {
            SongListFragment songListFragment = new SongListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(SongListFragmentPresenter.KEY_ID_CATEGORY, id);
            songListFragment.setArguments(bundle);
            songList.put(id, songListFragment);
            return songListFragment;
        }
    }

    /** 获得本地音乐的Fragment */
    public static BaseFragment newInstanceNativeMusicFragment(int position){
        position = position + 5;
        if (map.containsKey(position)){
            return map.get(position);
        }else{
            switch (position){
                case 5:
                    MusicFragment musicFragment = new MusicFragment();
                    map.put(position,musicFragment);
                    break;
                case 6:
                    SingerFragment singerFragment = new SingerFragment();
                    map.put(position,singerFragment);
                    break;
                case 7:
                    AlbumFragment albumFragment = new AlbumFragment();
                    map.put(position,albumFragment);
                    break;
                case 8:
                    FolderFragment folderFragment = new FolderFragment();
                    map.put(position,folderFragment);
                    break;
            }
            return map.get(position);
        }
    }
}

