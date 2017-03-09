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

public class HomeFragment extends BaseFragment {

    private TextView view;

    @Override
    protected View initView() {
        view = new TextView(mContext);
        view.setText("主页");
        view.setGravity(Gravity.CENTER);
        return view;
    }
    @Override
    protected void initData() {
        super.initData();
    }
}
