package com.mz.sanfen.canvasshader.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/12/20.
 */

public class LinearView extends View {

    Paint mPaint;

    private Shader.TileMode mDefaultTileMode = Shader.TileMode.REPEAT;


    public LinearView(Context context) {
        this(context, null);
    }

    public LinearView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        drawLinear();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth() ,  getHeight(), mPaint);
    }

    public void drawLinear(){
        LinearGradient linearGradient = new LinearGradient(
                0, 0, getWidth() / 4, getHeight() / 4,
                Color.YELLOW,
                Color.RED,
                mDefaultTileMode
        );
        mPaint.setShader(linearGradient);
    }

    public void setTileMode(Shader.TileMode mode) {
        mDefaultTileMode = mode;
        requestLayout();
    }
}
