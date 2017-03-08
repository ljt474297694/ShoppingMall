package com.atguigu.shoppingmall.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.app.GoodsInfoActivity;
import com.atguigu.shoppingmall.home.adapter.ExpandableListViewAdapter;
import com.atguigu.shoppingmall.home.adapter.GoodsListAdapter;
import com.atguigu.shoppingmall.home.adapter.HomeAdapter;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.home.bean.TypeListBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.atguigu.shoppingmall.utils.DensityUtil;
import com.atguigu.shoppingmall.utils.NetUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.atguigu.shoppingmall.R.id.rl_price_nolimit;
import static com.atguigu.shoppingmall.R.id.tv_drawer_price;

public class GoodsListActivity extends AppCompatActivity {


    @InjectView(R.id.ib_goods_list_back)
    ImageButton ibGoodsListBack;
    @InjectView(R.id.tv_goods_list_search)
    TextView tvGoodsListSearch;
    @InjectView(R.id.ib_goods_list_home)
    ImageButton ibGoodsListHome;
    @InjectView(R.id.tv_goods_list_sort)
    TextView tvGoodsListSort;
    @InjectView(R.id.tv_goods_list_price)
    TextView tvGoodsListPrice;
    @InjectView(R.id.iv_goods_list_arrow)
    ImageView ivGoodsListArrow;
    @InjectView(R.id.ll_goods_list_price)
    LinearLayout llGoodsListPrice;
    @InjectView(R.id.tv_goods_list_select)
    TextView tvGoodsListSelect;
    @InjectView(R.id.ll_goods_list_head)
    LinearLayout llGoodsListHead;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.ib_drawer_layout_back)
    ImageButton ibDrawerLayoutBack;
    @InjectView(R.id.tv_ib_drawer_layout_title)
    TextView tvIbDrawerLayoutTitle;
    @InjectView(R.id.ib_drawer_layout_confirm)
    TextView ibDrawerLayoutConfirm;
    @InjectView(R.id.rb_select_hot)
    RadioButton rbSelectHot;
    @InjectView(R.id.rb_select_new)
    RadioButton rbSelectNew;
    @InjectView(R.id.rg_select)
    RadioGroup rgSelect;
    @InjectView(tv_drawer_price)
    TextView tvDrawerPrice;

    @InjectView(R.id.tv_drawer_recommend)
    TextView tvDrawerRecommend;
    @InjectView(R.id.rl_select_recommend_theme)
    RelativeLayout rlSelectRecommendTheme;
    @InjectView(R.id.tv_drawer_type)
    TextView tvDrawerType;
    @InjectView(R.id.rl_select_type)
    RelativeLayout rlSelectType;
    @InjectView(R.id.btn_select_all)
    Button btnSelectAll;
    @InjectView(R.id.ll_select_root)
    LinearLayout llSelectRoot;
    @InjectView(R.id.btn_drawer_layout_cancel)
    Button btnDrawerLayoutCancel;
    @InjectView(R.id.btn_drawer_layout_confirm)
    Button btnDrawerLayoutConfirm;
    @InjectView(R.id.iv_price_no_limit)
    CheckBox ivPriceNoLimit;
    @InjectView(rl_price_nolimit)
    RelativeLayout rlPriceNolimit;
    @InjectView(R.id.iv_price_0_15)
    CheckBox ivPrice015;
    @InjectView(R.id.rl_price_0_15)
    RelativeLayout rlPrice015;
    @InjectView(R.id.iv_price_15_30)
    CheckBox ivPrice1530;
    @InjectView(R.id.rl_price_15_30)
    RelativeLayout rlPrice1530;
    @InjectView(R.id.iv_price_30_50)
    CheckBox ivPrice3050;
    @InjectView(R.id.rl_price_30_50)
    RelativeLayout rlPrice3050;
    @InjectView(R.id.iv_price_50_70)
    CheckBox ivPrice5070;
    @InjectView(R.id.rl_price_50_70)
    RelativeLayout rlPrice5070;
    @InjectView(R.id.iv_price_70_100)
    CheckBox ivPrice70100;
    @InjectView(R.id.rl_price_70_100)
    RelativeLayout rlPrice70100;
    @InjectView(R.id.iv_price_100)
    CheckBox ivPrice100;
    @InjectView(R.id.rl_price_100)
    RelativeLayout rlPrice100;
    @InjectView(R.id.et_price_start)
    EditText etPriceStart;
    @InjectView(R.id.v_price_line)
    View vPriceLine;
    @InjectView(R.id.et_price_end)
    EditText etPriceEnd;
    @InjectView(R.id.rl_select_price)
    RelativeLayout rlSelectPrice;
    @InjectView(R.id.ll_price_root)
    LinearLayout llPriceRoot;
    @InjectView(R.id.btn_drawer_theme_cancel)
    Button btnDrawerThemeCancel;
    @InjectView(R.id.btn_drawer_theme_confirm)
    Button btnDrawerThemeConfirm;
    @InjectView(R.id.iv_theme_all)
    CheckBox ivThemeAll;
    @InjectView(R.id.rl_theme_all)
    RelativeLayout rlThemeAll;
    @InjectView(R.id.iv_theme_note)
    CheckBox ivThemeNote;
    @InjectView(R.id.rl_theme_note)
    RelativeLayout rlThemeNote;
    @InjectView(R.id.iv_theme_funko)
    CheckBox ivThemeFunko;
    @InjectView(R.id.rl_theme_funko)
    RelativeLayout rlThemeFunko;
    @InjectView(R.id.iv_theme_gsc)
    CheckBox ivThemeGsc;
    @InjectView(R.id.rl_theme_gsc)
    RelativeLayout rlThemeGsc;
    @InjectView(R.id.iv_theme_origin)
    CheckBox ivThemeOrigin;
    @InjectView(R.id.rl_theme_origin)
    RelativeLayout rlThemeOrigin;
    @InjectView(R.id.iv_theme_sword)
    CheckBox ivThemeSword;
    @InjectView(R.id.rl_theme_sword)
    RelativeLayout rlThemeSword;
    @InjectView(R.id.iv_theme_food)
    CheckBox ivThemeFood;
    @InjectView(R.id.rl_theme_food)
    RelativeLayout rlThemeFood;
    @InjectView(R.id.iv_theme_moon)
    CheckBox ivThemeMoon;
    @InjectView(R.id.rl_theme_moon)
    RelativeLayout rlThemeMoon;
    @InjectView(R.id.iv_theme_quanzhi)
    CheckBox ivThemeQuanzhi;
    @InjectView(R.id.rl_theme_quanzhi)
    RelativeLayout rlThemeQuanzhi;
    @InjectView(R.id.iv_theme_gress)
    CheckBox ivThemeGress;
    @InjectView(R.id.rl_theme_gress)
    RelativeLayout rlThemeGress;
    @InjectView(R.id.ll_theme_root)
    LinearLayout llThemeRoot;
    @InjectView(R.id.btn_drawer_type_cancel)
    Button btnDrawerTypeCancel;
    @InjectView(R.id.btn_drawer_type_confirm)
    Button btnDrawerTypeConfirm;
    @InjectView(R.id.expandableListView)
    ExpandableListView expandableListView;
    @InjectView(R.id.ll_type_root)
    LinearLayout llTypeRoot;
    @InjectView(R.id.dl_left)
    DrawerLayout dlLeft;
    /**
     * 请求网络
     */
    private String[] urls = new String[]{
            Constants.CLOSE_STORE,
            Constants.GAME_STORE,
            Constants.COMIC_STORE,
            Constants.COSPLAY_STORE,
            Constants.GUFENG_STORE,
            Constants.STICK_STORE,
            Constants.WENJU_STORE,
            Constants.FOOD_STORE,
            Constants.SHOUSHI_STORE,
    };
    private int position;
    private GoodsListAdapter adapter;

    private boolean isDesc = true; // 默认箭头向下红
    private ArrayList<String> group;
    private ArrayList<ArrayList<String>> child;
    private ExpandableListViewAdapter expandableListViewAdapter;
    private ArrayList<CheckBox> ckeckPrice;

    private String priceText = "不限";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.inject(this);
        initView();
        getData();
        getDataFromNet();
    }

    private void initView() {
        ButterKnife.inject(this);
        testToRed(tvGoodsListSort); //综合排序高亮
        showLinearLayout(llSelectRoot); //侧滑默认显示筛选

        ckeckPrice = new ArrayList<>();
        ckeckPrice.add(ivPriceNoLimit);
        ckeckPrice.add(ivPrice015);
        ckeckPrice.add(ivPrice1530);
        ckeckPrice.add(ivPrice3050);
        ckeckPrice.add(ivPrice5070);
        ckeckPrice.add(ivPrice70100);


    }

    private void getDataFromNet() {
        NetUtils.getInstance().okhttpUtilsGet(urls[position], TypeListBean.class, new NetUtils.responseBean<TypeListBean>() {
            @Override
            public void onResponse(TypeListBean bean) {
                setAdapter(bean);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "GoodsListActivity onError()" + e.getMessage());
            }
        });
    }

    private void setAdapter(TypeListBean bean) {
        adapter = new GoodsListAdapter(this, bean.getResult());
        adapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(TypeListBean.ResultEntity.PageDataEntity data) {
                String name = data.getName();
                String cover_price = data.getCover_price();
                String figure = data.getFigure();
                String product_id = data.getProduct_id();

                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setName(name);
                goodsBean.setFigure(figure);
                goodsBean.setCover_price(cover_price);
                goodsBean.setProduct_id(product_id);

                Intent intent = new Intent(GoodsListActivity.this, GoodsInfoActivity.class);
                intent.putExtra(HomeAdapter.GOODS_BEAN, goodsBean);
                startActivity(intent);
            }
        });

        recyclerview.setAdapter(adapter);
        //设置分割线
        recyclerview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                //不是第一个的格子都设一个左边和底部的间距
                outRect.left = DensityUtil.dip2px(GoodsListActivity.this, 10);
                outRect.bottom = DensityUtil.dip2px(GoodsListActivity.this, 10);
                //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
                if (parent.getChildLayoutPosition(view) % 2 == 0) outRect.left = 0;
            }
        });

        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));


    }

    private void getData() {
        position = getIntent().getIntExtra("position", 0);
    }


    @OnClick({R.id.btn_select_all,R.id.rl_price_15_30, R.id.rl_price_0_15, R.id.rl_price_nolimit, R.id.ib_goods_list_back, R.id.tv_goods_list_search, R.id.ib_goods_list_home, R.id.tv_goods_list_sort, R.id.tv_goods_list_price, R.id.tv_goods_list_select, R.id.ib_drawer_layout_back, R.id.ib_drawer_layout_confirm, R.id.rl_select_price, R.id.rl_select_recommend_theme, R.id.rl_select_type, R.id.btn_drawer_layout_cancel, R.id.btn_drawer_layout_confirm, R.id.btn_drawer_theme_cancel, R.id.btn_drawer_theme_confirm, R.id.btn_drawer_type_cancel, R.id.btn_drawer_type_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_goods_list_back:
                finish();
                break;
            case R.id.tv_goods_list_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_goods_list_home:
                finish();
                break;
            case R.id.tv_goods_list_sort:
                isDesc = true;
                ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_normal);
                testToRed(tvGoodsListSort);
                break;
            case R.id.tv_goods_list_price:
                if (isDesc) {
                    // 箭头向下红
                    ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_desc);
                } else {
                    // 箭头向上红
                    ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_asc);
                }
                isDesc = !isDesc; //取反下次点击 箭头向上红
                testToRed(tvGoodsListPrice);
                break;
            case R.id.tv_goods_list_select:
                isDesc = true;
                ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_normal);
                testToRed(tvGoodsListSelect);
                dlLeft.openDrawer(Gravity.RIGHT);
                break;

            case R.id.ib_drawer_layout_back:
                dlLeft.closeDrawers();   //关闭DrawLayout
                break;
            case R.id.ib_drawer_layout_confirm:
                dlLeft.closeDrawers();
                position = 2;
                getDataFromNet();
                Toast.makeText(GoodsListActivity.this, tvDrawerPrice.getText().toString()+"--"+tvDrawerType.getText().toString()+"--"+tvDrawerRecommend.getText().toString(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "筛选-确定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_select_price://显示-价格 llPriceRoot
                showLinearLayout(llPriceRoot);
                break;
            case R.id.rl_select_recommend_theme://主题 llThemeRoot
                showLinearLayout(llThemeRoot);
                break;
            case R.id.rl_select_type://类别 llTypeRoot
                showLinearLayout(llTypeRoot);
                initExpandableListView();
                break;
            case R.id.btn_drawer_layout_cancel://取消 回到llSelectRoot
                showLinearLayout(llSelectRoot);
                break;
            case R.id.btn_drawer_layout_confirm:
                Toast.makeText(this, "价格-确定", Toast.LENGTH_SHORT).show();
                if(ivPrice1530.isChecked()) {
                    priceText = "15-30";
                }
                if(ivPriceNoLimit.isChecked()) {
                    priceText = "不限";
                }
                if(ivPrice015.isChecked()) {
                    priceText = "0-15";
                }
                tvDrawerPrice.setText(priceText);
                showLinearLayout(llSelectRoot);
                break;
            case R.id.btn_drawer_theme_cancel://取消 回到llSelectRoot
                showLinearLayout(llSelectRoot);
                break;
            case R.id.btn_drawer_theme_confirm:
                Toast.makeText(this, "主题-确定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_drawer_type_cancel://取消 回到llSelectRoot
                showLinearLayout(llSelectRoot);
                break;
            case R.id.btn_drawer_type_confirm:
                Toast.makeText(this, "类别-确定", Toast.LENGTH_SHORT).show();
                tvDrawerType.setText(child.get(expandableListViewAdapter.groupPosition).get(expandableListViewAdapter.childPosition));
                showLinearLayout(llSelectRoot);
                break;
            case R.id.rl_price_nolimit:
                Toast.makeText(GoodsListActivity.this, "全部", Toast.LENGTH_SHORT).show();
                ivPriceNoLimit.setChecked(!ivPriceNoLimit.isChecked());
                hiedPriceCheckBox(ivPriceNoLimit);

                break;
            case R.id.rl_price_0_15:
                Toast.makeText(GoodsListActivity.this, "0 15", Toast.LENGTH_SHORT).show();
                ivPrice015.setChecked(!ivPrice015.isChecked());
                hiedPriceCheckBox(ivPrice015);

                break;
            case R.id.rl_price_15_30:
                Toast.makeText(GoodsListActivity.this, "15 30", Toast.LENGTH_SHORT).show();
                ivPrice1530.setChecked(!ivPrice1530.isChecked());
                hiedPriceCheckBox(ivPrice1530);
                break;
            case R.id.btn_select_all:

                break;
        }
    }

    private void initExpandableListView() {
        //创建集合
        group = new ArrayList<>();
        child = new ArrayList<>();

        //添加数据
        addInfo("全部", new String[]{});
        addInfo("上衣", new String[]{"古风", "和风", "lolita", "日常"});
        addInfo("下装", new String[]{"日常", "泳衣", "汉风", "lolita", "创意T恤"});
        addInfo("外套", new String[]{"汉风", "古风", "lolita", "胖次", "南瓜裤", "日常"});

        //设置适配器
        //设置适配器
        expandableListViewAdapter = new ExpandableListViewAdapter(this, group, child);
        expandableListView.setAdapter(expandableListViewAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (child.get(groupPosition).isEmpty()) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                expandableListViewAdapter.isChildSelectable(groupPosition, childPosition);
                expandableListViewAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    //高亮传入的TextView的文字
    private void testToRed(TextView textView) {
        //设置默认
        tvGoodsListSort.setTextColor(Color.parseColor("#333538"));
        tvGoodsListPrice.setTextColor(Color.parseColor("#333538"));
        tvGoodsListSelect.setTextColor(Color.parseColor("#333538"));
        textView.setTextColor(Color.parseColor("#ed4141"));
    }

    //显示传入的LinearLayout
    private void showLinearLayout(LinearLayout l) {
        llPriceRoot.setVisibility(View.GONE);
        llThemeRoot.setVisibility(View.GONE);
        llTypeRoot.setVisibility(View.GONE);
        llSelectRoot.setVisibility(View.GONE);
        l.setVisibility(View.VISIBLE);
    }

    private void addInfo(String father, String[] datas) {
        group.add(father);//下装
        //下装--孩子的数据{日常", "泳衣", "汉风", "lolita", "创意T恤}
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < datas.length; i++) {
            list.add(datas[i]);
        }
        child.add(list);
    }

    private void hiedPriceCheckBox(CheckBox checkBox) {
        for(int i = 0; i < ckeckPrice.size(); i++) {
          if(checkBox!=ckeckPrice.get(i)) {
              ckeckPrice.get(i).setChecked(false);
          }
        }
    }
}
