package com.atguigu.shoppingmall.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.utils.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李金桐 on 2017/2/27.
 * QQ: 474297694
 * 功能: xxxx
 */

public class CartStorage {

    public static final String JSON_CART = "json_cart";

    private static Context mContext;

    private final SparseArray<GoodsBean> sparseArray;


    private CartStorage() {
        sparseArray = new SparseArray();
        listToSparseArray();
    }

    private static class CartStorageTool {
        private static final CartStorage cartStorage = new CartStorage();
    }

    public static final CartStorage getInstance(Context mContext) {
        if (CartStorage.mContext == null)
            CartStorage.mContext = mContext;
        return CartStorageTool.cartStorage;
    }

    /**
     * List 转换 SparseArray
     */
    private void listToSparseArray() {
        List<GoodsBean> beanList = getAllData();
        for (int i = 0; i < beanList.size(); i++) {
            sparseArray.put(Integer.parseInt(beanList.get(i).getProduct_id()), beanList.get(i));
        }
    }

    public void addData(GoodsBean goodsBean) {

        GoodsBean tempGoodsBean = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if (tempGoodsBean != null) {
            tempGoodsBean.setNumber(tempGoodsBean.getNumber() + goodsBean.getNumber());
        } else {
            tempGoodsBean = goodsBean;
        }
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), tempGoodsBean);

        //2.保持到本地
        saveLocal();
    }

    public void deleteData(GoodsBean goodsBean) {
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
        //2.保持到本地
        saveLocal();
    }

    public void updataData(GoodsBean goodsBean) {
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        //2.保持到本地
        saveLocal();
    }

    private void saveLocal() {
        //1.把sparseArray转成List
        List<GoodsBean> goodsBeanList = sparseArrayToList();

        String json = new Gson().toJson(goodsBeanList);

        CacheUtils.setString(mContext, JSON_CART, json);

    }

    private List<GoodsBean> sparseArrayToList() {
        List<GoodsBean> list = new ArrayList<>();

        for (int i = 0; i < sparseArray.size(); i++) {
            list.add(sparseArray.valueAt(i));
        }
        return list;
    }

    /**
     * 得到所有数据
     *
     * @return
     */
    public  List<GoodsBean> getAllData() {
        return getLocalData();
    }

    /**
     * 得到本地缓存的数据
     *
     * @return
     */
    private List<GoodsBean> getLocalData() {
        String json = CacheUtils.getString(mContext, JSON_CART);

        if (TextUtils.isEmpty(json)) {
            return new ArrayList<GoodsBean>();//如果为空 返回0 size 数组
        }
        return new Gson().fromJson(json, new TypeToken<List<GoodsBean>>() {
        }.getType());
    }


}
