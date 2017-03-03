package com.atguigu.shoppingmall.type.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 李金桐 on 2017/3/3.
 * QQ: 474297694
 * 功能: xxxx
 */

public abstract class TypeBaseViewHolder extends RecyclerView.ViewHolder {

    public TypeBaseViewHolder(View itemView) {
        super(itemView);
    }

    public  void setData(){}
    public  void setData(int position){}
}
