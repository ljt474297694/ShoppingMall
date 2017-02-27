package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.app.GoodsInfoActivity;
import com.atguigu.shoppingmall.app.WebViewActivity;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.home.bean.HomeBean;
import com.atguigu.shoppingmall.home.bean.WebViewBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.atguigu.shoppingmall.utils.DensityUtil;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.iwgang.countdownview.CountdownView;

import static android.view.View.inflate;

/**
 * Created by 李金桐 on 2017/2/23.
 * QQ: 474297694
 * 功能: xxxx
 */

public class HomeAdapter extends RecyclerView.Adapter<BaseViewHolder> {
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
    private long endTime;
    public static final String GOODS_BEAN = "goodsbean";
    public static final String WEBVIEW_BEAN = "WEBVIEW_BEAN";


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
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                return new BannerViewHolder(inflate(mContext, R.layout.banner_viewpager, null));
            case CHANNEL:

                return new ChannelViewHolder(inflate(mContext, R.layout.channel_item, null));
            case ACT:
                return new ActViewHolder(inflate(mContext, R.layout.act_item, null));
            case SECKILL:
                endTime = Long.parseLong(result.getSeckill_info().getEnd_time()) -
                        Long.parseLong(result.getSeckill_info().getStart_time()) + System.currentTimeMillis();
                return new SeckillViewHolder(inflate(mContext, R.layout.seckill_item, null));
            case RECOMMEND:
                return new RecommendViewHolder(inflate(mContext, R.layout.recommend_item, null));
            case HOT:
                return new HotViewHolder(inflate(mContext, R.layout.hot_item, null));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder h, int position) {
        h.setData(result);
    }

    @Override
    public int getItemCount() {
        return 6;
    }


    class SeckillViewHolder extends BaseViewHolder {
        @InjectView(R.id.countdownview)
        CountdownView countdownView;
        @InjectView(R.id.tv_more_seckill)
        TextView tvMoreSeckill;
        @InjectView(R.id.rv_seckill)
        RecyclerView rvSeckill;

        private Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (countdownView != null) {
                    if (countdownView.getRemainTime() <= 0) {
                        handler.removeCallbacksAndMessages(null);
                    } else {
                        countdownView.updateShow(endTime - System.currentTimeMillis());
                        sendEmptyMessageDelayed(0, 1000);
                    }
                }
            }
        };

        public SeckillViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        @Override
        protected void setData(final HomeBean.ResultBean datas) {
            handler.removeCallbacksAndMessages(null);
            handler.sendEmptyMessageDelayed(0, 1000);
            countdownView.start(endTime - System.currentTimeMillis());

            SeckillRecyclerViewAdapter adapter = new SeckillRecyclerViewAdapter(mContext, datas.getSeckill_info());

            adapter.setOnItemClickListener(new SeckillRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
//                    Toast.makeText(mContext, ""  position, Toast.LENGTH_SHORT).show();

                    GoodsBean goodsBean = new GoodsBean();
                    HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = datas.getSeckill_info().getList().get(position);
                    goodsBean.setCover_price(listBean.getCover_price());
                    goodsBean.setName(listBean.getName());
                    goodsBean.setFigure(listBean.getFigure());
                    goodsBean.setProduct_id(listBean.getProduct_id());
                    mContext.startActivity(new Intent(mContext, GoodsInfoActivity.class).putExtra(GOODS_BEAN, goodsBean));

                }
            });
            rvSeckill.setAdapter(adapter);

            rvSeckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        }
    }

    class HotViewHolder extends BaseViewHolder {
        @InjectView(R.id.tv_more_hot)
        TextView tvMoreHot;
        @InjectView(R.id.gv_hot)
        GridView gvHot;

        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

        }

        @Override
        protected void setData(HomeBean.ResultBean datas) {
            final List<HomeBean.ResultBean.HotInfoBean> hot_info = datas.getHot_info();
            gvHot.setAdapter(new HotGridViewAdapter(mContext, hot_info));
            gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(mContext, "GridView="  position, Toast.LENGTH_SHORT).show();
                    GoodsBean goodsBean = new GoodsBean();
                    HomeBean.ResultBean.HotInfoBean hotInfoBean = hot_info.get(position);
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setFigure(hotInfoBean.getFigure());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());
                    mContext.startActivity(new Intent(mContext, GoodsInfoActivity.class).putExtra(GOODS_BEAN, goodsBean));

                }
            });
        }
    }

    class RecommendViewHolder extends BaseViewHolder {
        @InjectView(R.id.tv_more_recommend)
        TextView tvMoreRecommend;
        @InjectView(R.id.gv_recommend)
        GridView gvRecommend;

        public RecommendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

        }

        @Override
        protected void setData(HomeBean.ResultBean datas) {
            final List<HomeBean.ResultBean.RecommendInfoBean> recommend_info = datas.getRecommend_info();
            RecommendGridViewAdapter recommendGridViewAdapter = new RecommendGridViewAdapter(mContext, recommend_info);
            gvRecommend.setAdapter(recommendGridViewAdapter);

            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(mContext, "GridView="  position, Toast.LENGTH_SHORT).show();
                    GoodsBean goodsBean = new GoodsBean();
                    HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(position);
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());
                    mContext.startActivity(new Intent(mContext, GoodsInfoActivity.class).putExtra(GOODS_BEAN, goodsBean));


                }
            });
        }
    }

    class ActViewHolder extends BaseViewHolder {
        @InjectView(R.id.act_viewpager)
        ViewPager actViewpager;

        public ActViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(HomeBean.ResultBean datas) {
            final List<HomeBean.ResultBean.ActInfoBean> act_info = datas.getAct_info();
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mContext, act_info);

            viewPagerAdapter.setonPagerClickListener(new ViewPagerAdapter.onPagerClickListener() {
                @Override
                public void onClick(int position) {
//                    Toast.makeText(mContext, "ViewPager="  position, Toast.LENGTH_SHORT).show();
                    WebViewBean webViewBean = new WebViewBean();
                    webViewBean.setName(act_info.get(position).getName());
                    webViewBean.setIcon_url(act_info.get(position).getIcon_url());
                    webViewBean.setUrl(act_info.get(position).getUrl());

                    mContext.startActivity(new Intent(mContext, WebViewActivity.class).putExtra(WEBVIEW_BEAN,webViewBean));
                }
            });
            actViewpager.setAdapter(viewPagerAdapter);
//设置每个页面的间距
            actViewpager.setPageMargin(DensityUtil.px2dip(mContext, 20));
//>=3
            actViewpager.setOffscreenPageLimit(3);
//设置动画
            actViewpager.setPageTransformer(true, new
                    ScaleInTransformer());

        }
    }

    class ChannelViewHolder extends BaseViewHolder {
        @InjectView(R.id.gv_channel)
        GridView gvChannel;

        public ChannelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(HomeBean.ResultBean datas) {
            List<HomeBean.ResultBean.ChannelInfoBean> channel_info = datas.getChannel_info();
            gvChannel.setAdapter(new ChannelAdapter(mContext, channel_info));
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(mContext, position  "", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    class BannerViewHolder extends BaseViewHolder {
        @InjectView(R.id.banner)
        Banner banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(HomeBean.ResultBean datas) {
            final List<HomeBean.ResultBean.BannerInfoBean> banner_info = datas.getBanner_info();

            banner.setBannerAnimation(Transformer.FlipHorizontal);
            banner.setBannerStyle(BannerConfig.NUM_INDICATOR);

//            banner.setIndicatorGravity(BannerConfig.RIGHT);

            ArrayList<String> images = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                images.add(Constants.BASE_URL_IMAGE +banner_info.get(i).getImage());
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
                    String product_id = "";
                    String name = "";
                    String cover_price = "";
                    String image = "";
                    if (position == 0) {
                        product_id = "627";
                        cover_price = "32.00";
                        name = "剑三T恤批发";
                    } else if (position == 1) {
                        product_id = "21";
                        cover_price = "8.00";
                        name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                    } else {
                        product_id = "1341";
                        cover_price = "50.00";
                        name = "【蓝诺】《天下吾双》 剑网3同人本";
                    }

                    image = banner_info.get(position).getImage();

                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setProduct_id(product_id);
                    goodsBean.setName(name);
                    goodsBean.setCover_price(cover_price);
                    goodsBean.setFigure(image);
                    mContext.startActivity(new Intent(mContext, GoodsInfoActivity.class).putExtra(GOODS_BEAN, goodsBean));
                }
            });
        }
    }


}
