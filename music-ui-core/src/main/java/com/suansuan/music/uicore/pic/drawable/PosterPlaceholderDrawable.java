package com.suansuan.music.uicore.pic.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.suansuanliu.music.uicore.R;

public class PosterPlaceholderDrawable extends Drawable {

    private Paint mPaint;
    private Drawable iconDrawable;
    private Context context;

    public PosterPlaceholderDrawable (Context context) {
        this.context = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
//        drawBg(canvas);
        drawImage(canvas);
    }

    private void drawImage(Canvas canvas) {
        Drawable iconDrawable = context.getResources().getDrawable(R.drawable.ic_audiotrack);
        Rect bounds = new Rect(0, 0, 80, 80);
//        iconDrawable.getPaint().setColor(Color.GRAY);
        iconDrawable.setBounds(bounds);
//            BitmapDrawable bitmap = (BitmapDrawable) iconDrawable;
//            canvas.drawBitmap(bitmap.getBitmap(), (float) bitmap.getIntrinsicWidth(), (float) bitmap.getIntrinsicHeight(), mPaint);
        canvas.save();
        canvas.translate(this.getIntrinsicWidth()/ 2, this.getIntrinsicHeight()/2);
        iconDrawable.draw(canvas);
        canvas.restore();
    }

    private void drawBg(Canvas canvas) {
//        canvas.save();
        canvas.drawColor(Color.parseColor("#CCCCCC"));
//        canvas.restore();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
