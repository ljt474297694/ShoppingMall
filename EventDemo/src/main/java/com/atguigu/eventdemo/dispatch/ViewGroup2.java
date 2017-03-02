package com.atguigu.eventdemo.dispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by 李金桐 on 2017/2/28.
 * QQ: 474297694
 * 功能: xxxx
 */

public class ViewGroup2 extends FrameLayout {
    public ViewGroup2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("TAG", "ViewGroup2 dispatchTouchEvent()"+actionToStr(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG", "ViewGroup2 onTouchEvent()"+actionToStr(event.getAction()));
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("TAG", "ViewGroup2 onInterceptTouchEvent()"+actionToStr(ev.getAction()));
        return super.onInterceptTouchEvent(ev);
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
