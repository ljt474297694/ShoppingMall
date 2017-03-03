package com.atguigu.shoppingmall.type.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.type.adapter.TypeLeftAdapter;
import com.atguigu.shoppingmall.type.adapter.TypeRightAdapter;
import com.atguigu.shoppingmall.type.bean.TypeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.atguigu.shoppingmall.utils.NetUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;


/**
 * Created by 李金桐 on 2017/3/3.
 * QQ: 474297694
 * 功能: xxxx
 */

public class ListFragment extends BaseFragment {
    @InjectView(R.id.lv_left)
    ListView lvLeft;
    @InjectView(R.id.rv_right)
    RecyclerView rvRight;
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品",
            "办公文具", "数码周边", "游戏专区"};
    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};
    private TypeLeftAdapter leftAdapter;
    private TypeRightAdapter rightAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        leftAdapter = new TypeLeftAdapter(mContext, titles);
        lvLeft.setAdapter(leftAdapter);

        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //1.设置点击的位置
                leftAdapter.position = position;
                //2.在TypeLeftAdapter getView根据位置中高亮显示代码
                //3.刷新适配器
                leftAdapter.notifyDataSetChanged();
                getDataFromNet(urls[position]);

            }
        });
        getDataFromNet(urls[0]);

    }

    //联网请求
    private void getDataFromNet(String url) {
        NetUtils.getInstance().okhttpUtilsGet(url, TypeBean.class, new NetUtils.responseBean<TypeBean>() {
            @Override
            public void onResponse(TypeBean bean) {
                prossData(bean);
            }
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "ListFragment onError()"+e.getMessage());
            }
        });
    }


    //解析数据 设置适配器
    private void prossData(TypeBean typeBean) {

        List<TypeBean.ResultBean> result = typeBean.getResult();

        if (result == null || result.size() == 0) return;

        if (rightAdapter != null) {

            rightAdapter.refresh(result);
            rvRight.scrollToPosition(0);
        } else {
            rightAdapter = new TypeRightAdapter(mContext, result);


            rvRight.setAdapter(rightAdapter);

            GridLayoutManager manager = new GridLayoutManager(mContext, 3);

            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

                @Override
                public int getSpanSize(int position) {
                    if (position == 0) {
                        return 3;
                    } else {
                        return 1;
                    }
                }
            });

            rvRight.setLayoutManager(manager);
        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}