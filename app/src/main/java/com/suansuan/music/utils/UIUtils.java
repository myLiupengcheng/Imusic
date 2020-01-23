package com.suansuan.music.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 *
 * Created by suansuan on 2016/10/2.
 */

public class UIUtils {

    /** 找到Button ImageButton 设置 监听器 */
    public static void findButtonSetOnClickListener(View.OnClickListener listener,View view){
        if(view instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0 ; i < viewGroup.getChildCount() ; i++){
                View childAt = viewGroup.getChildAt(i);
                if(childAt instanceof Button || childAt instanceof ImageButton ||
                        childAt instanceof ImageView){
                    childAt.setOnClickListener(listener);
                }else if (childAt instanceof ViewGroup){
                    findButtonSetOnClickListener(listener,childAt);
                }
            }
        }
    }


//    public static void findImagerViewSetOnClickListener(View.OnClickListener listener,View view){
//        if(view instanceof ViewGroup){
//            ViewGroup viewGroup = (ViewGroup) view;
//            for (int i = 0 ; i < viewGroup.getChildCount() ; i++){
//                View childAt = viewGroup.getChildAt(i);
//                if(childAt instanceof ImageView){
//                    childAt.setOnClickListener(listener);
//                }else if (childAt instanceof ViewGroup){
//                    findButtonSetOnClickListener(listener,childAt);
//                }
//            }
//        }
//    }
}
