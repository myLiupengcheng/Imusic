package com.suansuan.music.song.list.bean;

import java.util.List;

public class SongListCategoryGroup {

    public String categoryGroupName;
    public List<SongListCategory> items;
    public int usable;

    public static class SongListCategory {
        public String categoryId;
        public String categoryName;
        public int usable;

        @Override
        public String toString() {
            return "SongListCategory{" + "categoryId='" + categoryId + '\'' +
                    ", categoryName='" + categoryName + '\'' + ", usable=" + usable + '}';
        }
    }

    @Override
    public String toString() {
        return "SongListCategoryGroup{" + "categoryGroupName='" + categoryGroupName + '\'' +
                ", items=" + items + ", usable=" + usable + '}';
    }
}
