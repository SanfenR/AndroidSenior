package com.mz.sanfen.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author MZ
 * @email sanfenruxi1@163.com
 * @date 2017/1/17.
 */

public class TextButton extends LinearLayout {
    private static final String TAG = "TextButton";

    public TextButton(Context context) {
        this(context, null);
    }

    public TextButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        onDir();
    }



    private void onDir() {
        int left = getLeft();
        int right = getRight();
        int top = getTop();
        int bottom = getBottom();

        Log.e(TAG, "onDir: left->" + left + ", right->" + right + ", top->" + top + ", bottom->" + bottom);
    }


    /**
     * 当触摸屏幕的时候触发这个事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
