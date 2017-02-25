package com.atguigu.viewpagerandfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by 李金桐 on 2017/2/25.
 * QQ: 474297694
 * 功能: xxxx
 */

public class ImageFragment extends Fragment {


    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        imageView = new ImageView(getActivity());
        return imageView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String image = getArguments().getString("image");
        if(!TextUtils.isEmpty(image)) {
            Glide.with(getActivity()).load(image).into(imageView);
        }
    }
}
