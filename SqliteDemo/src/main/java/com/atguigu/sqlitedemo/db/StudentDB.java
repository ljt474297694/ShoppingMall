package com.atguigu.sqlitedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.atguigu.sqlitedemo.TableConstant;

/**
 * Created by 李金桐 on 2017/2/27.
 * QQ: 474297694
 * 功能: StudentDB
 */

public class StudentDB extends SQLiteOpenHelper {

    public StudentDB(Context context, int version) {
        super(context, "student.db", null, version);
    }


    /**
     *  常用 基本语句
     *  创建表 create table 表名(字段1 类型,字段2 类型,字段3 类型);
     *  删除表 drop table 表名;
     *
     *  增   insert into 表名(字段1,字段2,字段3) values(值1,值2,值3);
     *  删   delete from 表名 where=条件;
     *  改   update 表名 set 字段=值 where=条件;
     *  查询 select * from 表名;
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableConstant.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            //把旧表的名修改为 临时名" temp" + TableConstant.TABLE_NAME
            db.execSQL("alter table " + TableConstant.TABLE_NAME + " rename to "+" temp" + TableConstant.TABLE_NAME);
            //创建新表 比旧表多1个字段
            db.execSQL(TableConstant.UPDATA_CREATE_TABLE);
            //把旧表的数据导入到新表 数据字段顺序对应  不存在的字段""表示空 此处为"更新成功"
            db.execSQL("insert into " + TableConstant.TABLE_NAME + " select "
                    + TableConstant.FIELD_ID + "," + TableConstant.FIELD_NAME + ",\"更新成功\" from" + " temp" + TableConstant.TABLE_NAME);
            //降旧表删除
            db.execSQL("drop table " + " temp" + TableConstant.TABLE_NAME);
            Log.e("TAG", "StudentDB onUpgrade()" + "更新成功");
        }
    }
}
