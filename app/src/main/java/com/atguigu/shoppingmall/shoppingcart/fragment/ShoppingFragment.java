package com.atguigu.shoppingmall.shoppingcart.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.shoppingcart.adapter.ShoppingCartAdapter;
import com.atguigu.shoppingmall.shoppingcart.utils.CartStorage;
import com.atguigu.shoppingmall.shoppingcart.utils.PayResult;
import com.atguigu.shoppingmall.shoppingcart.utils.SignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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
    private boolean isEdit;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "购物车数据被初始化了");
        showData();
    }

    private void showData() {
        isEdit = false;
        showOrHideDelete();
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
//                Toast.makeText(mContext, "结算", Toast.LENGTH_SHORT).show();
                pay();
                break;
            case R.id.checkbox_delete_all:
//                Toast.makeText(mContext, "删除全选", Toast.LENGTH_SHORT).show();
                //全选和反全选
                adapter.checkAll_none(checkboxDeleteAll.isChecked());
                //显示总价格
                adapter.showTotalPrice();
                break;
            case R.id.btn_delete:
//                Toast.makeText(mContext, "删除按钮", Toast.LENGTH_SHORT).show();
                adapter.deleteData();
                adapter.checkAll();
                showEempty();
                break;
            case R.id.btn_collection:
                Toast.makeText(mContext, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_empty_cart_tobuy:
                Toast.makeText(mContext, "去逛逛", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    /**
     *    删除与购买模式切换的方法
     */

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

    /**
     * 没有数据的时候显示
     */
    private void showEempty() {
        if(adapter.getItemCount() == 0){
            llEmptyShopcart.setVisibility(View.VISIBLE);
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
    //---------------------支付

    // 商户PID
    public static final String PARTNER = "2088911876712776";
    // 商户收款账号
    public static final String SELLER = "chenlei@atguigu.com";


    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANDl5XiZplckwpJ/\n" +
            "EVejypog8kwjqVPAWQ0tGYWG/E4g1ccF+KjlICSWlkPiJY6JTpVwQ9xbfA12HVEZ\n" +
            "6hH4k9GIIJI7+H5/dDOFdCRyJUcXGx/6cTHiK1oe3BxJ/+vtYRCmqX6FaiChXSmr\n" +
            "nero6+QtMhElWVlxXsQoll6rcQg5AgMBAAECgYBrW4tIJKcBKCo7AcTW5p2MApyj\n" +
            "lrxCB5t8kw4HuqzWNjfHtgDqQ/717tJBButISraxRGYLzJR/kpFUid3q6HaGjdcs\n" +
            "8IHPTAtTaOMVrp3MVizoJgd2JQ5ATO0loMVqAzqCftmr/UsKYzt/r/ghBQUq73cQ\n" +
            "KHPLpTq98Z+GONvxJQJBAPKup+v5NAJARPfl4s6j/j/JJ8B4E5VXm0CFjR2dCLk6\n" +
            "e15lQ1U2Sc7XJHm5gJUmxAVMlVhohDLFr+chwx0aAisCQQDcXJ6mUhB5TnpMWljD\n" +
            "Kn4CJA0bK5vQhZmOIcKrPfb/q90qzgqJTEckz1CPY+Erx/vbiiAxVNuXR8ADmtco\n" +
            "cYErAkA+1BbnUco0Nv1kDKEujGh7jRF8k9nGFTs9dhX1r70db1WXN7I58mcjZLGt\n" +
            "zefLNZEUKiKUE+L93g5PUX9ZHm2nAkEAnOxgFjTuglQ+h0z3VNRXs5KUurqNVSsu\n" +
            "tKaaCHQyBtQOxcLAghGNwEraJaKM4S6izi5IypiRX+O6e1ayzZw2zQJBAIMDTt5g\n" +
            "j1lZEBrLIxo2mRrTJKLQCLuW77CrMJtN6+79G39X9DBeao7dGP2agyVE7SYTJiV+\n" +
            "E0hkilFDq3xItew=";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQ5eV4maZXJMKSfxFXo8qaIPJMI6lTwFkNLRmFhvxOINXHBfio5SAklpZD4iWOiU6VcEPcW3wNdh1RGeoR+JPRiCCSO/h+f3QzhXQkciVHFxsf+nEx4itaHtwcSf/r7WEQpql+hWogoV0pq53q6OvkLTIRJVlZcV7EKJZeq3EIOQIDAQAB";


    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(mContext, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    /**
     * call alipay sdk pay. 调用SDK支付
     *
     */
    public void pay() {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(mContext).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
//                            finish();
                        }
                    }).show();
            return;
        }
        //生成订单信息-在客户端或者服务器
        String orderInfo = getOrderInfo("硅谷", "测试", adapter.getTotalPrice()+"");

             /**
             * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
             */
            String sign = sign(orderInfo);
            try {
                /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask((Activity) mContext);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /**
     * create the order info. 创建订单信息
     *
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     *
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

}
