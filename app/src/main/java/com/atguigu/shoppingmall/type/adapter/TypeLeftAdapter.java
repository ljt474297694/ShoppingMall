package com.atguigu.shoppingmall.type.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 李金桐 on 2017/3/3.
 * QQ: 474297694
 * 功能: xxxx
 */

public class TypeLeftAdapter extends BaseAdapter {
    private final Context mContext;
    private final String[] datas;
    public int position;

    public TypeLeftAdapter(Context mContext, String[] titles) {
        this.mContext = mContext;
        this.datas = titles;

    }

    @Override
    public int getCount() {
        return datas.length;
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
            convertView = View.inflate(mContext, R.layout.item_type, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTitle.setText(datas[position]);
        if(this.position == position) {
            viewHolder.tvTitle.setTextColor(Color.parseColor("#fd3f3f"));
            convertView.setBackgroundColor(Color.parseColor("#ffffff"));
        }else{
            viewHolder.tvTitle.setTextColor(Color.parseColor("#323437"));
            convertView.setBackgroundColor(Color.parseColor("#11000000"));
        }
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
