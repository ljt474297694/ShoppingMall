package com.atguigu.shoppingmall.type.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 李金桐 on 2017/2/22.
 * QQ: 474297694
 * 功能: 分类Fragment
 */
public class TypeFragment extends BaseFragment {

    @InjectView(R.id.tl_1)
    SegmentTabLayout tl1;
    @InjectView(R.id.iv_type_search)
    ImageView ivTypeSearch;
    @InjectView(R.id.fl_type)
    FrameLayout flType;
    private Fragment tempFragemnt;
    String[] titls = {"分类","标签"};
    private ArrayList<Fragment> fragments;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        initListener();
        initFragment();
    }

    private void initListener() {
        tl1.setTabData(titls);
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                Toast.makeText(mContext, ""+position, Toast.LENGTH_SHORT).show();
                switchFragment(fragments.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ListFragment());
        fragments.add(new TagFragment());
        //默认显示ListFragment
        switchFragment(fragments.get(0));
    }

    private void switchFragment(Fragment currentFragment) {
        if (tempFragemnt != currentFragment) {

            if (currentFragment != null) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!currentFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (tempFragemnt != null) {
                        transaction.hide(tempFragemnt);
                    }
                    //添加Fragment
                    transaction.add(R.id.fl_type, currentFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (tempFragemnt != null) {
                        transaction.hide(tempFragemnt);
                    }
                    transaction.show(currentFragment).commit();
                }
            }
            tempFragemnt = currentFragment;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
