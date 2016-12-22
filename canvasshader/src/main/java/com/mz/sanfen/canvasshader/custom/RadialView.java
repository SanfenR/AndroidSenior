package com.mz.sanfen.canvasshader.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/12/20.
 */

public class RadialView extends View {

    Paint mPaint;

    private Shader.TileMode mDefaultTileMode = Shader.TileMode.REPEAT;

    public RadialView(Context context) {
        this(context, null);
    }

    public RadialView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }


    public void setTileMode(Shader.TileMode mDefaultTileMode) {
        this.mDefaultTileMode = mDefaultTileMode;
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        drawRadial();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0 , 0, getWidth(), getHeight(), mPaint);
    }

    public void drawRadial(){
        RadialGradient radial = new RadialGradient(
                getWidth() / 2, getHeight() / 2, getWidth() / 4,
                Color.YELLOW, Color.RED,
                mDefaultTileMode
        );

        mPaint.setShader(radial);
    }

}
