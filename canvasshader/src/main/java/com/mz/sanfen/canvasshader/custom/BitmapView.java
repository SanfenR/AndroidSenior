package com.mz.sanfen.canvasshader.custom;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.mz.sanfen.canvasshader.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/12/20.
 */

public class BitmapView extends View {

    Paint mPaint;
    Bitmap bitmap;

    private Shader.TileMode mDefaultTileMode = Shader.TileMode.REPEAT;


    public BitmapView(Context context) {
        this(context, null);
    }

    public BitmapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint = new Paint();
        drawBitmap();
    }


    public void setTileMode(Shader.TileMode mDefaultTileMode) {
        this.mDefaultTileMode = mDefaultTileMode;
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        drawBitmap();
    }

    public void drawBitmap(){
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.music_player);
        BitmapShader bitmapShader = new BitmapShader(bitmap,
                mDefaultTileMode,
                mDefaultTileMode
        );
        mPaint.setShader(bitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0,  canvas.getWidth(), canvas.getHeight(), mPaint);
    }
}
