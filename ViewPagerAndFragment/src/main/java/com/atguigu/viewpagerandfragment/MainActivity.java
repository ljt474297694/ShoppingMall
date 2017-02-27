package com.atguigu.viewpagerandfragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tablelayout)
    TabLayout tablelayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    String[] datas = {
            "http://sjbz.fd.zol-img.com.cn/t_s480x800c/g5/M00/08/0A/ChMkJliEUgWIGWy8AAWUH9AG9zMAAZe7gPrREkABZQ3007.jpg",
            "http://sjbz.fd.zol-img.com.cn/t_s480x800c/g5/M00/08/0A/ChMkJliEUgWIWzw0AAQmOu8l33oAAZe7gPdxW0ABCZS129.jpg",
            "http://sjbz.fd.zol-img.com.cn/t_s480x800c/g5/M00/08/0A/ChMkJ1iEUgWIbpwIAAUz5kEUSy0AAZe7wASX0kABTP-083.jpg",
            "http://sjbz.fd.zol-img.com.cn/t_s480x800c/g5/M00/08/0A/ChMkJ1iEUgWIYAyaAAixGG1uSlAAAZe7wAJrhkACLEw058.jpg",
            "http://sjbz.fd.zol-img.com.cn/t_s480x800c/g5/M00/08/0A/ChMkJliEUgWIRotxAATQL-FHoo4AAZe7wAE3dIABNBH087.jpg"
    };
    private ArrayList<ImageFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        initData();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        ImageFragment imageFragment;
        for(int i = 0; i <datas.length ; i++) {
            Bundle bundle = new Bundle();
            bundle.putString("image",datas[i]);

            imageFragment = new ImageFragment();
            imageFragment.setArguments(bundle);

            fragments.add(imageFragment);
        }
    }
    private void initData() {

        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        tablelayout.setupWithViewPager(viewpager);
    }

    private void initView() {
        ButterKnife.inject(this);
    }

    class MyAdapter extends FragmentPagerAdapter{
        @Override
        public CharSequence getPageTitle(int position) {
            return "第"+(position+1)+"页";
        }

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
