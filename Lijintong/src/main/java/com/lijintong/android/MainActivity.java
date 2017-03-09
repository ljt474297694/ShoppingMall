package com.lijintong.android;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lijintong.android.fragment.HomeFragment;
import com.lijintong.android.fragment.TypeFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.rb_1)
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
                    case R.id.rb_1:
                        switchFragment(fragments.get(0));
                        break;
                    case R.id.rb_2:

                        switchFragment(fragments.get(1));
                        break;
                }
            }
        });
        rgMain.check(R.id.rb_1);
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());

        final Adapter adapter = new Adapter();

        mListView.setAdapter(adapter);

        mListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.closeDrawers();
                adapter.notifyDataSetChanged();
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
                    viewHolder.textview.setTextColor(Color.parseColor("#ff00ddff"));
            }else{
                    viewHolder.textview.setTextColor(Color.parseColor("#fff"));
            }
            viewHolder.textview.setText(datas[position]);
            return convertView;
        }

        class ViewHolder {
            @InjectView(R.id.textview)
            TextView textview;

            ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
        }
    }
}
