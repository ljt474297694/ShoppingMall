package com.atguigu.shoppingmall.utils;

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

    static class Tool {
        private static NetUtils netUtils = new NetUtils();
    }

    public static NetUtils getInstance() {
        return Tool.netUtils;
    }

    /**
     * 传入的url和字节码文件 使用fastjson解析 通过接口回调bean对象
     *
     * @param url   GET请求的url
     * @param clazz 需要转换的bean的Class
     * @param r     返回bean对象的接口
     */
    public void okhttpUtilsGet(String url, final Class clazz, final responseBean r) {
        r.getClass();
        OkHttpUtils.get().id(100).url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                r.onError(call, e, id);
            }

            @Override
            public void onResponse(String response, int id) {
                r.onResponse(JSON.parseObject(response, clazz));
            }
        });
    }


    public interface responseBean<Bean> {
        void onResponse(Bean bean);

        void onError(Call call, Exception e, int id);
    }
}
