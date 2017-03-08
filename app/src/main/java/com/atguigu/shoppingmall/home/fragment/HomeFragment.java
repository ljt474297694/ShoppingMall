package com.atguigu.shoppingmall.home.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.app.GoodsInfoActivity;
import com.atguigu.shoppingmall.app.SearchActivity;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.home.adapter.HomeAdapter;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.home.bean.HomeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.atguigu.shoppingmall.R.id.ib_top;
import static com.atguigu.shoppingmall.home.adapter.HomeAdapter.GOODS_BEAN;

/**
 * Created by 李金桐 on 2017/2/22.
 * QQ: 474297694
 * 功能: 主页Fragment
 */
public class HomeFragment extends BaseFragment {

    @InjectView(R.id.ll_main_scan)
    LinearLayout llMainScan;
    @InjectView(R.id.tv_search_home)
    TextView tvSearchHome;
    @InjectView(R.id.tv_message_home)
    TextView tvMessageHome;
    @InjectView(R.id.rv_home)
    RecyclerView rvHome;
    @InjectView(ib_top)
    ImageButton ibTop;
    private HomeAdapter adapter;
    private HomeBean homeBean;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
        initListener();
    }

    private void initListener() {

    }

    /**
     * 联网获取数据
     */
    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.HOME_URL)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "HomeFragment onError()" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String json, int id) {
                        processData(json);
                    }
                });
    }

    /**
     * 解析json数据
     *
     * @param json
     */
    private void processData(String json) {
        if (TextUtils.isEmpty(json)) {
            return;
        }
        homeBean = JSON.parseObject(json, HomeBean.class);
        adapter = new HomeAdapter(mContext, homeBean.getResult());
        rvHome.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
        //设置滑动到哪个位置了的监听
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position <= 3) {
                    ibTop.setVisibility(View.GONE);
                } else {
                    ibTop.setVisibility(View.VISIBLE);
                }
                //只能返回1

                return 1;
            }
        });//设置网格布局
        rvHome.setLayoutManager(manager);

    }


    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    @OnClick({R.id.ll_main_scan, R.id.tv_search_home, R.id.tv_message_home, ib_top})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main_scan:
                Toast.makeText(mContext, "扫一扫", Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                Intent intent = new Intent(mContext, CaptureActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.tv_search_home:
//                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(mContext, SearchActivity.class));
            break;
            case R.id.tv_message_home:
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                break;
            case ib_top:
                rvHome.scrollToPosition(0);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //跳转商品页面
                    startShop(result);

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    //跳转商品页面
    private void startShop(String result) {
        List<HomeBean.ResultBean.BannerInfoBean> banner_info = homeBean.getResult().getBanner_info();
        List<HomeBean.ResultBean.HotInfoBean> hot_info = homeBean.getResult().getHot_info();
        List<HomeBean.ResultBean.RecommendInfoBean> recommend_info = homeBean.getResult().getRecommend_info();

        for (int i = 0; i < banner_info.size(); i++) {
            HomeBean.ResultBean.BannerInfoBean bannerInfoBean = banner_info.get(i);
            String imageUrl = Constants.BASE_URL_IMAGE + bannerInfoBean.getImage();
            if (imageUrl.equals(result)) {
                String product_id = "627";
                String cover_price = "32.00";
                String name = "剑三T恤批发";
                String image = banner_info.get(i).getImage();
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setProduct_id(product_id);
                goodsBean.setName(name);
                goodsBean.setCover_price(cover_price);
                goodsBean.setFigure(image);
                mContext.startActivity(new Intent(mContext, GoodsInfoActivity.class).putExtra(GOODS_BEAN, goodsBean));
                return;
            }
        }

        for(int i = 0; i <hot_info.size() ; i++) {
            HomeBean.ResultBean.HotInfoBean hotInfoBean = hot_info.get(i);
            String imageUrl = Constants.BASE_URL_IMAGE + hotInfoBean.getFigure();
            if (imageUrl.equals(result)) {
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setCover_price(hotInfoBean.getCover_price());
                goodsBean.setName(hotInfoBean.getName());
                goodsBean.setFigure(hotInfoBean.getFigure());
                goodsBean.setProduct_id(hotInfoBean.getProduct_id());
                mContext.startActivity(new Intent(mContext, GoodsInfoActivity.class).putExtra(GOODS_BEAN, goodsBean));
                return;
            }
        }

        for(int i = 0; i <recommend_info.size() ; i++) {
            HomeBean.ResultBean.RecommendInfoBean infoBean = recommend_info.get(i);
            String imageUrl = Constants.BASE_URL_IMAGE + infoBean.getFigure();
            if (imageUrl.equals(result)) {
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setCover_price(infoBean.getCover_price());
                goodsBean.setName(infoBean.getName());
                goodsBean.setFigure(infoBean.getFigure());
                goodsBean.setProduct_id(infoBean.getProduct_id());
                mContext.startActivity(new Intent(mContext, GoodsInfoActivity.class).putExtra(GOODS_BEAN, goodsBean));
                return;
            }
        }
    }
}
