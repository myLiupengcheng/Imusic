package com.suansuan.music.song.list.parser;

import com.google.gson.Gson;
import com.suansuan.music.bean.Parser;
import com.suansuan.music.song.list.bean.SongListCategoryGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.suansuan.music.song.list.bean.SongListCategoryGroup.SongListCategory;

public class SongListCategoryParser implements Parser<List<SongListCategoryGroup>, List<SongListCategory>> {

    private List<SongListCategoryGroup> mListSongListCategoryGroup;
    private Gson mGsonParser;

    public SongListCategoryParser () {
        mGsonParser = new Gson();
        mListSongListCategoryGroup = new ArrayList<>();
    }

    @Override
    public void parserJsonToBean(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject response = jsonObject.getJSONObject("response");
            JSONObject data = response.getJSONObject("data");
            JSONArray categories = data.getJSONArray("categories");
            for (int i = 0; i < categories.length(); i++) {
                JSONObject jsonObject1 = categories.getJSONObject(i);
                SongListCategoryGroup songListCategoryGroup = mGsonParser.fromJson(
                    jsonObject1.toString(), SongListCategoryGroup.class);
                mListSongListCategoryGroup.add(songListCategoryGroup);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SongListCategory> interceptorData (List<SongListCategoryGroup> categoryGroup) {
        List<SongListCategory> categoryList = new ArrayList<>();
        for (int i = 0; i< categoryGroup.size(); i++) {
            SongListCategoryGroup songListCategoryGroup = categoryGroup.get(i);
            SongListCategory songListCategory = new SongListCategory();
            songListCategory.categoryName = i == 0 ? songListCategoryGroup.categoryGroupName :
                songListCategoryGroup.items.get(0).categoryName;
            songListCategory.usable = songListCategoryGroup.usable;
            songListCategory.categoryId = songListCategoryGroup.items.get(0).categoryId;
            categoryList.add(songListCategory);
        }
        return categoryList;
    }

    @Override
    public List<SongListCategoryGroup> getParserData() {
        return mListSongListCategoryGroup;
    }


}
