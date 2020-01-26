package com.suansuan.music.bean;

import android.database.Cursor;
import android.util.Log;

/**
 * 专辑列表的JavaBean
 I/suansuan(27912): ---->>>>>>>>._id = 108
 I/suansuan(27912): ---->>>>>>>>.album = 赵小雷
 I/suansuan(27912): ---->>>>>>>>.album_key = ZHAO.XIAO.LEI.1161250
 I/suansuan(27912): ---->>>>>>>>.artist = 赵雷
 I/suansuan(27912): ---->>>>>>>>.artist_id = 73
 I/suansuan(27912): ---->>>>>>>>.artist_key = ZHAO.LEI.
 I/suansuan(27912): ---->>>>>>>>.numsongs = 5
 I/suansuan(27912): ---->>>>>>>>.album_art = /storage/sdcard0/Android/data/com.android.providers.media/albumthumbs/1456414612881
 * Created by suansuan on 2016/10/14.
 */

public class Album implements Comparable<Album>{

    private int _id;
    private String albumName;
    private String albumKey;
    private String albumArtist;
    private int numSongs;
    private String imagePath;


    @Override
    public String toString() {
        return "Album{" +
                "_id=" + _id +
                ", albumName='" + albumName + '\'' +
                ", albumKey='" + albumKey + '\'' +
                ", albumArtist='" + albumArtist + '\'' +
                ", numSongs=" + numSongs +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAlbumKey(String albumKey) {
        this.albumKey = albumKey;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public void setNumSongs(int numSongs) {
        this.numSongs = numSongs;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int get_id() {
        return _id;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public String getAlbumKey() {
        return albumKey;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getNumSongs() {
        return numSongs;
    }

    public void formCursor(Cursor cursor){
        if(cursor != null){
            this.set_id(cursor.getInt(0));
            this.setAlbumName(cursor.getString(1));
            this.setAlbumKey(cursor.getString(2));
            this.setAlbumArtist(cursor.getString(3));
            this.setNumSongs(cursor.getInt(4));
            this.setImagePath(cursor.getString(5));

        }else{
            Log.i("Album","Album formCursor(); cursor is null");
        }
    }

    @Override
    public int compareTo(Album another) {
        return getAlbumKey().compareTo(another.getAlbumKey());
    }
}
