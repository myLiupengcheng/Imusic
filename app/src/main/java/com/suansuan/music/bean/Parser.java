package com.suansuan.music.bean;

public interface Parser<T, E> {

    void parserJsonToBean (String jsonString);
    T getParserData();
    E interceptorData (T categoryGroup);
}
