package com.lijintong.android.fragment;

import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
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
    private ViewPager viewpager;
    private String url;
    private ImageView view;

    public ImageFragment(String url, ViewPager viewpager) {
        super();
        this.url = url;
        this.viewpager = viewpager;
    }

    @Override
    protected View initView() {
        view = new ImageView(mContext);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (event.getX() > view.getWidth() / 2) {
                            viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
                        } else {
                            viewpager.setCurrentItem(viewpager.getCurrentItem() - 1);
                        }
                        break;
                }
                return true;
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Glide.with(mContext).load(url).into(view);
    }
}
