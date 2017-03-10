package com.lijintong.android.utils;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


/**
 * Created by 李金桐 on 2017/3/3.
 * QQ: 474297694
 * 功能: OkHttpUtils Get请求封装
 */

public class NetUtils {

    private static Context mContext;

    private NetUtils() {
    }

    static class Tool {
        private static NetUtils netUtils = new NetUtils();
    }

    public static NetUtils getInstance(Context context) {
       mContext = context;
        return Tool.netUtils;
    }

    /**
     * 传入的url和字节码文件 使用fastjson解析 通过接口回调bean对象
     *
     * @param url      GET请求的url
     * @param clazz    需要转换的bean的Class
     * @param listener 返回结果的接口 需要声明类泛型和clazz为同一类型
     *                 注:接口的泛型必须和clazz为统一类型 否则会抛出类型转换异常
     */
    public void okhttpUtilsGet(final String url, final Class clazz, final responseBean listener) {

        OkHttpUtils.get().id(100).url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.onError(call, e, id);
            }

            @Override
            public void onResponse(String response, int id) {
                if (TextUtils.isEmpty(response)) throw new RuntimeException("网络请求结果为空 请检查url");
                CacheUtils.putString(mContext,url,response);
                listener.onResponse(JSON.parseObject(response, clazz));
            }
        });
    }

    /**
     * 传入的url和字节码文件 使用fastjson解析 通过接口回调bean对象
     *
     * @param id       http :100 https :101
     * @param url      GET请求的url
     * @param clazz    需要转换的bean的Class
     * @param listener 返回结果的接口 需要声明类泛型和clazz为同一类型
     *                 注:接口的泛型必须和clazz为统一类型 否则会抛出类型转换异常
     */
    public void okhttpUtilsGet(final String url, final int id, final Class clazz, final responseBean listener) {
        OkHttpUtils.get().id(id).url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.onError(call, e, id);
            }

            @Override
            public void onResponse(String response, int id) {
                if (TextUtils.isEmpty(response)) throw new RuntimeException("网络请求结果为空 请检查url");
                CacheUtils.putString(mContext,url,response);
                listener.onResponse(JSON.parseObject(response, clazz));
            }
        });
    }


    public interface responseBean<Bean> {
        void onResponse(Bean bean);

        void onError(Call call, Exception e, int id);
    }
}
