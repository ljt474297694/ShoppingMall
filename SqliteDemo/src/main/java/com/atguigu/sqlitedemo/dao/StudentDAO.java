package com.atguigu.sqlitedemo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.atguigu.sqlitedemo.TableConstant;
import com.atguigu.sqlitedemo.bean.Student;
import com.atguigu.sqlitedemo.db.StudentDB;

import java.util.ArrayList;

/**
 * Created by 李金桐 on 2017/2/27.
 * QQ: 474297694
 * 功能: StudentDAO
 */

public class StudentDAO {

    public StudentDB studentDB;
    private int version;

    public StudentDAO(Context mContext, int version) {
        this.version = version;
        studentDB = new StudentDB(mContext, version);
    }

    /**
     * @param student 添加
     */
    public void add(Student student) {
        if (student == null) return; //校验

        SQLiteDatabase database = studentDB.getReadableDatabase();//开启数据库连接
        ContentValues initialValues = new ContentValues();
        initialValues.put(TableConstant.FIELD_NAME, student.getName());
        database.replace(TableConstant.TABLE_NAME, null, initialValues);
    }

    /**
     * @param student 更新数据库中某个学生
     * @return
     */
    public int updata(Student student) {
        if (student == null) return 0;
        SQLiteDatabase database = studentDB.getReadableDatabase();

        ContentValues contenxtValues = new ContentValues();
        contenxtValues.put(TableConstant.FIELD_NAME, student.getName());
        return database.update(TableConstant.TABLE_NAME, contenxtValues, "_id=?", new String[]{student.get_id() + ""});


    }

    /**
     * @param student 删除数据库中某个学生
     * @return
     */
    public int delete(Student student) {
        if (student == null) return 0;

        SQLiteDatabase database = studentDB.getReadableDatabase();
        return database.delete(TableConstant.TABLE_NAME, "_id=?", new String[]{student.get_id() + ""});
    }

    /**
     * @return 获取表中所有学生数据 封装到ArrayList<Student>返回
     */
    public ArrayList<Student> getAll() {
        SQLiteDatabase database = studentDB.getReadableDatabase();

        Cursor cursor = database.rawQuery("select * from " + TableConstant.TABLE_NAME, null);

        ArrayList<Student> students = new ArrayList<>();
        Student student;
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(TableConstant.FIELD_NAME));
            int id = cursor.getInt(cursor.getColumnIndex(TableConstant.FIELD_ID));
            student = new Student(id, name);
            if (version == 2) {
                student.setHobby(cursor.getString(cursor.getColumnIndex(TableConstant.FIELD_HOBBY)));
            }
            students.add(student);
        }
        cursor.close();
        return students;
    }
}
