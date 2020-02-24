package com.suansuan.music.uicore.pic;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * 海报圆角矩形
 */
public class PosterRoundTransform extends BitmapTransformation {

    private static final float RADIUS_BITMAP_POSTER = 12f;
    private static float radius;

    /**
     * 构造函数
     * @param context Context
     */
    public PosterRoundTransform(Context context) {
        super(context);
        radius = Resources.getSystem().getDisplayMetrics().density * RADIUS_BITMAP_POSTER;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return transformBitmap(pool, toTransform);
    }

    private static Bitmap transformBitmap(BitmapPool pool, Bitmap source) {
        if (source == null) {
            return null;
        }
        Bitmap result = getBitmap(pool, source);
        Canvas canvas = new Canvas(result);
        drawRoundCrop(source, canvas);
        return result;
    }

    /**
     * 画圆角矩形
     * @param source 原Bitmap
     * @param canvas 画布
     */
    private static void drawRoundCrop(Bitmap source, Canvas canvas) {
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }

    /**
     * 画播放量
     * @param canvas 画布
     */
    private static void drawPlayNumber(Canvas canvas) {

    }

    private static Bitmap getBitmap(BitmapPool pool, Bitmap source) {
        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName() + Math.round(radius);
    }
}
