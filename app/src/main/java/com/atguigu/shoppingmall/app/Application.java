package com.atguigu.shoppingmall.app;


import android.database.sqlite.SQLiteDatabase;

import com.atguigu.shoppingmall.db.DaoMaster;
import com.atguigu.shoppingmall.db.DaoSession;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by 李金桐 on 2017/3/7.
 * QQ: 474297694
 * 功能: xxxx
 */

public class Application extends android.app.Application {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static Application instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        ZXingLibrary.initDisplayOpinion(this);
        setDatabase();
    }

    public static Application getInstances() {
        return instances;
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "user-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}