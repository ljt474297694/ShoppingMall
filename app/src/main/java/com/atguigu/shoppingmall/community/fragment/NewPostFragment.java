package com.atguigu.shoppingmall.community.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.community.adapter.NewPostListViewAdapter;
import com.atguigu.shoppingmall.community.bean.NewPostBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.atguigu.shoppingmall.utils.NetUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 李金桐 on 2017/3/3.
 * QQ: 474297694
 * 功能: xxxx
 */

public class NewPostFragment extends BaseFragment {

    @InjectView(R.id.lv_new_post)
    ListView lvNewPost;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_new_post, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        NetUtils.getInstance().okhttpUtilsGet(Constants.NEW_POST_URL, NewPostBean.class, new NetUtils.responseBean<NewPostBean>() {
            @Override
            public void onResponse(NewPostBean bean) {
                setAdapter(bean);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "NewPostFragment onError()" + e.getMessage());
            }
        });
    }

    private void setAdapter(NewPostBean bean) {
        NewPostListViewAdapter adapter = new NewPostListViewAdapter(mContext, bean.getResult());
        lvNewPost.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
