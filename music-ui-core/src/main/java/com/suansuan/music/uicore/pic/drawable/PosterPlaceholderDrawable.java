package com.suansuan.music.uicore.pic.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class PosterPlaceholderDrawable extends Drawable {

    private static final float RADIUS_BITMAP_POSTER = 12f;

    private Paint mPaint;
    private int mBackgroundColor;


    public PosterPlaceholderDrawable (Context context, int backgroundResId) {
        mBackgroundColor = context.getResources().getColor(backgroundResId);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        drawBackground(canvas);
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawColor(mBackgroundColor);
    }

    @Override
    public void setAlpha(int alpha) {}

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
