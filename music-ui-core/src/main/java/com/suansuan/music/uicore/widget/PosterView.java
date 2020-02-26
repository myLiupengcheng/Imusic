package com.suansuan.music.uicore.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatImageView;

import com.suansuanliu.music.uicore.R;

/**
 * 显示海报的View
 */
public class PosterView extends AppCompatImageView {

    private static final int ICON_LISTENER_ICON = R.drawable.ic_listener;
    private static final int ICON_PLAY_ICON = R.drawable.ic_play_arrow;
    private static final int LINEAR_START_COLOR = R.color.posterViewStartColor;
    private static final int LINEAR_END_COLOR = R.color.posterViewEndColor;
    private static final float RADIUS_BITMAP_POSTER = 12f;

    private static final String LISTENER_DEFAULT_NUMBER = "200";

    private Drawable mListenerIcon;
    private Drawable mPlayIcon;

    private Paint mPaint;
    private RectF mRectF;

    private int mStartColor;
    private int mEndColor;
    private float radius;

    private String listenerNumber = formatListenerNumber(LISTENER_DEFAULT_NUMBER);

    public PosterView(Context context) {
        this(context, null);
    }

    public PosterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PosterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPosterView();
    }

    private void initPosterView() {
        mListenerIcon = getContext().getResources().getDrawable(ICON_LISTENER_ICON);
        mPlayIcon = getContext().getResources().getDrawable(ICON_PLAY_ICON);
        mStartColor = getContext().getResources().getColor(LINEAR_START_COLOR);
        mEndColor = getContext().getResources().getColor(LINEAR_END_COLOR);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        radius = Resources.getSystem().getDisplayMetrics().density * RADIUS_BITMAP_POSTER;
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        mRectF = new RectF(0, 0, width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPosterLayer(canvas);
        drawPosterListenerNumber(canvas);
    }

    private void drawPosterLayer(Canvas canvas) {
        if (mRectF != null) {
            LinearGradient linearGradient = new LinearGradient(0, 0, 0,
                    mRectF.bottom, mStartColor, mEndColor, Shader.TileMode.MIRROR);
            mPaint.setShader(linearGradient);
            canvas.drawRoundRect(mRectF, radius, radius, mPaint);
        }
    }

    private void drawPosterListenerNumber(Canvas canvas) {
        if (mListenerIcon != null && mRectF != null) {
            canvas.save();
            float dx = mRectF.right / 13;
            float dy = mRectF.bottom - (mRectF.bottom / 7);
            canvas.translate(dx, dy);
            mListenerIcon.setBounds(0, 0, mListenerIcon.getIntrinsicWidth(), mListenerIcon.getIntrinsicHeight());
            mListenerIcon.draw(canvas);
//            mPaint.setColor(Color.WHITE);
//            mPaint.setShader(null);
//            mPaint.setTextSize(18);
//            StaticLayout staticLayout = new StaticLayout(listenerNumber, mPaint, 600,
//                    Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
//            canvas.drawText(listenerNumber,dx + mListenerIcon.getIntrinsicWidth() + 10, dy, mPaint);
            canvas.restore();
        }
    }

    public void setListenerNumber (String number) {
        this.listenerNumber = formatListenerNumber(number);
    }

    private static String formatListenerNumber (String listenerNumber) {
        listenerNumber  = listenerNumber + "万";
        return listenerNumber;
    }
}
