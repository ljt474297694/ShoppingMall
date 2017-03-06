package com.atguigu.eventdemo.dispatch;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by 李金桐 on 2017/2/28.
 * QQ: 474297694
 * 功能: xxxx
 */

public class View extends TextView {

    public View(Context context, AttributeSet attrs) {
        super(context, attrs);
//        Log.e("TAG", "View View()");
    }

//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        Log.e("TAG", "View onFinishInflate()");
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.e("TAG", "View onMeasure()");
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//        Log.e("TAG", "View onLayout()");
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        Log.e("TAG", "View onDraw()");
//    }
//
//    public View(Context context) {
//        this(context,null);
//    }
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        Log.e("TAG", "View onSizeChanged()");
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("TAG", "View dispatchTouchEvent()"+actionToStr(event.getAction()));
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG", "View onTouchEvent()"+actionToStr(event.getAction()));
//        getParent().requestDisallowInterceptTouchEvent(true);
        return true;
    }
    public String actionToStr(int action){
        String str = null;
        switch (action) {
            case  MotionEvent.ACTION_DOWN:
                str = "按下";
                break;
            case  MotionEvent.ACTION_MOVE:
                str = "滑动";
                break;
            case  MotionEvent.ACTION_UP:
                str = "离开";
                break;
        }
        return str;
    }



}
