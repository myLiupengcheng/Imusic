package com.suansuan.music.music.ui.view;

import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;


public class CircleImageView extends AbsRoundImageView {

	private int radius;

	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		super.initAttrs(attrs);
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.initAttrs(attrs);
	}

	public CircleImageView(Context context) {
		super(context);
		super.initAttrs(null);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = Math.min(getMeasuredHeight(), getMeasuredWidth());
		setMeasuredDimension(width, width);
	}
	

	@Override
	public void initBorderPath() {
		roundPath.reset();
		int width = getWidth();
		int height = getHeight();
		
		float x = width*0.5f;
		float y = height*0.5f;
		
		roundPath.addCircle(x, y, radius, Path.Direction.CW);
	}

	@Override
	public void initRoundPath() {
		borderPath.reset();
		float halfBorderWidth = borderWidth * 0.5f;
		int width = getWidth();
		int height = getHeight();
		
		float x = width*0.5f;
		float y = height*0.5f;
		
		borderPath.addCircle(x, y, radius - halfBorderWidth, Path.Direction.CW);
	}

	protected void setRadius(int radius){
		this.radius = radius;
	}
	
}
