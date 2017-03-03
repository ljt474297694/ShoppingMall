package com.atguigu.shoppingmall.type.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.app.GoodsInfoActivity;
import com.atguigu.shoppingmall.home.adapter.HomeAdapter;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.type.bean.TypeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.atguigu.shoppingmall.utils.DensityUtil;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 李金桐 on 2017/3/3.
 * QQ: 474297694
 * 功能: xxxx
 */

public class TypeRightAdapter extends RecyclerView.Adapter<TypeBaseViewHolder> {


    private  List<TypeBean.ResultBean.ChildBean> child;
    private  Context mContext;
    private  List<TypeBean.ResultBean.HotProductListBean> hot_product_list;

    /**
     * 热卖推荐
     */
    private static final int HOT = 0;
    /**
     * 常用分类
     */
    private static final int COMMON = 1;

    @Override
    public int getItemViewType(int position) {
        return position == HOT ? HOT : COMMON;
    }

    public TypeRightAdapter(Context mContext, List<TypeBean.ResultBean> result) {
        this.mContext = mContext;
        this.child = result.get(0).getChild();
        this.hot_product_list = result.get(0).getHot_product_list();
    }
    public void refresh(List<TypeBean.ResultBean> result){
        if(result.size()==0||result==null) {
            return;
        }
        this.child = result.get(0).getChild();
        this.hot_product_list = result.get(0).getHot_product_list();
        notifyDataSetChanged();

    }
    @Override
    public TypeBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HOT:
                return new HotViewHolder(View.inflate(mContext, R.layout.item_hot_right, null));
            case COMMON:
                return new CommonViewHolder(View.inflate(mContext, R.layout.item_common_right, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(TypeBaseViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HOT:
                holder.setData();
            case COMMON:
                holder.setData(position-1);
        }

    }

    @Override
    public int getItemCount() {
        return 1 + child.size();
    }

    class CommonViewHolder extends TypeBaseViewHolder {
        @InjectView(R.id.iv_ordinary_right)
        ImageView ivCommonRight;
        @InjectView(R.id.tv_ordinary_right)
        TextView tvCommonRight;
        @InjectView(R.id.ll_root)
        LinearLayout llRoot;

        public CommonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

        }

        @Override
        public void setData(final int position) {
            TypeBean.ResultBean.ChildBean bean = child.get(position);

            //设置图片
            Glide.with(mContext).load(Constants.BASE_URL_IMAGE + bean.getPic()).placeholder(R.drawable.new_img_loading_2)
                    .error(R.drawable.new_img_loading_2).into(ivCommonRight);

            //设置名称
            tvCommonRight.setText(bean.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, ""+position, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    class HotViewHolder extends TypeBaseViewHolder {
        @InjectView(R.id.ll_hot_right)
        LinearLayout llHotRight;
        @InjectView(R.id.hsl_hot_right)
        HorizontalScrollView hslHotRight;

        HotViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
        @Override
        public void setData() {
            llHotRight.removeAllViews();
            for (int i = 0; i < hot_product_list.size(); i++) {

                TypeBean.ResultBean.HotProductListBean bean = hot_product_list.get(i);

                /**
                 * 线性布局
                 */
                LinearLayout myLinear = new LinearLayout(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);

                params.setMargins(DensityUtil.dip2px(mContext, 5), 0, DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 20));
                myLinear.setOrientation(LinearLayout.VERTICAL);
                myLinear.setGravity(Gravity.CENTER);
                myLinear.setTag(i);


                //图片
                ImageView imageView = new ImageView(mContext);
                LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(DensityUtil.dip2px(mContext, 80), DensityUtil.dip2px(mContext, 80));
                ivParams.setMargins(0, 0, 0, DensityUtil.dip2px(mContext, 10));

                //请求图片
                Glide.with(mContext).load(Constants.BASE_URL_IMAGE + bean.getFigure()).into(imageView);

                myLinear.addView(imageView, ivParams);


                //文字
                LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                TextView textView = new TextView(mContext);
                textView.setGravity(Gravity.CENTER);
//                textView.setTextColor(Color.RED);
                textView.setTextColor(Color.parseColor("#ed3f3f"));
                textView.setText("￥" + bean.getCover_price());

                myLinear.addView(textView, tvParams);

                llHotRight.addView(myLinear, params);

                myLinear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) v.getTag();
                        String cover_price = hot_product_list.get(position).getCover_price();
                        String name = hot_product_list.get(position).getName();
                        String figure = hot_product_list.get(position).getFigure();
                        String product_id = hot_product_list.get(position).getProduct_id();
                        GoodsBean goodsBean = new GoodsBean();
                        goodsBean.setProduct_id(product_id);
                        goodsBean.setFigure(figure);
                        goodsBean.setCover_price(cover_price);
                        goodsBean.setName(name);

                        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                        intent.putExtra(HomeAdapter.GOODS_BEAN, goodsBean);
                        mContext.startActivity(intent);

                    }
                });
            }
        }
    }
}
