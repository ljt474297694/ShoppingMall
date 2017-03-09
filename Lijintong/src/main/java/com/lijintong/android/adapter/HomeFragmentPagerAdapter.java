package com.lijintong.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lijintong.android.fragment.ImageFragment;

import java.util.ArrayList;

/**
 * Created by 李金桐 on 2017/3/9.
 * QQ: 474297694
 * 功能: xxxx
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private  ArrayList<ImageFragment> datas;

    @Override
    public CharSequence getPageTitle(int position) {
        return "页面"+(1+position);
    }

    public HomeFragmentPagerAdapter(FragmentManager fm, ArrayList<ImageFragment> imageFragments) {
        super(fm);
        this.datas = imageFragments;

    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
