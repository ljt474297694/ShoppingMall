package com.lijintong.android.fragment;


import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.lijintong.android.base.BaseFragment;


/**
 * Created by 李金桐 on 2017/3/9.
 * QQ: 474297694
 * 功能: xxxx
 */

public class TypeListFragment extends BaseFragment {
    @Override
    protected View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("list");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
