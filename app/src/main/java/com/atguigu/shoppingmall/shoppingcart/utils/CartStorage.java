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
    private final SparseArray sparseArray;

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
        for(int i = 0; i <beanList.size() ; i++) {
            sparseArray.put(Integer.parseInt(beanList.get(i).getProduct_id()),beanList.get(i));
        }
    }

    /**
     * 得到所有数据
     *
     * @return
     */
    private List<GoodsBean> getAllData() {
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
        return new Gson().fromJson(json, new TypeToken<List<GoodsBean>>(){}.getType());
    }





}
