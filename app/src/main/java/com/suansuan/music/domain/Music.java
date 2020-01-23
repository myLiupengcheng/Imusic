package com.suansuan.music.domain;

import android.database.Cursor;

/**
 *
 * Created by suansuan on 2016/10/9.
 */

public class Music implements Comparable<Music> {

    /** 歌曲的唯一标示 */
    private int id;

    /** 歌曲的文件名 */
    private String fileName;

    /** 歌曲的标题 */
    private String title;

    private String pinYing;

    /** 歌曲的总时长 */
    private int duration;

    /** 歌曲的作者 */
    private String singer;

    /** 歌曲所属专辑 */
    private String album;

    /** 歌曲的发行年月 */
    private String year;

    /** 歌曲的文件格式  例如：MAP3 WAM */
    private String type;

    /** 歌曲文件的大小 */
    private String size;

    /** 歌曲文件的路径 */
    private String fileUrl;

    /** 通过Cursor自动生成Music对象 */
    public void formCursor(Cursor cursor){
        this.setId(cursor.getInt(0));
        this.setTitle(cursor.getString(1));
        this.setPinYing(cursor.getString(2));
        this.setDuration(cursor.getInt(3));
        this.setSize(cursor.getString(4));
        this.setSinger(cursor.getString(5));
        this.setAlbum(cursor.getString(6));
        this.setYear(cursor.getString(7));
        this.setType(cursor.getString(8));
        this.setFileUrl(cursor.getString(9));
    }

    public Music() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPinYing(String p){
        this.pinYing = p;
    }

    public String getPinYing(){
        return this.pinYing;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public int compareTo(Music another) {
        return getPinYing().compareTo(another.getPinYing());
    }


    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", title='" + title + '\'' +
                ", pinYing='" + pinYing + '\'' +
                ", duration=" + duration +
                ", singer='" + singer + '\'' +
                ", album='" + album + '\'' +
                ", year='" + year + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
