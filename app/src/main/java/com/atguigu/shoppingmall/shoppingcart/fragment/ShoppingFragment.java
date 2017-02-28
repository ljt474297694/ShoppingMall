package com.atguigu.shoppingmall.shoppingcart.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.shoppingcart.adapter.ShoppingCartAdapter;
import com.atguigu.shoppingmall.shoppingcart.utils.CartStorage;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 李金桐 on 2017/2/22.
 * QQ: 474297694
 * 功能: 购物车Fragment
 */
public class ShoppingFragment extends BaseFragment {


    @InjectView(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @InjectView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @InjectView(R.id.btn_check_out)
    Button btnCheckOut;
    @InjectView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @InjectView(R.id.checkbox_delete_all)
    CheckBox checkboxDeleteAll;
    @InjectView(R.id.btn_delete)
    Button btnDelete;
    @InjectView(R.id.btn_collection)
    Button btnCollection;
    @InjectView(R.id.ll_delete)
    LinearLayout llDelete;
    @InjectView(R.id.iv_empty)
    ImageView ivEmpty;
    @InjectView(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @InjectView(R.id.ll_empty_shopcart)
    LinearLayout llEmptyShopcart;
    private List<GoodsBean> datas;
    private ShoppingCartAdapter adapter;
    private boolean isEdit = true;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);
        ButterKnife.inject(this, view);
        tvShopcartEdit.setText("编辑");
        isEdit = true;
        //编辑页面
        llCheckAll.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "购物车数据被初始化了");
        showData();
    }

    private void showData() {
        datas = CartStorage.getInstance(mContext).getAllData();
        if (datas != null && datas.size() > 0) {

            llEmptyShopcart.setVisibility(View.GONE);
            adapter = new ShoppingCartAdapter(mContext, datas, checkboxAll, tvShopcartTotal, checkboxDeleteAll);
            recyclerview.setAdapter(adapter);

            recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            adapter.setOnItemClickListener(new ShoppingCartAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(View view, int position) {
                    //得到bean对象
                    GoodsBean goodsBean = adapter.datas.get(position);
                    //选择状态取反
                    goodsBean.setChecked(!goodsBean.isChecked());
                    //刷新此条数据
                    adapter.notifyItemChanged(position);
                    //刷新价格
                    adapter.showTotalPrice();

                    //3.校验是否全选
                    adapter.checkAll();

                }
            });
            adapter.checkAll();
        } else {
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }

    }

    @OnClick({R.id.tv_shopcart_edit, R.id.checkbox_all, R.id.btn_check_out, R.id.checkbox_delete_all, R.id.btn_delete, R.id.btn_collection, R.id.tv_empty_cart_tobuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
//                Toast.makeText(mContext, "编辑", Toast.LENGTH_SHORT).show();
                //切换状态 一个方法搞定
                showOrHideDelete();
                break;
            case R.id.checkbox_all:
//                Toast.makeText(mContext, "全选", Toast.LENGTH_SHORT).show();
                //全选和反全选
                adapter.checkAll_none(checkboxAll.isChecked());
                //显示总价格
                adapter.showTotalPrice();
                break;
            case R.id.btn_check_out:
                Toast.makeText(mContext, "结算", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_delete_all:
                Toast.makeText(mContext, "删除全选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                Toast.makeText(mContext, "删除按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_collection:
                Toast.makeText(mContext, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_empty_cart_tobuy:
                Toast.makeText(mContext, "去逛逛", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showOrHideDelete() {
        isEdit = !isEdit;
        //如果是编辑状态 就显示编辑 隐藏删除 文本改变 单选框改变 反之同理
        llDelete.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        llCheckAll.setVisibility(isEdit ? View.VISIBLE : View.GONE);
        tvShopcartEdit.setText(isEdit ? "编辑" : "完成");

        if (adapter != null) {
            adapter.checkAll_none(isEdit);
            adapter.checkAll();
            adapter.showTotalPrice();
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            showData();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
