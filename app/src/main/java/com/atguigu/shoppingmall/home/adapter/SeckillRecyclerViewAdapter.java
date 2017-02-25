package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.HomeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 李金桐 on 2017/2/25.
 * QQ: 474297694
 * 功能: xxxx
 */

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHolder> {


    private final Context mContext;
    private final List<HomeBean.ResultBean.SeckillInfoBean.ListBean> datas;

    public SeckillRecyclerViewAdapter(Context mContext, HomeBean.ResultBean.SeckillInfoBean seckill_info) {
        this.mContext = mContext;
        this.datas = seckill_info.getList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_seckill, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvCoverPrice.setText("￥"+datas.get(position).getCover_price());

        holder.tvOriginPrice.setText("￥"+datas.get(position).getOrigin_price());

        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+datas.get(position).getFigure()).into(holder.ivFigure);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_figure)
        ImageView ivFigure;
        @InjectView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @InjectView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @InjectView(R.id.ll_root)
        LinearLayout llRoot;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(l!=null) {
                        l.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    interface OnItemClickListener{
        void onClick(int position);
    }
    private OnItemClickListener l;

    public void setOnItemClickListener(OnItemClickListener l) {
        this.l = l;
    }
}
