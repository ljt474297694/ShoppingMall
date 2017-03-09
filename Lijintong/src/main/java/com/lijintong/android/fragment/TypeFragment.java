package com.lijintong.android.fragment;


import android.support.v4.view.ViewPager;
import android.view.View;

import com.lijintong.android.R;
import com.lijintong.android.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 李金桐 on 2017/3/9.
 * QQ: 474297694
 * 功能: xxxx
 */

public class TypeFragment extends BaseFragment {


    @InjectView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
