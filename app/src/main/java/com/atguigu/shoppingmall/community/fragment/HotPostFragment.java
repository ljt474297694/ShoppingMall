package com.atguigu.shoppingmall.community.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.community.adapter.HotPostListViewAdapter;
import com.atguigu.shoppingmall.community.bean.HotPostBean;
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

public class HotPostFragment extends BaseFragment {

    @InjectView(R.id.lv_hot_post)
    ListView lvHotPost;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_hot_post, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        getDataFromNet();
    }

    private void getDataFromNet() {
        NetUtils.getInstance().okhttpUtilsGet
                (Constants.HOT_POST_URL, HotPostBean.class, new NetUtils.responseBean<HotPostBean>() {
                    @Override
                    public void onResponse(HotPostBean bean) {
                        setAdapter(bean);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "HotPostFragment onError()"+e.getMessage());
                    }
                });
    }

    private void setAdapter(HotPostBean bean) {
        HotPostListViewAdapter adapter = new HotPostListViewAdapter(mContext, bean.getResult());
        lvHotPost.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
