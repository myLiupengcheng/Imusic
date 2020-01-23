package com.suansuan.music.music.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


/**
 *  自定义一个Indicate圆点ViewPager的指示器
 * Created by suansuan on 2016/9/30.
 */

public class ViewPagerIndicate extends LinearLayout{

    private String Tag = "ViewPagerIndicate";

    /** 画笔 */
    private Paint mPaint;

    /** 形状 */
    private Path mPath;

    /** 开始画的时候的X坐标 */
    private int mStartX = 0;

    /** 开始画的时候的Y坐标 */
    private int mStartY;

    /** 指示器的宽度 */
    private int mIndicatorWidth;

    /** 指示器的高度 */
    private int mIndicatorHeight = 6;

    /** 指示器的颜色 */
    private String mIndicatorColor = "#2BB681";

    /** Tap的个数 */
    private int mChildCount = 4;

    /** Tab的名字 */
    private List<String> mList;

    /** Indicator所关联的ViewPager */
    private ViewPager mViewPager;

    /** 指示器的圆角度数 */
    private float mRoundDress = 2f;

    /** 暴漏ViewPager的事件监听 */
    private OnPagerChangeListener mOnPagerChangeListener;

    private boolean isShowLine = true;

    public ViewPagerIndicate(Context context) {
        this(context,null);
    }

    public ViewPagerIndicate(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewPagerIndicate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化方法
     */
    public void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor(mIndicatorColor));
        mPaint.setStyle(Paint.Style.FILL);
    }

    /** 这个方法就是在我们需要知道一些控件的宽高，或者根据控件的宽高去设置一些高度 */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mIndicatorWidth = getMeasuredWidth() / mChildCount;
        mStartY = h - mIndicatorHeight;
        initPath();
    }

    /** 初始化要画的形状 */
    private void initPath() {
        mPath = new Path();
        RectF rectF = new RectF(0, 0, mIndicatorWidth,mIndicatorHeight);
        mPath.addRoundRect(rectF, new float[]{
                mRoundDress,mRoundDress,mRoundDress,mRoundDress,
                mRoundDress,mRoundDress,mRoundDress,mRoundDress,
        }, Path.Direction.CW);
    }

    /** 一般ViewGroup绘制孩子的内容的时候使用dispatchDraw()*/
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mStartX,mStartY);
        if(isShowLine){
            canvas.drawPath(mPath,mPaint);
        }
        canvas.restore();
    }

    /** 获取屏幕的宽度 */
    private int getScreenWidth(){
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /** 暴露方法供外部修改 */
    public void setTab(List<String> tabList){
        if(tabList == null || tabList.size() <= 0){
            return;
        }
        mChildCount = tabList.size();
        this.mList = tabList;
        this.removeAllViews();
        for(String tab : tabList){
            addView(generateTextView(tab));
        }
    }

    /** 根据一个String类型的字符串生成一个TextView */
    private View generateTextView(String tab){
        TextView textView = new TextView(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.weight = 0;
        params.width = getScreenWidth() / mChildCount;
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        textView.setTextColor(Color.BLACK);
        textView.setText(tab);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(params);
        setTabStyle(0);
        return textView;
    }

    /** 关联ViewPager */
    public void setViewPager(ViewPager viewPager){
        this.mViewPager = viewPager;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scroll(position,positionOffset);
                if (mOnPagerChangeListener != null){
                    mOnPagerChangeListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (mOnPagerChangeListener != null){
                    mOnPagerChangeListener.onPageSelected(position);
                }
                setTabStyle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mOnPagerChangeListener != null){
                    mOnPagerChangeListener.onPageScrollStateChanged(state);
                }
            }
        });
    }

    /** 根据选中状态去绘制，字体的不同颜色 */
    private void setTabStyle(int position) {
        for (int i = 0 ; i < getChildCount(); i++){
            if (i == position) {
                ((TextView)getChildAt(i)).setTextColor(Color.parseColor(mIndicatorColor));
            }else{
                ((TextView)getChildAt(i)).setTextColor(Color.parseColor("#000000"));
            }
        }
    }

    /** 设置是否显示线 */
    public void setIsShowLine(boolean isShowLine){
        this.isShowLine = isShowLine;
    }


    /** 让指示器动起来 */
    public void scroll(int position,float positionOffset){
        mStartX =(int)(position * mIndicatorWidth + positionOffset * mIndicatorWidth);
        invalidate();
    }

    public void addOnPagerChangeListener(OnPagerChangeListener onPagerChangeListener){
        this.mOnPagerChangeListener = onPagerChangeListener;
    }

    public interface OnPagerChangeListener{
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
        void onPageSelected(int position);
        void onPageScrollStateChanged(int state);
    }

}
