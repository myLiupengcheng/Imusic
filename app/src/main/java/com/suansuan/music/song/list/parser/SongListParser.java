package com.suansuan.music.song.list.parser;

import com.google.gson.Gson;
import com.suansuan.music.bean.Parser;
import com.suansuan.music.song.list.bean.SongList;
import com.suansuan.music.song.list.bean.SongListCategoryGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SongListParser implements Parser<List<SongList>, List<SongList>> {

    private List<SongList> mListSongList;
    private Gson mGsonParser;

    public SongListParser () {
        mGsonParser = new Gson();
        mListSongList = new ArrayList<>();
    }

    @Override
    public void parserJsonToBean(String jsonString) {
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject response = jsonObject.getJSONObject("response");
            JSONObject data = response.getJSONObject("data");
            JSONArray list = data.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                JSONObject jsonObject1 = list.getJSONObject(i);
                SongList songList = mGsonParser.fromJson(jsonObject1.toString(), SongList.class);
                mListSongList.add(songList);
            }
        }catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<SongList> getParserData() {
        return mListSongList;
    }

    @Override
    public List<SongList> interceptorData(List<SongList> categoryGroup) {
        return null;
    }
}
