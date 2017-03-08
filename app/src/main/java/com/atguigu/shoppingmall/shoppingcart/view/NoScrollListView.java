package com.atguigu.shoppingmall.shoppingcart.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by 李金桐 on 2017/3/8.
 * QQ: 474297694
 * 功能: xxxx
 */

public class NoScrollListView extends ListView {

    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
