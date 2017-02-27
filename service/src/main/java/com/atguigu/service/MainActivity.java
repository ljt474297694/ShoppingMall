package com.atguigu.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                startService(new Intent(this,MyService.class));
                break;
            case R.id.btn_2:
                stopService(new Intent(this,MyService.class));
                break;
        }
    }
}
