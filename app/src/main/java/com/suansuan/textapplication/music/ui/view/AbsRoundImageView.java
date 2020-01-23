package com.suansuan.textapplication.music.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.suansuan.textapplication.music.R;

/**
 * Բ�Ǿ��λ���
 * @author Liu Peng Cheng
 *
 */
public abstract class AbsRoundImageView extends ImageView{
	
	private static final PorterDuffXfermode sMode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
	
	/**
	 * ͼƬ����
	 */
	private Paint mBitmapPaint;
	
	/**
	 * ͼƬ��������
	 */
	protected Path roundPath;
	
	/**
	 * ͼƬ�߿�
	 * 
	 */
	protected Path borderPath;
	
	/**
	 * �߿���
	 */
	protected float borderWidth;
	
	/**
	 * �߿���ɫ
	 */
	protected int borderColor;
	
	/**
	 * �߿򻭱�
	 */
	private Paint borderPaint;

	public AbsRoundImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttrs(attrs);
	}

	public AbsRoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(attrs);
	}

	public AbsRoundImageView(Context context) {
		this(context, null);
	}

	/**
	 * ��ȡ�Զ��������
	 */
	protected void initAttrs(AttributeSet attrs) {
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AbsRoundImageView);
		borderWidth = ta.getDimension(R.styleable.AbsRoundImageView_borderWidth, 0);
		borderColor = ta.getColor(R.styleable.AbsRoundImageView_borderColor, 0);
		ta.recycle();
		init();
	}
	
	/**
	 * ��ʼ������
	 */
	private void init() {
		//ͼƬ����������
		mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		roundPath = new Path();
		
		//�߿�ϸ�ڴ���
		borderPath = new Path();
		borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		borderPaint.setStrokeWidth(borderWidth);
		setScaleType(ScaleType.CENTER_CROP);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		onDrawImage(canvas);
		onDrawBroder(canvas);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if(changed){
			initBorderPath();
			initRoundPath();
		}
	}
	

	/**
	 * ����ͼƬ�ı߿�
	 * @param canvas
	 */
	private void onDrawBroder(Canvas canvas) {
		borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(borderColor);
        canvas.drawPath(borderPath, borderPaint);
	}

	/**
	 * ����ͼƬ������
	 * @param canvas
	 */
	private void onDrawImage(Canvas canvas) {
		
		Drawable drawable = getDrawable();
		if(!(!isInEditMode() && drawable != null)){
			return;
		}
		Bitmap bitmap;
		if(drawable instanceof ColorDrawable){
			bitmap = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
		}else{
			bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
		}
		
		Canvas tempCanvas = new Canvas(bitmap);
		drawable.setBounds(0,0,tempCanvas.getWidth(), tempCanvas.getHeight());
		drawable.draw(tempCanvas);
		
		Bitmap roundBitmap = getRoundBitmap();
		
		mBitmapPaint.reset();
		mBitmapPaint.setFilterBitmap(false);
		mBitmapPaint.setXfermode(sMode);
		tempCanvas.drawBitmap(roundBitmap, 0,0,mBitmapPaint);
		mBitmapPaint.setXfermode(null);
		canvas.drawBitmap(bitmap, 0, 0, mBitmapPaint);
	}
	
	/**
	 * ��ȡһ����ɫ��Bitmap
	 * @return
	 */
	public Bitmap getRoundBitmap(){
		Bitmap bitmap = Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.WHITE);
		canvas.drawPath(roundPath,paint);
		return bitmap;
	}
	
	/**
	 * ��ʼ���߿��Path
	 */
	public abstract void initBorderPath();
	
	/**
	 * ��ʼ�����������Path
	 */
	public abstract void initRoundPath();
	
}
