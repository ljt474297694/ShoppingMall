package com.atguigu.shoppingmall.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.app.GoodsInfoActivity;
import com.atguigu.shoppingmall.home.adapter.GoodsListAdapter;
import com.atguigu.shoppingmall.home.adapter.HomeAdapter;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.home.bean.TypeListBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.atguigu.shoppingmall.utils.DensityUtil;
import com.atguigu.shoppingmall.utils.NetUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class GoodsListActivity extends AppCompatActivity {

    @InjectView(R.id.ib_goods_list_back)
    ImageButton ibGoodsListBack;
    @InjectView(R.id.tv_goods_list_search)
    TextView tvGoodsListSearch;
    @InjectView(R.id.ib_goods_list_home)
    ImageButton ibGoodsListHome;
    @InjectView(R.id.tv_goods_list_sort)
    TextView tvGoodsListSort;
    @InjectView(R.id.tv_goods_list_price)
    TextView tvGoodsListPrice;
    @InjectView(R.id.iv_goods_list_arrow)
    ImageView ivGoodsListArrow;
    @InjectView(R.id.ll_goods_list_price)
    LinearLayout llGoodsListPrice;
    @InjectView(R.id.tv_goods_list_select)
    TextView tvGoodsListSelect;
    @InjectView(R.id.ll_goods_list_head)
    LinearLayout llGoodsListHead;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.dl_left)
    DrawerLayout dlLeft;
    /**
     * 请求网络
     */
    private String[] urls = new String[]{
            Constants.CLOSE_STORE,
            Constants.GAME_STORE,
            Constants.COMIC_STORE,
            Constants.COSPLAY_STORE,
            Constants.GUFENG_STORE,
            Constants.STICK_STORE,
            Constants.WENJU_STORE,
            Constants.FOOD_STORE,
            Constants.SHOUSHI_STORE,
    };
    private int position;
    private GoodsListAdapter adapter;

    private boolean isDesc = true; // 默认箭头向下红

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        initView();
        getData();
        getDataFromNet();
    }

    private void initView() {
        ButterKnife.inject(this);
        testToRed(tvGoodsListSort);
    }

    private void getDataFromNet() {
        NetUtils.getInstance().okhttpUtilsGet(urls[position], TypeListBean.class, new NetUtils.responseBean<TypeListBean>() {
            @Override
            public void onResponse(TypeListBean bean) {
                setAdapter(bean);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "GoodsListActivity onError()" + e.getMessage());
            }
        });
    }

    private void setAdapter(TypeListBean bean) {
        adapter = new GoodsListAdapter(this, bean.getResult());
        adapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(TypeListBean.ResultEntity.PageDataEntity data) {
                String name = data.getName();
                String cover_price = data.getCover_price();
                String figure = data.getFigure();
                String product_id = data.getProduct_id();

                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setName(name);
                goodsBean.setFigure(figure);
                goodsBean.setCover_price(cover_price);
                goodsBean.setProduct_id(product_id);

                Intent intent = new Intent(GoodsListActivity.this, GoodsInfoActivity.class);
                intent.putExtra(HomeAdapter.GOODS_BEAN, goodsBean);
                startActivity(intent);
            }
        });

        recyclerview.setAdapter(adapter);
        //设置分割线
        recyclerview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                //不是第一个的格子都设一个左边和底部的间距
                outRect.left = DensityUtil.dip2px(GoodsListActivity.this, 10);
                outRect.bottom = DensityUtil.dip2px(GoodsListActivity.this, 10);
                //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
                if (parent.getChildLayoutPosition(view) % 2 == 0) outRect.left = 0;
            }
        });

        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));

    }

    private void getData() {
        position = getIntent().getIntExtra("position", 0);
    }

    @OnClick({R.id.ib_goods_list_back, R.id.tv_goods_list_search, R.id.ib_goods_list_home, R.id.tv_goods_list_sort, R.id.tv_goods_list_price, R.id.tv_goods_list_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_goods_list_back:
                finish();
                break;
            case R.id.tv_goods_list_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_goods_list_home:
                finish();
                break;
            case R.id.tv_goods_list_sort:
                isDesc = true;
                ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_normal);
                testToRed(tvGoodsListSort);
                break;
            case R.id.tv_goods_list_price:
                if (isDesc) {
                    // 箭头向下红
                    ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_desc);
                } else {
                    // 箭头向上红
                    ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_asc);
                }
                isDesc = !isDesc; //取反下次点击 箭头向上红
                testToRed(tvGoodsListPrice);
                break;
            case R.id.tv_goods_list_select:
                isDesc = true;
                ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_normal);
                testToRed(tvGoodsListSelect);
                break;
        }
    }

    //高亮传入的TextView的文字
    private void testToRed(TextView textView) {
        //设置默认
        tvGoodsListSort.setTextColor(Color.parseColor("#333538"));
        tvGoodsListPrice.setTextColor(Color.parseColor("#333538"));
        tvGoodsListSelect.setTextColor(Color.parseColor("#333538"));
        textView.setTextColor(Color.parseColor("#ed4141"));
    }

}
