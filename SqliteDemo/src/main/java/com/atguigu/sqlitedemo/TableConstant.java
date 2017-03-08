package com.atguigu.sqlitedemo;

/**
 * Created by 李金桐 on 2017/2/27.
 * QQ: 474297694
 * 功能: 定义 数据库字段和创建语句
 */

public class TableConstant {

    public static final String TABLE_NAME = "student";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_HOBBY = "hobby";


    public static final String CREATE_TABLE =
            "create table " + TABLE_NAME + " ("
                    + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + FIELD_NAME + " text)" ;

    public static final String UPDATA_CREATE_TABLE =
            "create table " + TABLE_NAME + " ("
                    + FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + FIELD_NAME + " text,"
                    + FIELD_HOBBY + " text)";

    

}
