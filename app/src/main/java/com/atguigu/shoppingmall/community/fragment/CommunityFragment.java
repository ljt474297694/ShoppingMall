package com.atguigu.shoppingmall.community.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.community.adapter.CommunityViewPagerAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 李金桐 on 2017/2/22.
 * QQ: 474297694
 * 功能: 发现Fragment
 */
public class CommunityFragment extends BaseFragment {
    @InjectView(R.id.ib_community_icon)
    ImageButton ibCommunityIcon;
    @InjectView(R.id.ib_community_message)
    ImageButton ibCommunityMessage;
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    private ArrayList<Fragment> fragmentList;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_community, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        initFragments();
        CommunityViewPagerAdapter adapter = new CommunityViewPagerAdapter(getFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();

        NewPostFragment newPostFragment = new NewPostFragment();
        HotPostFragment hotPostFragment = new HotPostFragment();
        fragmentList.add(newPostFragment);
        fragmentList.add(hotPostFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.ib_community_icon, R.id.ib_community_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_community_icon:
                Toast.makeText(mContext, "icon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_community_message:
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
