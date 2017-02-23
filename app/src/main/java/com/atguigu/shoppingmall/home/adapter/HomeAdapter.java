package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.HomeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.atguigu.shoppingmall.utils.DensityUtil;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 李金桐 on 2017/2/23.
 * QQ: 474297694
 * 功能: xxxx
 */

public class HomeAdapter extends RecyclerView.Adapter {
    /**
     * 六种类型
     */
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;
    private final Context mContext;
    private final HomeBean.ResultBean result;
    /**
     * 当前类型
     */
    public int currentType = BANNER;

    public HomeAdapter(Context mContext, HomeBean.ResultBean result) {
        this.mContext = mContext;
        this.result = result;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == CHANNEL) {
            currentType = CHANNEL;
        } else if (position == ACT) {
            currentType = ACT;
        } else if (position == SECKILL) {
            currentType = SECKILL;
        } else if (position == RECOMMEND) {
            currentType = RECOMMEND;
        } else if (position == HOT) {
            currentType = HOT;
        }
        return currentType;
//        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(View.inflate(mContext, R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(View.inflate(mContext, R.layout.channel_item, null));
        }
        if (viewType == ACT) {
            return new ActViewHolder(View.inflate(mContext, R.layout.act_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //设置数据Banner的数据
            bannerViewHolder.setData(result.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(result.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(result.getAct_info());
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ActViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.act_viewpager)
        ViewPager actViewpager;

        public ActViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(List<HomeBean.ResultBean.ActInfoBean> act_info) {

            actViewpager.setAdapter(new ViewPagerAdapter(mContext, act_info));
//设置每个页面的间距
            actViewpager.setPageMargin(DensityUtil.px2dip(mContext,20));
//>=3
            actViewpager.setOffscreenPageLimit(3);
//设置动画
            actViewpager.setPageTransformer(true, new
                    ScaleInTransformer());

        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.gv_channel)
        GridView gvChannel;

        public ChannelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {

            gvChannel.setAdapter(new ChannelAdapter(mContext, channel_info));
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, position + "", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.banner)
        Banner banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            banner.setBannerAnimation(Transformer.FlipHorizontal);

            ArrayList<String> images = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                images.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());
            }

            banner.setImages(images)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);
                        }
                    })
                    .start();
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "banner=" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
