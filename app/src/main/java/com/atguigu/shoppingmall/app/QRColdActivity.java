package com.atguigu.shoppingmall.app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.utils.Constants;
import com.atguigu.shoppingmall.utils.CreateQRImageTest;
import com.atguigu.shoppingmall.utils.DensityUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class QRColdActivity extends AppCompatActivity {

    @InjectView(R.id.iv_qr_cold)
    ImageView ivQrCold;
    AutoLinearLayout a;
    AutoLayoutActivity aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcold);
        ButterKnife.inject(this);
        final String url = getIntent().getStringExtra("url");
        if (url != null) {
            Glide.with(this).load(Constants.BASE_URL_IMAGE + url).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    if (resource != null) {
                        Bitmap qrImage = CreateQRImageTest.createQRImage(Constants.BASE_URL_IMAGE + url,
                                DensityUtil.dip2px(QRColdActivity.this, 260), DensityUtil.dip2px(QRColdActivity.this, 260), resource);
                        if (qrImage != null) ivQrCold.setImageBitmap(qrImage);
                    }
                }
            });
        } else {
            Toast.makeText(QRColdActivity.this, "路径错误 生成二维码失败", Toast.LENGTH_SHORT).show();
        }
        int[] a = new int[]{1, 2, 94, 63, 498, 46, 13, 541, 854, 64, 6351, 4};
        for (int i = 0; i < a.length - 1; i++) {
            int t = i;
            for (int l = i + 1; l < a.length; l++) {
                if (a[t] > a[l]) {
                    t = l;
                }
            }
            if (t != i) {
                int temp = a[t];
                a[t] = a[i];
                a[i] = temp;
            }
        }


        for (int i = 0; i < a.length; i++) {
            Log.e("TAG", "QRColdActivity onCreate()选择" + a[i]);
        }
        a = new int[]{1, 2, 94, 63, 498, 46, 13, 541, 854, 64, 6351, 4};
        for (int i = 0; i < a.length - 1; i++) {
            for (int l = 0; l < a.length - 1 - i; l++) {
                if (a[l] > a[l + 1]) {
                    int temp = a[l];
                    a[l] = a[l + 1];
                    a[l + 1] = temp;
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            Log.e("TAG", "QRColdActivity onCreate()冒泡" + a[i]);
        }
        a = new int[]{1, 2, 94, 63, 498, 46, 13, 541, 854, 64, 6351, 4};
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            for (int l = i - 1; l >= 0 && a[l] > temp; l--) {
                a[l + 1] = a[l];
                a[l] = temp;
            }
        }
        for (int i = 0; i < a.length; i++) {
            Log.e("TAG", "QRColdActivity onCreate()插入" + a[i]);
        }
        a = new int[]{1, 2, 94, 63, 498, 46, 13, 541, 854, 64, 6351, 4};

        sort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            Log.e("TAG", "QRColdActivity onCreate()快速" + a[i]);
        }
    }

    public int index(int[] array, int start, int end) {

        //固定的切分方式
        int temp = array[start];
        while(start<end) {
            while(start<end&&array[end]>=temp) {
                end--;
            }
            array[start] = array[end];
            while(start<end&&array[start]<=temp) {
                start++;
            }
            array[end] = array[start];
        }
        array[end] = temp;
        return end;

    }

    public void sort(int[] array, int start, int end) {
        int index = index(array, start, end);
        sort(array,index+1,end);
        sort(array,start,index-1);
    }
}
