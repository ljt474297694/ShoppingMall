package com.atguigu.shoppingmall.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.shoppingcart.view.AddSubView;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 李金桐 on 2017/2/28.
 * QQ: 474297694
 * 功能: xxxx
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private final Context mContext;
    private final List<GoodsBean> datas;
    private final CheckBox checkboxDeleteAll;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;

    public ShoppingCartAdapter(Context mContext, List<GoodsBean> datas, CheckBox checkboxAll, TextView tvShopcartTotal, CheckBox checkboxDeleteAll) {
        this.mContext = mContext;
        this.datas = datas;
        this.checkboxAll = checkboxAll;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxDeleteAll = checkboxDeleteAll;
        showTotalPrice();
    }

    private void showTotalPrice() {
        tvShopcartTotal.setText(getTotalPrice() + "");
    }

    private double getTotalPrice() {
        double b = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if(goodsBean.isChecked()) {
                    b += Double.parseDouble(goodsBean.getCover_price()) * goodsBean.getNumber();
                }
            }
        }
        return b;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_shop_cart, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoodsBean goodsBean = datas.get(position);
        //2.绑定数据
        holder.cbGov.setChecked(goodsBean.isChecked());
        //图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(holder.ivGov);
        holder.tvDescGov.setText(goodsBean.getName());
        //设置价格
        holder.tvPriceGov.setText("￥" + goodsBean.getCover_price());
        holder.addSubView.setValue(goodsBean.getNumber());

        holder.addSubView.setMinValue(1);
        //设置库存
        holder.addSubView.setMaxValue(100);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.cb_gov)
        CheckBox cbGov;
        @InjectView(R.id.iv_gov)
        ImageView ivGov;
        @InjectView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @InjectView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @InjectView(R.id.addSubView)
        AddSubView addSubView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }


}
