package com.suansuan.music.domain;

import android.database.Cursor;

/**
 * 音乐歌手的Model
 * Created by suansuan on 2016/10/22.
 *  I/suansuan: _id==========3
    I/suansuan: artist==========Daya
    I/suansuan: artist_key==========			Á	
    I/suansuan: number_of_albums==========1
    I/suansuan: number_of_tracks==========3
 */

public class Artists implements Comparable<Artists>{

    private int _id;
    private String name;
    private String nameKey;
    private int number_of_albums;
    private int number_of_tracks;
    private String artists_art;


    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getNameKey() {
        return nameKey;
    }

    public int getNumber_of_albums() {
        return number_of_albums;
    }

    public int getNumber_of_tracks() {
        return number_of_tracks;
    }

    public String getArtists_art() {
        return artists_art;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public void setNumber_of_albums(int number_of_albums) {
        this.number_of_albums = number_of_albums;
    }

    public void setNumber_of_tracks(int number_of_tracks) {
        this.number_of_tracks = number_of_tracks;
    }

    public void setArtists_art(String artists_art) {
        this.artists_art = artists_art;
    }

    public void formCursor(Cursor cursor) {
        set_id(cursor.getInt(0));
        setName(cursor.getString(1));
        setNameKey(cursor.getString(2));
        setNumber_of_albums(cursor.getInt(3));
        setNumber_of_tracks(cursor.getInt(4));
    }

    @Override
    public int compareTo(Artists another) {
        return getNameKey().compareTo(another.getNameKey());
    }
}
