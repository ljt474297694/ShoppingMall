package com.atguigu.shoppingmall.home.bean;

import java.io.Serializable;

/**
 * Created by 李金桐 on 2017/2/25.
 * QQ: 474297694
 * 功能: xxxx
 */

public class GoodsBean implements Serializable {

    /**
     * cover_price : 138.00
     * figure : /supplier/1478873740576.jpg
     * name : 【尚硅谷】日常 萌系小天使卫衣--白色款
     * product_id : 10659
     */

    private String cover_price;
    private String figure;
    private String name;
    private String product_id;
    private boolean isChecked = true;

    private String express = "尚硅谷";

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    /**
     * 某个商品在购物车购买的数量
     */
    private int number = 1;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                ", number=" + number +
                '}';
    }
}
