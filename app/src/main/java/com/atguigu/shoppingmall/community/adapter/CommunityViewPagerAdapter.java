package com.atguigu.shoppingmall.community.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguigu.shoppingmall.community.fragment.HotPostFragment;
import com.atguigu.shoppingmall.community.fragment.NewPostFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李金桐 on 2017/3/3.
 * QQ: 474297694
 * 功能: xxxx
 */

public class CommunityViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private String[] titles = new String[]{"新帖", "热帖"};

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public CommunityViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();

        NewPostFragment newPostFragment = new NewPostFragment();
        HotPostFragment hotPostFragment = new HotPostFragment();
        fragmentList.add(newPostFragment);
        fragmentList.add(hotPostFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
