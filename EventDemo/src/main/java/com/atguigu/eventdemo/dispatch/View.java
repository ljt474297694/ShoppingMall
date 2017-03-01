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
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("TAG", "View dispatchTouchEvent()"+actionToStr(event.getAction()));
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG", "View onTouchEvent()"+actionToStr(event.getAction()));

        return super.onTouchEvent(event);
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
