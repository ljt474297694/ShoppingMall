package com.atguigu.shoppingmall.community.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.community.bean.NewPostBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;
import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.opendanmaku.IDanmakuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 李金桐 on 2017/3/4.
 * QQ: 474297694
 * 功能: xxxx
 */

public class NewPostListViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<NewPostBean.ResultBean> datas;

    public NewPostListViewAdapter(Context mContext, List<NewPostBean.ResultBean> result) {
        this.mContext = mContext;
        this.datas = result;
    }

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
            viewHolder= new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NewPostBean.ResultBean resultBean = datas.get(position);
        viewHolder.tvCommunityUsername.setText(resultBean.getUsername());
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE +resultBean.getFigure())
                .into(viewHolder.ivCommunityFigure);

        viewHolder.tvCommunitySaying.setText(resultBean.getSaying());
        viewHolder.tvCommunityLikes.setText(resultBean.getLikes());
        viewHolder.tvCommunityComments.setText(resultBean.getComments());

        List<String> comment_list = resultBean.getComment_list();
        if(comment_list!=null&&comment_list.size()>0) {
            ArrayList<IDanmakuItem> items = new ArrayList<>();

            for(int i = 0; i <comment_list.size() ; i++) {
                items.add(new DanmakuItem(mContext,comment_list.get(i),viewHolder.danmakuView.getWidth()));
            }

            viewHolder.danmakuView.addItem(items,true);
            viewHolder.danmakuView.setVisibility(View.VISIBLE);
            viewHolder.danmakuView.show();
        }else{
            viewHolder.danmakuView.hide();
            viewHolder.danmakuView.setVisibility(View.GONE);
        }

        //设置头像
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+resultBean.getAvatar()).into(viewHolder.ibNewPostAvatar);
        return convertView;
    }

    static class ViewHolder {
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
        @InjectView(R.id.danmakuView)
        DanmakuView danmakuView;
        @InjectView(R.id.tv_community_saying)
        TextView tvCommunitySaying;
        @InjectView(R.id.tv_community_likes)
        TextView tvCommunityLikes;
        @InjectView(R.id.tv_community_comments)
        TextView tvCommunityComments;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
