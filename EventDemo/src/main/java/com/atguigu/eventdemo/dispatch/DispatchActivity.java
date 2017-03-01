package com.atguigu.eventdemo.dispatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.atguigu.eventdemo.R;

public class DispatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("TAG", "Activity dispatchTouchEvent()"+actionToStr(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG", "Activity onTouchEvent()"+actionToStr(event.getAction()));
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
