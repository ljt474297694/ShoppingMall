package com.lijintong.android;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lijintong.android.fragment.HomeFragment;
import com.lijintong.android.fragment.TypeFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.lijintong.android.R.id.rb_1;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(rb_1)
    RadioButton rb1;
    @InjectView(R.id.rb_2)
    RadioButton rb2;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;
    @InjectView(R.id.lv_main)
    ListView mListView;
    @InjectView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    private ArrayList<Fragment> fragments;
    private Fragment tempFragment;
    private int position;
    String[] datas = {"页面1", "页面2", "页面3", "页面4", "页面5"};
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        ButterKnife.inject(this);
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case rb_1:
                        switchFragment(fragments.get(0));
                        //只有在主页面才可以滑动DrawerLayout
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                        break;
                    case R.id.rb_2:
                        //屏蔽侧滑 菜单
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        switchFragment(fragments.get(1));
                        break;
                }
            }
        });
        rgMain.check(rb_1);

        //当HomeFragment中ViewPager改变的时候 刷新DrawerLayout的侧滑菜单中 ListView的高亮位置
        ((HomeFragment) fragments.get(0)).addOnPageChangeListener(new HomeFragment.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                MainActivity.this.position = position;
                if(adapter!=null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());

        adapter = new Adapter();

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, datas[position], Toast.LENGTH_SHORT).show();
                //关闭侧滑菜单
                drawerLayout.closeDrawers();
                //刷新侧滑菜单中ListView的高亮位置
                MainActivity.this.position = position;
                adapter.notifyDataSetChanged();
                //根据ListView的点击位置 切换不同的页面
                if (rb1.isChecked()) {
                    HomeFragment homeFragment = (HomeFragment) fragments.get(0);
                    homeFragment.viewpager.setCurrentItem(position);
                }
            }
        });

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

    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_text, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            if (MainActivity.this.position == position) {
                viewHolder.textview.setTextColor(Color.RED);
            } else {
                viewHolder.textview.setTextColor(Color.WHITE);
            }
            viewHolder.textview.setText(datas[position]);
            return convertView;
        }


    }

    class ViewHolder {
        @InjectView(R.id.textview)
        TextView textview;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
