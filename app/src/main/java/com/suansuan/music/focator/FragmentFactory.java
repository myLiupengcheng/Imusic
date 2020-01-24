package com.suansuan.music.focator;

import com.suansuan.music.Base.BaseFragment;
import com.suansuan.music.music.ui.fragment.FindFragment;
import com.suansuan.music.fragment.HomeFragment;
import com.suansuan.music.music.ui.fragment.NativeMusicFragment.AlbumFragment;
import com.suansuan.music.music.ui.fragment.NativeMusicFragment.FolderFragment;
import com.suansuan.music.music.ui.fragment.NativeMusicFragment.MusicFragment;
import com.suansuan.music.music.ui.fragment.NativeMusicFragment.SingerFragment;
import com.suansuan.music.music.ui.fragment.SubscribeFragment;
import com.suansuan.music.music.ui.fragment.VFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Fragment的工厂类
 * Created by suansuan on 2016/10/2.
 */

public class FragmentFactory {

    private static Map<Integer ,BaseFragment> map = new HashMap<>();

    /** 获得大分类的Fragment */
    public static BaseFragment newInstance(int position){
        if (map.containsKey(position)){
            return map.get(position);
        }else{
            switch (position){
                case 0:
                    HomeFragment homeFragment = new HomeFragment();
                    map.put(position,homeFragment);
                    break;
                case 1:
                    FindFragment findFragment = new FindFragment();
                    map.put(position,findFragment);
                    break;
                case 2:
                    VFragment vFragment = new VFragment();
                    map.put(position,vFragment);
                    break;
                case 3:
                    SubscribeFragment subscribeFragment = new SubscribeFragment();
                    map.put(position,subscribeFragment);
                    break;
            }
            return map.get(position);
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

