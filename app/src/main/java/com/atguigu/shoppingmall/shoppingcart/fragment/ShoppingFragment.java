package com.atguigu.shoppingmall.shoppingcart.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoppingmall.base.BaseFragment;

/**
 * Created by 李金桐 on 2017/2/22.
 * QQ: 474297694
 * 功能: 购物车Fragment
 */
public class ShoppingFragment extends BaseFragment {
    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }
    @Override
    public void initData() {
        super.initData();
        Log.e("TAG","购物车数据被初始化了");
        textView.setText("购物车");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
