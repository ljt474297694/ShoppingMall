package com.atguigu.eventdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.atguigu.eventdemo.dispatch.DispatchActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.bt_dispatch)
    Button btDispatch;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

        @OnClick({R.id.bt_dispatch})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt_dispatch:
                    startActivity(new Intent(this, DispatchActivity.class));
                    break;
            }
    }

}
