package com.lijintong.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lijintong.android.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 李金桐 on 2017/3/9.
 * QQ: 474297694
 * 功能: xxxx
 */

public class TypeFragmentPagerAdapter extends FragmentPagerAdapter {

    @Override
    public CharSequence getPageTitle(int position) {
        return position==0?"页面1":"页面2";
    }

    private  ArrayList<BaseFragment> datas;

    public TypeFragmentPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.datas = fragments;
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
