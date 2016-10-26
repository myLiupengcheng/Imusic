package com.suansuan.textapplication.music.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.suansuan.textapplication.music.R;


public class RoundImageView extends AbsRoundImageView {
	
	private float leftTopRadius;
	private float rightTopRadius;
	private float leftBottomRadius;
	private float rightBottomRadius;
	
	
	public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttrs(attrs);
	}

	public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(attrs);
	}

	public RoundImageView(Context context) {
		this(context,null);
	}
	
	@Override
	protected void initAttrs(AttributeSet attrs) {
		super.initAttrs(attrs);
		if(attrs != null){
			TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RoundImageView);
			leftTopRadius = ta.getDimension(R.styleable.RoundImageView_leftTopRadius, 0);
			rightTopRadius = ta.getDimension(R.styleable.RoundImageView_rightTopRadius, 0);
			leftBottomRadius = ta.getDimension(R.styleable.RoundImageView_leftBottomRadius, 0);
			rightBottomRadius = ta.getDimension(R.styleable.RoundImageView_rightBottomRadius, 0);
			ta.recycle();
		}
	}
	

	@Override
	public void initBorderPath() {
		roundPath.reset();
		int width = getWidth();
		int height = getHeight();
		
		//��Բ�ǽ�����һЩ����
		leftTopRadius = Math.min(leftTopRadius, Math.min(width, height)*0.5f);
		rightTopRadius = Math.min(rightTopRadius, Math.min(width, height)*0.5f);
		leftBottomRadius = Math.min(leftBottomRadius, Math.min(width, height)*0.5f);
		rightBottomRadius = Math.min(rightBottomRadius, Math.min(width, height)*0.5f);
		
		RectF rect = new RectF(0, 0, width, height);
		roundPath.addRoundRect(rect , 
				new float[]{leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius,
                rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius},
				Path.Direction.CW);
	}

	@Override
	public void initRoundPath() {
//        borderPath.reset();
//        /**
//         * ����0.5�ᵼ��border��Բ�Ǵ����ܰ���ԭͼ
//         */
//        final float halfBorderWidth = borderWidth * 0.35f;
//        final int width = getWidth();
//        final int height = getHeight();
//        leftTopRadius = Math.min(leftTopRadius, Math.min(width, height) * 0.5f);
//        rightTopRadius = Math.min(rightTopRadius, Math.min(width, height) * 0.5f);
//        rightBottomRadius = Math.min(rightBottomRadius, Math.min(width, height) * 0.5f);
//        leftBottomRadius = Math.min(leftBottomRadius, Math.min(width, height) * 0.5f);
//
//        RectF rect = new RectF(halfBorderWidth, halfBorderWidth,
//                width - halfBorderWidth, height - halfBorderWidth);
//        borderPath.addRoundRect(rect,
//                new float[]{leftTopRadius, leftTopRadius, rightTopRadius, rightTopRadius,
//                        rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius},
//                Path.Direction.CW);
	}

}
