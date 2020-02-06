package com.suansuan.music.song.list.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class SongListCategoryGroup implements Parcelable {

    public int usable;
    public String categoryGroupName;
    public ArrayList<SongListCategory> items;

    protected SongListCategoryGroup(Parcel in) {
        usable = in.readInt();
        categoryGroupName = in.readString();
    }

    public static final Creator<SongListCategoryGroup> CREATOR = new Creator<SongListCategoryGroup>() {
        @Override
        public SongListCategoryGroup createFromParcel(Parcel in) {
            return new SongListCategoryGroup(in);
        }

        @Override
        public SongListCategoryGroup[] newArray(int size) {
            return new SongListCategoryGroup[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(usable);
        dest.writeString(categoryGroupName);
    }

    public static class SongListCategory implements Parcelable {

        public int usable;
        public String categoryId;
        public String categoryName;

        public SongListCategory(){}

        protected SongListCategory(Parcel in) {
            usable = in.readInt();
            categoryId = in.readString();
            categoryName = in.readString();
        }

        public static final Creator<SongListCategory> CREATOR = new Creator<SongListCategory>() {
            @Override
            public SongListCategory createFromParcel(Parcel in) {
                return new SongListCategory(in);
            }

            @Override
            public SongListCategory[] newArray(int size) {
                return new SongListCategory[size];
            }
        };

        @Override
        public String toString() {
            return "SongListCategory{" + "categoryId='" + categoryId + '\'' +
                    ", categoryName='" + categoryName + '\'' + ", usable=" + usable + '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(usable);
            dest.writeString(categoryId);
            dest.writeString(categoryName);
        }
    }

    @Override
    public String toString() {
        return "SongListCategoryGroup{" + "categoryGroupName='" + categoryGroupName + '\'' +
                ", items=" + items + ", usable=" + usable + '}';
    }
}
