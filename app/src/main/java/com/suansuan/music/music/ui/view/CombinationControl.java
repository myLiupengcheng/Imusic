package com.suansuan.music.music.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suansuan.music.R;


/**
 *
 * Created by suansuan on 2016/10/3.
 */

public class CombinationControl extends LinearLayout {

    private View mRootView;
    private TextView mNodeTextView;

    private int mStartIcon;
    private int mEndIcon;

    private String mTitle;
    private String mNote;
    private String Tag = "CombinationControl";

    public CombinationControl(Context context) {
        this(context,null);
    }

    public CombinationControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /** 初始化操作 */
    private void init(Context context, AttributeSet attrs) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.item_home_combinationcontrol, this, true);
        initAttrs(context,attrs);
    }

    /** 初始化属性 */
    private void initAttrs(Context context,AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.CombinationControl);
        mStartIcon = ta.getResourceId(R.styleable.CombinationControl_startIcon,0);
        mEndIcon = ta.getResourceId(R.styleable.CombinationControl_endIcon,0);
        mTitle = ta.getString(R.styleable.CombinationControl_titles);
        mNote = ta.getString(R.styleable.CombinationControl_notes);
        Log.i(Tag,">>" + mStartIcon + mEndIcon + mTitle + mNote);

        ta.recycle();
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        ImageView mStartIconView = (ImageView) mRootView.findViewById(R.id.start_icon);
        mStartIconView.setImageResource(mStartIcon);
        ImageView mEndIconView = (ImageView) mRootView.findViewById(R.id.end_icon);
        mEndIconView.setImageResource(mEndIcon);
        TextView mTitleView = (TextView) mRootView.findViewById(R.id.tv_title);
        mTitleView.setText(mTitle);
        mNodeTextView = (TextView) mRootView.findViewById(R.id.tv_note);
        mNodeTextView.setText(mNote);
    }


    public void setNoteText(String str){
        mNodeTextView.setText(str);
    }

}
