package com.atguigu.shoppingmall.home.adapter;



import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.atguigu.shoppingmall.home.bean.HomeBean;

/**
 * Created by 李金桐 on 2017/2/24.
 * QQ: 474297694
 * 功能: ViewHolder的基类 BaseViewHolder 各种类型的子类会继承此类
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void setData(HomeBean.ResultBean datas);
}