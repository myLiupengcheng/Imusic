package com.suansuan.textapplication.engine;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.suansuan.textapplication.domain.Album;
import com.suansuan.textapplication.domain.Artists;
import com.suansuan.textapplication.domain.Music;
import com.suansuan.textapplication.domain.MusicFilePath;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by suansuan on 2016/10/15.
 */
public class NativeEngine {

    private static Context mContext;

    /** 访问音乐的Uri */
    private Uri mMusicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    /** 访问专辑的Uri */
    private Uri mAlbumUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

    /** 访问歌手的Uri */
    private Uri mArtistsUri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;

    /** 含有自生的引用 */
    private static NativeEngine mMusicEngine;

    /** 构造方法私有化 */
    private NativeEngine(){}

    /** 暴露的单利构造方法 */
    public static NativeEngine newInstance(Context context){
        mContext = context;
        if (mMusicEngine != null){
            return mMusicEngine;
        }
        return new NativeEngine();
    }

    /** 得到所有的音乐文件 */
    public List<Music> findAllMusic(){
        List<Music> list = new ArrayList<Music>();
        String[] projection = {
                MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.TITLE_KEY,
                MediaStore.Audio.Media.DURATION,MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.YEAR, MediaStore.Audio.Media.MIME_TYPE,
                MediaStore.Audio.Media.DATA};

        String selection = MediaStore.Audio.Media.MIME_TYPE + "=? " +"or "+ MediaStore.Audio.Media.MIME_TYPE + "=?";
        String[] selectionArgs = new String[] { "audio/mpeg", "audio/x-ms-wma" };

        Cursor cursor = mContext.getContentResolver().query(mMusicUri, projection, selection, selectionArgs, null);
        if(cursor == null){  return null; }
        while(cursor.moveToNext()){
            Music music = new Music();
            music.formCursor(cursor);
            list.add(music);
        }
        cursor.close();
        return  list;
    }

    /** 得到所有歌曲的文件夹 */
    public List<MusicFilePath> getMusicFodler(){
        String[] projection = { MediaStore.Audio.Media.DATA };
        String selection = MediaStore.Audio.Media.MIME_TYPE + "=?"+"or " + MediaStore.Audio.Media.MIME_TYPE + "=?";
        String[] selectionArgs = new String[]{ "audio/mpeg", "audio/x-ms-wma" };
        Cursor cursor = mContext.getContentResolver().query(mMusicUri, projection, selection, selectionArgs, null);
        if(cursor == null) return null;
        return parseCursor(cursor);
    }


    /** 解析Cursor生成相应的对象 */
    private List<MusicFilePath> parseCursor(Cursor cursor){
        List<MusicFilePath> list = new ArrayList<>();
        String temp = "";
        while(cursor.moveToNext()){
            String path = cursor.getString(0).substring(0, cursor.getString(0).lastIndexOf("/"));
            MusicFilePath musicFilePath = null;
            if(!path.equals(temp)){
                musicFilePath = new MusicFilePath();
                toBean(path, musicFilePath);
                list.add(musicFilePath);
            }else{
                for( int i = 0; i < list.size(); i++ ){
                    if(list.get(i).getPath().equals(path)){
                        list.get(i).setSongNumber(list.get(i).getSongNumber() + 1);
                    }
                }
            }
            temp = path;
        }
        return list;
    }


    private void toBean(String path, MusicFilePath musicFilePath){
        musicFilePath.setPath(path);
        String pathName = path.substring(path.lastIndexOf("/") + 1);
        musicFilePath.setPathName(pathName);
    }


    /** 得到所有的专辑文件 */
    public List<Album> findAllAlbum(){
        List<Album> list = new ArrayList<>();
        String[] projection = {
                MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ALBUM_KEY,
                MediaStore.Audio.Albums.ARTIST,
                MediaStore.Audio.Albums.NUMBER_OF_SONGS,
                MediaStore.Audio.Albums.ALBUM_ART};

        Cursor cursor = mContext.getContentResolver().query(mAlbumUri, projection, null, null, null);
        while(cursor.moveToNext()){
            Album album = new Album();
            album.formCursor(cursor);
            list.add(album);
        }
        cursor.close();
        return  list;
    }

    /** 得到所有的歌手文件 */
    public List<Artists> findAllArtists(){
        List<Artists> list = new ArrayList<>();
        Cursor cursor = mContext.getContentResolver().query(mArtistsUri, null, null, null, null);
        while(cursor.moveToNext()){
            Artists artists = new Artists();
            artists.formCursor(cursor);
            list.add(artists);
        }
        cursor.close();
        return  list;
    }



    public void text(){
        Uri uri = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
        while(cursor.moveToNext()){
            Log.i("suansuan","++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            for(int i = 0; i < cursor.getColumnCount(); i++ ){
                Log.i("suansuan", cursor.getColumnName(i)+"=========="+cursor.getString(i));
            }
            Log.i("suansuan", "+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        cursor.close();
    }

}
