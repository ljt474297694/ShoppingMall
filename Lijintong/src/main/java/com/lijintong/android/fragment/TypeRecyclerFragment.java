package com.lijintong.android.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lijintong.android.R;
import com.lijintong.android.base.BaseFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by 李金桐 on 2017/3/9.
 * QQ: 474297694
 * 功能: xxxx
 */

public class TypeRecyclerFragment extends BaseFragment {
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private Adapter adapter;
    ArrayList<String> datas;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type_recycler, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                datas.add("我是类型1 (。・`ω´・)");
            } else {
                datas.add("我是类型2 (ง •̀_•́)ง");
            }

        }
        adapter = new Adapter();
        recyclerview.setAdapter(adapter);

        recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(View.inflate(mContext, R.layout.item_recyclerview, null));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textview.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.textview)
        TextView textview;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, getLayoutPosition()%2== 0 ? "我是类型1 (。・`ω´・)" : "我是类型2 (ง •̀_•́)ง", Toast.LENGTH_SHORT).show();
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(mContext)
                            .setTitle("确定删除吗")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    datas.remove(getLayoutPosition());
                                    adapter.notifyItemRemoved(getLayoutPosition());
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();

                    return false;
                }
            });
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
