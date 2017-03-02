package com.atguigu.interfacedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 李金桐 on 2017/3/1.
 * QQ: 474297694
 * 功能: xxxx
 */

public class Utils {


    //内部类单例
    private Utils() {}

    public static Utils getInstance() {
        return UtilsTool.utils;
    }

   private static class UtilsTool {
        private static Utils utils = new Utils();
    }



    public void loadImage(final String url, final RequestBitmap request) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection) new URL(url).openConnection();
                    //设置超时事件
                    connection.setReadTimeout(10000);
                    connection.setConnectTimeout(10000);
                    //设置请求方式
                    connection.setRequestMethod("GET");
                    connection.connect();
                    //当成功时 把inputStream通过BitmapFactory转换成Bitmap
                    //并回调传入的接口方法将Bitmap对象传递过去
                    if (connection.getResponseCode() == 200) {
                        InputStream inputStream = connection.getInputStream();
                        if (request != null) {
                            request.onSuccess(BitmapFactory.decodeStream(inputStream));
                        }
                    }
                    connection.disconnect();
                } catch ( IOException e) {
                    connection.disconnect();
                    if (request != null) {
                        request.onError(e.getMessage());
                    }
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 请求图片的接口定义
     */
    public interface RequestBitmap {
        //当成功的时候回调一个Bitmap对象
        void onSuccess(Bitmap bitmap);
        //当失败的时候回调失败的信息
        void onError(String error);
    }

}
