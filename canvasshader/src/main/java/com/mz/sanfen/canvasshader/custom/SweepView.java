package com.mz.sanfen.canvasshader.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 16/12/21.
 */

public class SweepView extends View {

    Paint mPaint;

    public SweepView(Context context) {
        this(context, null);
    }

    public SweepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        drawSweep();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 4, mPaint);
    }


    public void drawSweep(){
        //SweepGradient sweep = new SweepGradient(getWidth() /2, getHeight() / 2, Color.GREEN, Color.BLUE);
        int[] colors = {Color.RED, Color.WHITE, Color.YELLOW};
        float[] positions = {0f, 0.75f, 0f};
        SweepGradient sweep = new SweepGradient(getWidth() / 2 , getHeight() / 2, colors, positions);

        mPaint.setShader(sweep);
    }

}
