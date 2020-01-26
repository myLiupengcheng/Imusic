package com.suansuan.music.bean;

/**
 *
 * Created by suansuan on 2016/10/25.
 */

public class MusicFilePath implements Comparable<MusicFilePath>{

    private String path;
    private String pathKey;
    private String pathName;
    private int songNumber = 1;

    public void setSongNumber(int songNumber) {
        this.songNumber = songNumber;
    }

    public int getSongNumber() {

        return songNumber;
    }

    public void setPath(String path) {
        this.path = path;
    }



    public String getPath() {

        return path;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
        setPathKey(pathName.charAt(0)+"");
    }

    public String getPathName() {

        return pathName;
    }



    public void setPathKey(String pathKey) {
        this.pathKey = pathKey;
    }

    public String getPathKey() {

        return pathKey;
    }

    @Override
    public String toString() {
        return "MusicFilePath{" +
                "path='" + path + '\'' +
                ", pathKey='" + pathKey + '\'' +
                ", pathName='" + pathName + '\'' +
                ", songNumber='" + songNumber + '\'' +
                '}';
    }

    @Override
    public int compareTo(MusicFilePath another) {
        return  getPathKey().compareTo(another.getPathKey());
    }
}
