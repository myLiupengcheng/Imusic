package com.suansuan.music.music.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.suansuan.music.R;


public class IndexQueryView extends View{

	private String Tag = "IndexQueryView";

	/** 一个单元的高度 */
	private int mUnitHight;

	/** 所需要的数据 */
	private String[] mArrayData = {"A","B","C","D","E","F","G","H","I","G","K","L","M","N","O",
			"P","Q","R","S","T","U","V","W","X","Y","Z"};

	/** 字体的颜色 */
	private String mTextColor = "#1F7246";

	/** 背景的颜色 */
	private int mBackground = R.drawable.index_query_bg_shape;

	/** 一个单元的宽度，也就是整个空间的宽度 */
	private int mUnitWidth;

	/** 需要一只画笔 */
	private Paint mPaint;

	/** 画字符开始的X轴的位置 */
	private int mStartX;

	/** 画字符索要开始的Y位置 */
	private int mSatrtY;

	/** 记录上一次触摸的位置 */
	private int mLastPosition;

	/** 准备回调接口 */
	private OnClickIndexQueryListener mOnClickIndexQueryListener;


	public IndexQueryView(Context context) {
		this(context,null);
	}

	public IndexQueryView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public IndexQueryView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	/** 初始化操作 */
	private void init() {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setTextSize(20);
		mPaint.setTextAlign(Align.CENTER);//设置文本的起点是文字边框底边的中心
		setBackground(getResources().getDrawable(mBackground));
	}

	/** 测量 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mUnitHight = getMeasuredHeight() / mArrayData.length;
		mUnitWidth = getMeasuredWidth();
		mStartX = mUnitWidth / 2;
		mSatrtY = mUnitHight * 3 / 4;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		for(int i = 0; i< mArrayData.length;i++){
			int dy = i * mUnitHight;
			mPaint.setColor(i == mLastPosition ? Color.parseColor(mTextColor) : Color.BLACK);
			canvas.drawText(mArrayData[i], mStartX, mSatrtY + dy, mPaint);
		}
		canvas.restore();
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
				int moveY = (int) event.getY();
				int index = moveY/mUnitHight;
				if(mLastPosition != index){
					if(index > 0 && index < mArrayData.length){
						if(mOnClickIndexQueryListener != null){
							mOnClickIndexQueryListener.onClickIndexQuery(mArrayData[index]);
						}
					}
				}
				mLastPosition = index;
				break;
			case MotionEvent.ACTION_UP:
				mLastPosition = -1;
				break;
		}
		//引起重绘
		invalidate();
		return true;
	}

	public void addOnClickIndexQueryListener(OnClickIndexQueryListener onClickIndexQueryListener){
		this.mOnClickIndexQueryListener = onClickIndexQueryListener;
	}

	public interface OnClickIndexQueryListener{
		void onClickIndexQuery(String letter);
	}
}
