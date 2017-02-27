package com.atguigu.viewpagerandfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.atguigu.viewpagerandfragment.fragment.ImageFragment;
import com.atguigu.viewpagerandfragment.fragment.ViewPagerFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;
    private Fragment tempFragment;
    private ArrayList<Fragment> fragments;
    String data = "http://imgsrc.baidu.com/forum/pic/item/3e950a7b02087bf45010fc7efbd3572c10dfcfad.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case  R.id.bt_1:
                        switchFragment(fragments.get(0));
                        break;
                    case  R.id.bt_2:
                        switchFragment(fragments.get(1));
                        break;
                }
            }
        });
        rgMain.check(R.id.bt_1);
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new ViewPagerFragment());
        ImageFragment imageFragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("image",data);
        imageFragment.setArguments(bundle);
        fragments.add(imageFragment);
    }


    private void initView() {
        ButterKnife.inject(this);
    }




    private void switchFragment(Fragment currentFragment) {
        if (tempFragment != currentFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if (currentFragment.isAdded()) {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment);
            } else {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.fl_main, currentFragment);
            }
            tempFragment = currentFragment;
            ft.commit();
        }
    }

}
