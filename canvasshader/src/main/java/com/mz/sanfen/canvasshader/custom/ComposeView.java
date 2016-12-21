package com.mz.sanfen.canvasshader.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import com.mz.sanfen.canvasshader.R;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/12/21.
 */

public class ComposeView extends View {

    Bitmap mBitmap;
    Paint mPaint;
    PorterDuffXfermode mPorterDuffXfermode;

    int mBitmapWidth, mBitmapHeight;

    BitmapShader bitmapShader;

    public ComposeView(Context context) {
        this(context, null);
    }

    public ComposeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        drawCompose();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.saveLayer(0, 0, mBitmapWidth, mBitmapHeight, null, Canvas.ALL_SAVE_FLAG);
        mPaint.setShader()
        canvas.drawRect(0, 0, mBitmapWidth, mBitmapHeight, mPaint);

        Xfermode xfermode = mPaint.setXfermode(mPorterDuffXfermode);


    }

    public void drawCompose(){
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.frame);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
        bitmapShader                                                                                                                                                                                                                                 = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //将BitmapShader作为画笔paint绘图所使用的shader

        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);

        LinearGradient linearGradient = new LinearGradient(0, 0, mBitmapWidth, mBitmapHeight, Color.GREEN, Color.BLUE, Shader.TileMode.CLAMP);

    }
}
