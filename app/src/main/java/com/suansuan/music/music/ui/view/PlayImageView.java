package com.suansuan.music.music.ui.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.RotateAnimation;

/**
 *
 * Created by suansuan on 2016/10/22.
 */

public class PlayImageView extends CircleImageView {

    private Paint mPaint;
    private RotateAnimation mRotateAnimation;

    private boolean isRotate;

    private int mRadius;

    public PlayImageView(Context context) {
        this(context, null);
    }

    public PlayImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /** 初始化操作 */
    private void init() {
//        setBackground();
        initPaint();
    }

    /** 初始化画笔 */
    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = (int) (MeasureSpec.getSize(heightMeasureSpec) / 3f * 2);
        int widthAndHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.getMode(heightMeasureSpec));
        super.onMeasure(widthAndHeightMeasureSpec, widthAndHeightMeasureSpec);
        setRadius(30);
    }
}
