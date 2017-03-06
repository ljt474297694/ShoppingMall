package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.TypeListBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.atguigu.shoppingmall.R.id.iv_hot;

/**
 * Created by 李金桐 on 2017/3/6.
 * QQ: 474297694
 * 功能: xxxx
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder> {


    private final List<TypeListBean.ResultEntity.PageDataEntity> datas;
    private Context mContext;

    public GoodsListAdapter(Context context, TypeListBean.ResultEntity datas) {
        this.mContext = context;
        this.datas = datas.getPage_data();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_goods_list, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(datas.get(position).getName());
        holder.tvPrice.setText("￥"+datas.get(position).getCover_price());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE +datas.get(position).getFigure()).into(holder.ivHot);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(iv_hot)
        ImageView ivHot;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(l!=null) {
                        l.setOnItemClickListener(datas.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    private OnItemClickListener l;

    /**
     * 设置item的点击事件的监听
     */
    public interface OnItemClickListener {
        void setOnItemClickListener(TypeListBean.ResultEntity.PageDataEntity data);
    }

    /**
     设置监听
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.l = onItemClickListener;
    }

}
