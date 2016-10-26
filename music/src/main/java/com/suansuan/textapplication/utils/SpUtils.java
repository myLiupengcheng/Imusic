package com.suansuan.textapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 *  对SharedPreference的使用做了建议的封装，
 *  对外公布出put，get，remove，clear等等方法；注意一点，里面所有的commit操作
 *  使用了SharedPreferencesCompat.apply进行了替代，目的是尽可能的使用apply代替commit
 *  首先说下为什么，因为commit方法是同步的，并且我们很多时候的commit操作都是UI线程中，
 *  毕竟是IO操作，尽可能异步；所以我们使用apply进行替代，apply异步的进行写入；
 *
 */
public class SpUtils {

    /**
     * 存取文件的名字
     */
    private static final String FILE_NAME = "share_data";
    private static final int FILE_MODE = Context.MODE_PRIVATE;

    /**
     * 使用SharedPreferences放入键值对
     * @param context:上下文环境
     * @param key:建
     * @param object:值
     */
    public static void put(Context context , String key , Object object){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,FILE_MODE);
        SharedPreferences.Editor editor = sp.edit();
        if(object instanceof String){
            editor.putString(key,(String)object);
        }else if(object instanceof Integer){
            editor.putInt(key,(Integer)object);
        }else if(object instanceof Long){
            editor.putLong(key,(Long)object);
        }else if(object instanceof Float){
            editor.putFloat(key,(Float)object);
        }else if(object instanceof Boolean){
            editor.putBoolean(key,(Boolean)object);
        }else {
            editor.putString(key,object.toString());
        }
        editor.apply();
    }

    /**
     * 使用SharedPreferences取出键值对
     * @param context:上下文環境
     * @param key: 键
     * @param object:值
     * @return 取出的值
     */
    public static Object get(Context context , String key , Object object){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,FILE_MODE);
        Object obj;
        if(object instanceof String){
            obj = sp.getString(key,(String) object);
        }else if(object instanceof Integer){
            obj = sp.getInt(key,(Integer) object);
        }else if(object instanceof Long){
            obj = sp.getLong(key,(Long) object);
        }else if(object instanceof Float){
            obj = sp.getFloat(key,(Float) object);
        }else if(object instanceof Boolean){
            obj = sp.getBoolean(key,(Boolean) object);
        }else {
            obj = sp.getString(key,object.toString());
        }
        return obj;
    }

    /**
     * 使用SharedPreferences移除已经存在的节点
     * @param context:上下文环境
     * @param key:要移除的键
     */
    public void remove(Context context , String key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,FILE_MODE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 使用SharedPreferences移除所以的节点
     * @param context:
     */
    public void removeAll(Context context){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,FILE_MODE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 具体去判断这个Key是否存在
     * @param context:上下文环境
     * @param key: 要查询的Key
     * @return 存在返回Ture，不存在返回False.
     */
    public boolean contains(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,FILE_MODE);
        return sp.contains(key);
    }
}
