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
import com.atguigu.shoppingmall.shoppingcart.utils.CartStorage;
import com.atguigu.shoppingmall.shoppingcart.view.AddSubView;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.Iterator;
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
    public final List<GoodsBean> datas;
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

    public void showTotalPrice() {
        tvShopcartTotal.setText(getTotalPrice() + "￥");
    }

    public double getTotalPrice() {
        double b = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isChecked()) {
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
        final GoodsBean goodsBean = datas.get(position);
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
        holder.addSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChenge(int number) {
                goodsBean.setNumber(number);

                CartStorage.getInstance(mContext).updataData(goodsBean);

                showTotalPrice();
        }
    });
        holder.express_name.setText("由"+goodsBean.getExpress()+"发货");
}

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void checkAll() {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                if (!datas.get(i).isChecked()) {
                    checkboxAll.setChecked(false);
                    checkboxDeleteAll.setChecked(false);
                    return;
                }
            }
            //循环结束到此处 表示都为勾选 改变全选状态
            checkboxAll.setChecked(true);
            checkboxDeleteAll.setChecked(true);

        } else {
            checkboxAll.setChecked(false);
            checkboxDeleteAll.setChecked(false);
        }
    }

    public void checkAll_none(boolean checked) {
        if (datas == null || datas.size() == 0) {
            return;
        }
        for (int i = 0; i < datas.size(); i++) {
            //根据传入状态 改变状态
            datas.get(i).setChecked(checked);
            checkboxAll.setChecked(checked);
            checkboxDeleteAll.setChecked(checked);
            //更新视图
            notifyItemChanged(i);
        }
    }

    public void deleteData() {
        if(datas != null && datas.size() >0){
            for(Iterator iterator = datas.iterator(); iterator.hasNext();){
                GoodsBean cart = (GoodsBean) iterator.next();
                if(cart.isChecked()){
                    //根据对象找到在列表中的位置
                    int position = datas.indexOf(cart);
                    //从本地中删除
                    CartStorage.getInstance(mContext).deleteData(cart);
                    iterator.remove();
                    //刷新页面
                    notifyItemRemoved(position);
                }
            }
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
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
        @InjectView(R.id.express_name)
        TextView express_name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClickListener(v, getLayoutPosition());
                    }
                }
            });
        }
    }

    //回调点击事件的监听
    private OnItemClickListener itemClickListener;

    /**
     * 点击item的监听
     */
    public interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    /**
     * 设置item的监听
     *
     * @param l
     */
    public void setOnItemClickListener(OnItemClickListener l) {
        this.itemClickListener = l;
    }

}
