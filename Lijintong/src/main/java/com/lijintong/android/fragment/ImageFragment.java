package com.lijintong.android.fragment;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lijintong.android.base.BaseFragment;

/**
 * Created by 李金桐 on 2017/3/9.
 * QQ: 474297694
 * 功能: xxxx
 */

public class ImageFragment extends BaseFragment {
    private String url;
    private ImageView view;


    public ImageFragment(String url) {
        super();
        this.url = url;

    }

    @Override
    protected View initView() {
        view = new ImageView(mContext);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Glide.with(mContext).load(url).into(view);
    }
}
