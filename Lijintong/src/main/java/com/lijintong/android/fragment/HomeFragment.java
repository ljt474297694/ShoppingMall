package com.lijintong.android.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lijintong.android.R;
import com.lijintong.android.adapter.HomeFragmentPagerAdapter;
import com.lijintong.android.base.BaseFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 李金桐 on 2017/3/9.
 * QQ: 474297694
 * 功能: xxxx
 */

public class HomeFragment extends BaseFragment {


    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;

    ArrayList<ImageFragment> imageFragments;

    String[] datas = {
            "http://sjbz.fd.zol-img.com.cn/t_s480x800c/g5/M00/08/0A/ChMkJliEUgWIGWy8AAWUH9AG9zMAAZe7gPrREkABZQ3007.jpg",
            "http://sjbz.fd.zol-img.com.cn/t_s480x800c/g5/M00/08/0A/ChMkJliEUgWIWzw0AAQmOu8l33oAAZe7gPdxW0ABCZS129.jpg",
            "http://sjbz.fd.zol-img.com.cn/t_s480x800c/g5/M00/08/0A/ChMkJ1iEUgWIbpwIAAUz5kEUSy0AAZe7wASX0kABTP-083.jpg",
            "http://sjbz.fd.zol-img.com.cn/t_s480x800c/g5/M00/08/0A/ChMkJ1iEUgWIYAyaAAixGG1uSlAAAZe7wAJrhkACLEw058.jpg",
            "http://sjbz.fd.zol-img.com.cn/t_s480x800c/g5/M00/08/0A/ChMkJliEUgWIRotxAATQL-FHoo4AAZe7wAE3dIABNBH087.jpg"
    };

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        imageFragments = new ArrayList<>();
        for (int i = 0; i < datas.length; i++) {
            imageFragments.add(new ImageFragment(datas[i]));
        }
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getFragmentManager(),imageFragments);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
