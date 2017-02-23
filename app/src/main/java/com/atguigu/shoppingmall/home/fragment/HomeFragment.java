package com.atguigu.shoppingmall.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.home.adapter.HomeAdapter;
import com.atguigu.shoppingmall.home.bean.HomeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by 李金桐 on 2017/2/22.
 * QQ: 474297694
 * 功能: 主页Fragment
 */
public class HomeFragment extends BaseFragment {

    @InjectView(R.id.ll_main_scan)
    LinearLayout llMainScan;
    @InjectView(R.id.tv_search_home)
    TextView tvSearchHome;
    @InjectView(R.id.tv_message_home)
    TextView tvMessageHome;
    @InjectView(R.id.rv_home)
    RecyclerView rvHome;
    @InjectView(R.id.ib_top)
    ImageButton ibTop;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.HOME_URL)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "HomeFragment onError()"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String json, int id) {
                        processData(json);
                    }
                });
    }

    private void processData(String json) {
        HomeBean homeBean = JSON.parseObject(json,HomeBean.class);
        HomeAdapter adapter =   new HomeAdapter(mContext,homeBean.getResult());
        rvHome.setAdapter(adapter);

        rvHome.setLayoutManager(new LinearLayoutManager(mContext ,LinearLayoutManager.VERTICAL,false));


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.ll_main_scan, R.id.tv_search_home, R.id.tv_message_home, R.id.ib_top})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main_scan:
                Toast.makeText(mContext, "扫一扫", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_search_home:
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home:
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_top:
                Toast.makeText(mContext, "回到顶部", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
