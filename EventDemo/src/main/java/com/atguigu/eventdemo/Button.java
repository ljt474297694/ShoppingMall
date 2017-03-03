package com.atguigu.eventdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by 李金桐 on 2017/3/3.
 * QQ: 474297694
 * 功能: xxxx
 */

public class Button extends android.widget.Button {
    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e("TAG", "Button Button()");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e("TAG", "Button onFinishInflate()");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("TAG", "Button onMeasure()");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("TAG", "Button onLayout()");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("TAG", "Button onDraw()");
    }

    public Button(Context context) {
        this(context,null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("TAG", "Button onSizeChanged()");
    }
}
