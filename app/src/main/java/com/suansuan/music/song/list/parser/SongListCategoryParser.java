package com.suansuan.music.song.list.parser;

import com.suansuan.music.bean.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SongListCategoryParser implements Parser {

    public SongListCategoryParser () {

    }

    @Override
    public void parserJsonToBean(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject response = jsonObject.getJSONObject("response");
            JSONObject data = response.getJSONObject("data");
            JSONArray categories = data.getJSONArray("categories");

            for (int i = 0; i < categories.length(); i++){
                JSONObject jsonObject1 = categories.getJSONObject(i);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
