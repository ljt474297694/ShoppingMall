package com.atguigu.viewpagerandfragment.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.viewpagerandfragment.BreatheInterpolator;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 李金桐 on 2017/2/25.
 * QQ: 474297694
 * 功能: 显示一张图片的Fragment
 */

public class ImageFragment extends Fragment {


    private  int position;
    private ImageView imageView;
    private  boolean isViewCreated;
    private boolean isVisibleToUser;

    public ImageFragment(int i) {
        this.position = i+1 ;
    }

    public ImageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        imageView = new ImageView(getActivity());
        return imageView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreated = true;
        setData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if(isVisibleToUser&&isViewCreated) {
            setData();
        }
    }

    private void setData() {
        if(!isVisibleToUser||!isViewCreated) {
            return;
        }
        isViewCreated = false;
        String image = getArguments().getString("image");
        if (!TextUtils.isEmpty(image)) {
            Glide.with(getActivity()).load(image).into(imageView);
            ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView,"alpha",0f,1f);
            alpha.setDuration(4000);
            alpha.setInterpolator(new BreatheInterpolator());
            alpha.setRepeatCount(ValueAnimator.INFINITE);
            alpha.start();
        }
        Log.e("TAG", "ImageFragment onActivityCreated() 第"+position+"初始化完成");
    }

    public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }
        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);
        // Set the component to be explicit
        explicitIntent.setComponent(component);
        return explicitIntent;
    }
}
