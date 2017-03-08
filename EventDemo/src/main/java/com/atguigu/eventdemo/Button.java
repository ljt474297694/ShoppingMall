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
    /**
     * 构造方法 该控件被实例化的时候调用
     * @param context
     * @param attrs
     */
    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e("TAG", "Button Button()");
    }
    public Button(Context context) {
        this(context,null);
    }


    /**
     * 该方法当View及其子View从XML文件中加载完成后会被调用。
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e("TAG", "Button onFinishInflate()");
    }

    /**
     * 该方法在当前View被附到一个window上时被调用。
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e("TAG", "Button onAttachedToWindow()");
    }

    /**
     * 该方法在计算当前View及其所有子View尺寸大小需求时会被调用。
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("TAG", "Button onMeasure()");
    }

    /**
     * 该方法在当前View需要为其子View分配尺寸和位置时会被调用。
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("TAG", "Button onLayout()");
    }

    /**
     * 该方法在当前View需要呈现其内容时被调用。
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("TAG", "Button onDraw()");
    }

    /**
     * 该方法在当前View从一个window上分离时被调用。
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e("TAG", "Button onDetachedFromWindow()");
    }

    /**
     * 该方法在当前View尺寸变化时被调用。
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("TAG", "Button onSizeChanged()");
    }
}
