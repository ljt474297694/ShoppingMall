package com.atguigu.shoppingmall.community.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.community.bean.HotPostBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.atguigu.shoppingmall.utils.DensityUtil;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 李金桐 on 2017/3/4.
 * QQ: 474297694
 * 功能: xxxx
 */

public class HotPostListViewAdapter extends BaseAdapter {


    private final Context mContext;
    private final List<HotPostBean.ResultBean> datas;

    public HotPostListViewAdapter(Context mContext, List<HotPostBean.ResultBean> result) {
        this.datas = result;
        this.mContext = mContext;
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
            convertView = View.inflate(mContext, R.layout.item_hotpost_listview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HotPostBean.ResultBean resultBean = datas.get(position);
        //设置用户名称
        viewHolder.tvHotUsername.setText(resultBean.getUsername());

        SimpleDateFormat myFmt = new SimpleDateFormat("MM-dd HH:mm");
        //设置文本
        viewHolder.tvHotAddtime.setText(myFmt.format(Integer.parseInt(resultBean.getAdd_time())));
        //请求图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE +resultBean.getFigure()).into(viewHolder.ivHotFigure);
        //说话
        viewHolder.tvHotSaying.setText(resultBean.getSaying());
        //喜欢
        viewHolder.tvHotLikes.setText(resultBean.getLikes());
        //评论
        viewHolder.tvHotComments.setText(resultBean.getComments());
        //请求图像
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+resultBean.getAvatar()).into(viewHolder.ivNewPostAvatar);
        viewHolder.llHotPost.removeAllViews();
        //置顶
        String is_top = resultBean.getIs_top();

        if ("1".equals(is_top)) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(mContext);
            textView.setText("置顶");
            textViewLp.setMargins(DensityUtil.dip2px(mContext, 8), 0, DensityUtil.dip2px(mContext, 5), 0);
            //内部对齐
            textView.setGravity(Gravity.CENTER);
            //白色
            textView.setTextColor(Color.WHITE);

            //设置背景
            textView.setBackgroundResource(R.drawable.is_top_shape);
            //padding都是5
            textView.setPadding(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            //先把之前的移除

            //添加
            viewHolder.llHotPost.addView(textView, textViewLp);
        }
//热门
        String is_hot = resultBean.getIs_hot();
        if ("1".equals(is_hot)) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textView = new TextView(mContext);
            textViewLp.setMargins(0, 0, DensityUtil.dip2px(mContext, 5), 0);
            textView.setText("热门");
            textView.setGravity(Gravity.CENTER);
            //文字为白色
            textView.setTextColor(Color.WHITE);
            //设置padding
            textView.setPadding(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            //设置橙色北京
            textView.setBackgroundResource(R.drawable.is_hot_shape);
            viewHolder.llHotPost.addView(textView, textViewLp);
        }
//精华
        String is_essence = resultBean.getIs_essence();
        if ("1".equals(is_essence)) {
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //距离右边
            textViewLp.setMargins(0, 0, DensityUtil.dip2px(mContext, 5), 0);
            TextView textView = new TextView(mContext);
            textView.setText("精华");
            textView.setGravity(Gravity.CENTER);
            //文字白色
            textView.setTextColor(Color.WHITE);
            textView.setPadding(DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 5));
            //设置背景为亮橙色
            textView.setBackgroundResource(R.drawable.is_essence_shape);
            viewHolder.llHotPost.addView(textView, textViewLp);
        }


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_hot_username)
        TextView tvHotUsername;
        @InjectView(R.id.tv_hot_addtime)
        TextView tvHotAddtime;
        @InjectView(R.id.rl)
        RelativeLayout rl;
        @InjectView(R.id.iv_new_post_avatar)
        ImageView ivNewPostAvatar;
        @InjectView(R.id.iv_hot_figure)
        ImageView ivHotFigure;
        @InjectView(R.id.ll_hot_post)
        LinearLayout llHotPost;
        @InjectView(R.id.tv_hot_saying)
        TextView tvHotSaying;
        @InjectView(R.id.tv_hot_likes)
        TextView tvHotLikes;
        @InjectView(R.id.tv_hot_comments)
        TextView tvHotComments;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
