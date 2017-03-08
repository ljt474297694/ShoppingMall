package com.atguigu.shoppingmall.shoppingcart.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 李金桐 on 2017/3/8.
 * QQ: 474297694
 * 功能: xxxx
 */

public class PaymentAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<GoodsBean> datas;

    public PaymentAdapter(Context mContext, List<GoodsBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_checkout, null);
            viewHolder= new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + datas.get(position).getFigure()).into(viewHolder.ivGov);

        viewHolder.tvPriceGov.setText("￥"+datas.get(position).getCover_price());

        viewHolder.tvDescGov.setText(datas.get(position).getName());
        return convertView;
    }

     class ViewHolder {
        @InjectView(R.id.iv_gov)
        ImageView ivGov;
        @InjectView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @InjectView(R.id.tv_price_gov)
        TextView tvPriceGov;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
