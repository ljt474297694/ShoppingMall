package com.atguigu.shoppingmall.type.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.type.adapter.TagGridViewAdapter;
import com.atguigu.shoppingmall.type.bean.TagBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 李金桐 on 2017/3/3.
 * QQ: 474297694
 * 功能: xxxx
 */

public class TagFragment extends BaseFragment {
    @InjectView(R.id.gv_tag)
    GridView gvTag;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_tag, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    public void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.TAG_URL)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "TagFragment onError()"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if(!TextUtils.isEmpty(response)) {
                            processData(response);
                        }
                    }
                });
    }

    private void processData(String response) {
        TagBean tagBean = JSON.parseObject(response,TagBean.class);

        gvTag.setAdapter(new TagGridViewAdapter(mContext,tagBean.getResult()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}