package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.shoppingmall.home.bean.HomeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 李金桐 on 2017/2/23.
 * QQ: 474297694
 * 功能: xxxx
 */

public class ViewPagerAdapter extends PagerAdapter {

    private final Context mContext;
    private final List<HomeBean.ResultBean.ActInfoBean> datas;

    public ViewPagerAdapter(Context mContext, List<HomeBean.ResultBean.ActInfoBean> act_info) {
        this.mContext =  mContext;
        this.datas = act_info;

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE+datas.get(position).getIcon_url())
                .crossFade()
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(l!=null) {
                    l.onClick(position);
                }
            }
        });
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    interface onPagerClickListener{
        void onClick(int position);
    }
    private onPagerClickListener l;

    public void setonPagerClickListener(onPagerClickListener l) {
        this.l = l;
    }
}
