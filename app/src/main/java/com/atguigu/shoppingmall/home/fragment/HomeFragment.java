package com.atguigu.shoppingmall.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

import static com.atguigu.shoppingmall.R.id.ib_top;

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
    @InjectView(ib_top)
    ImageButton ibTop;
    private HomeAdapter adapter;

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
        initListener();
    }

    private void initListener() {

    }

    /**
     * 联网获取数据
     */
    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.HOME_URL)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "HomeFragment onError()" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String json, int id) {
                        processData(json);
                    }
                });
    }

    /**
     * 解析json数据
     * @param json
     */
    private void processData(String json) {
        if (TextUtils.isEmpty(json)) {
            return;
        }
        HomeBean homeBean = JSON.parseObject(json, HomeBean.class);
        adapter = new HomeAdapter(mContext, homeBean.getResult());
        rvHome.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
//设置滑动到哪个位置了的监听
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position <= 3) {
                    ibTop.setVisibility(View.GONE);
                } else {
                    ibTop.setVisibility(View.VISIBLE);
                }
                //只能返回1

                return 1;
            }
        });
//设置网格布局
        rvHome.setLayoutManager(manager);

    }


    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    @OnClick({R.id.ll_main_scan, R.id.tv_search_home, R.id.tv_message_home, ib_top})
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
            case ib_top:
                rvHome.scrollToPosition(0);
                break;
        }
    }


}
