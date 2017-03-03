package com.atguigu.interfacedemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by 李金桐 on 2017/3/1.
 * QQ: 474297694
 * 功能: xxxx
 */

public class MyButton extends TextView {
    public MyButton(Context context) {
        this(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (l != null)  l.OnClick();
                break;
        }

        return true;
    }

    public interface OnMyClickListener {
        void OnClick();
    }

    private OnMyClickListener l;

    public void setOnMyClickListener(OnMyClickListener l) {
        this.l = l;
    }


}

