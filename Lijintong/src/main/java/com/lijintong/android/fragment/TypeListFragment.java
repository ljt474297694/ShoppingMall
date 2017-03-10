package com.lijintong.android.fragment;


import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lijintong.android.R;
import com.lijintong.android.base.BaseFragment;
import com.lijintong.android.bean.TypeListBean;
import com.lijintong.android.utils.CacheUtils;
import com.lijintong.android.utils.Constants;
import com.lijintong.android.utils.NetUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;


/**
 * Created by 李金桐 on 2017/3/9.
 * QQ: 474297694
 * 功能: xxxx
 */

public class TypeListFragment extends BaseFragment {
    @InjectView(R.id.pull_refresh_list)
    PullToRefreshListView refreshListView;

    ListView listview;

    private Adapter adapter;
    private List<TypeListBean.ResultBean> datas;
    private boolean isLoadMore;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type_list, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        listview = refreshListView.getRefreshableView();
        String string = CacheUtils.getString(mContext, Constants.NEW_POST_URL);
        if (!TextUtils.isEmpty(string)) {
            setAdapter(JSON.parseObject(string, TypeListBean.class));
        }
        getDataFromNet();
        initListener();
    }

    private void initListener() {
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(mContext, "下拉", Toast.LENGTH_SHORT).show();
                isLoadMore = false;
                getDataFromNet();
            }


            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Toast.makeText(mContext, "上拉", Toast.LENGTH_SHORT).show();
                isLoadMore = true;
                getDataFromNet();
            }
        });

    }

    private void getDataFromNet() {
        NetUtils.getInstance(mContext).okhttpUtilsGet(Constants.NEW_POST_URL, TypeListBean.class, new NetUtils.responseBean<TypeListBean>() {
            @Override
            public void onResponse(TypeListBean bean) {
                refreshListView.onRefreshComplete();
                if (!isLoadMore) {
                    setAdapter(bean);
                } else if (datas != null && adapter != null) {
                    datas.addAll(bean.getResult());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "TypeListFragment onError()" + e.getMessage());
            }
        });
    }

    private void setAdapter(TypeListBean bean) {
        datas = bean.getResult();
        adapter = new Adapter();
        listview.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
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
                convertView = View.inflate(mContext, R.layout.item_listview_new_post, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            TypeListBean.ResultBean resultBean = datas.get(position);

            viewHolder.tvCommunitySaying.setText(resultBean.getSaying());
            viewHolder.tvCommunityUsername.setText(resultBean.getUsername());

            Glide.with(mContext).load(Constants.BASE_URL_IMAGE + resultBean.getAvatar()).into(viewHolder.ibNewPostAvatar);
            Glide.with(mContext).load(Constants.BASE_URL_IMAGE + resultBean.getFigure()).into(viewHolder.ivCommunityFigure);

            return convertView;
        }

        class ViewHolder {
            @InjectView(R.id.tv_community_username)
            TextView tvCommunityUsername;
            @InjectView(R.id.tv_community_addtime)
            TextView tvCommunityAddtime;
            @InjectView(R.id.rl)
            RelativeLayout rl;
            @InjectView(R.id.ib_new_post_avatar)
            ImageButton ibNewPostAvatar;
            @InjectView(R.id.iv_community_figure)
            ImageView ivCommunityFigure;
            @InjectView(R.id.tv_community_saying)
            TextView tvCommunitySaying;

            ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
        }
    }

}
