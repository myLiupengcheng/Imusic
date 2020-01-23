package com.suansuan.music.engine;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

import com.suansuan.music.domain.Album;
import com.suansuan.music.domain.Music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 单例模式，使用静态内部类，延时加载
 * Created by suansuan on 2016/10/9.
 */
@SuppressWarnings("ALL")
public class NativeMusicEngine {

    public static final int FIND_ALL_MUSIC_INFO = 0;

    public static final int FIND_ALL_ALBUM_INFO = 1;

    public static final int FIND_MUSIC_BY_ALBUM_ID = 2;

    private Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    private static Context mContext;

    private NativeMusicEngine(){
    }

    private static class StaticInnerClass{
        private static NativeMusicEngine instance = new NativeMusicEngine();
    }

    //方法没有同步，调用效率高！
    public static NativeMusicEngine getInstance(Context context){
        mContext = context;
        return StaticInnerClass.instance;
    }

    private AsyncQueryHandler mAsyncQueryHandler = new AsyncQueryHandler(mContext.getContentResolver()){
        /** 这个方法会用行在主线程 */
        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            switch (token){
                case FIND_ALL_MUSIC_INFO:
                    List<Music> musics = toListMusic(cursor);
                    sendMsg(FIND_ALL_MUSIC_INFO,(Handler)cookie,musics);
                    break;
                case FIND_ALL_ALBUM_INFO:
                    List<Album> alba = toListAlbum(cursor);
                    sendMsg(FIND_ALL_ALBUM_INFO,(Handler)cookie,alba);
                    break;
                case FIND_MUSIC_BY_ALBUM_ID:

                    break;
            }
        }
    };

    /** 获取所有的音乐信息，耗时操作，应该使用异步 */
    public void getMusicInfo(final Handler handler){
        int token = FIND_ALL_MUSIC_INFO;			// 相当于Message.what
        Object cookie = handler;	                // 相当于Message.obj
        String[] projection = new String[] {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.YEAR,
                MediaStore.Audio.Media.MIME_TYPE,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DATA };
        String selection = MediaStore.Audio.Media.MIME_TYPE + "=? " +"or "+ MediaStore.Audio.Media.MIME_TYPE + "=?";
        String[] selectionArgs = new String[] { "audio/mpeg", "audio/x-ms-wma" };
        String orderBy = MediaStore.Audio.Media.TITLE + " ASC";
        /** 这个方法会运行在子线程 */
        mAsyncQueryHandler.startQuery(token,cookie,uri,projection,selection,selectionArgs,orderBy);
    }


    /** 查找专辑 */
    public void getAlbum(final Handler handler) {
        int token = FIND_ALL_ALBUM_INFO;
        Object cookie = handler;
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_KEY,
//                MediaStore.Audio.Media.ALBUM_ARTIST,
                "album_artist",
        };
        mAsyncQueryHandler.startQuery(token,cookie,uri,projection,null,null,null);
    }

    /** 根据专辑ID查找歌曲 */
    private void getMusicByAlbumId(int album_id , final Handler handler){
        int token = FIND_MUSIC_BY_ALBUM_ID;
        Object cookie = handler;
        String[] projection = new String[] {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.YEAR,
                MediaStore.Audio.Media.MIME_TYPE,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DATA };
        String selection = MediaStore.Audio.Media.MIME_TYPE + "=? " +"or "
                + MediaStore.Audio.Media.MIME_TYPE + "=?"
                + MediaStore.Audio.Media.ALBUM_ID + "=?";
        String[] selectionArgs = new String[] { "audio/mpeg", "audio/x-ms-wma" , album_id+""};
        String orderBy = MediaStore.Audio.Media.TITLE + " ASC";
        mAsyncQueryHandler.startQuery(token,cookie,uri,projection,null,null,null);
    }

    /** 将查找出来的musicInfo封装到List当中 */
    private List<Music> toListMusic (Cursor cursor){
        List<Music> list = new ArrayList<>();
        while(cursor.moveToNext()){
            Music music = new Music();
            music.formCursor(cursor);
            list.add(music);
        }
        Collections.sort(list);
        return list;
    }

    /** 将查找出来的musicInfo封装到List当中 */
    private List<Album> toListAlbum (Cursor cursor){
        List<Album> list = new ArrayList<>();
        while(cursor.moveToNext()){
            Album album = new Album();
            album.formCursor(cursor);
            list.add(album);
        }
        Collections.sort(list);
        return list;
    }



    /** 发送消息 */
    public void sendMsg(int state , Handler handler ,Object values){
        Message message = handler.obtainMessage();
        message.what = state ;
        message.obj = values;
        handler.sendMessage(message);
    }

}
