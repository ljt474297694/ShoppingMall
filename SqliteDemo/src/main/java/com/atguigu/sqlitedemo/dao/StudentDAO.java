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

    public  StudentDB studentDB;
    private  int version;

    public StudentDAO(Context mContext, int version) {
        this.version = version;
        studentDB = new StudentDB(mContext,  version);
    }

    public void add(Student student) {
        if (student == null) return; //校验

        SQLiteDatabase database = studentDB.getReadableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(TableConstant.FIELD_NAME, student.getName());
        database.replace(TableConstant.TABLE_NAME, null, initialValues);
    }

    public int updata(Student student){
        if (student == null) return 0;
        SQLiteDatabase database = studentDB.getReadableDatabase();

        ContentValues contenxtValues= new ContentValues();
        contenxtValues.put(TableConstant.FIELD_NAME,student.getName());
        return database.update(TableConstant.TABLE_NAME, contenxtValues, "_id=?", new String[]{student.get_id() + ""});


    }
    public int delete(Student student){
        if (student == null) return 0;

        SQLiteDatabase database = studentDB.getReadableDatabase();
        return database.delete(TableConstant.TABLE_NAME, "_id=?", new String[]{student.get_id() + ""});
    }
    public ArrayList<Student> getAll(){
        SQLiteDatabase database = studentDB.getReadableDatabase();

        Cursor cursor = database.rawQuery("select * from " + TableConstant.TABLE_NAME, null);

        ArrayList<Student> students = new ArrayList<>();
        Student student;
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(TableConstant.FIELD_NAME));
            int id = cursor.getInt(cursor.getColumnIndex(TableConstant.FIELD_ID));
            student = new Student(id,name);
            if(version==2) {
                student.setHobby(cursor.getString(cursor.getColumnIndex(TableConstant.FIELD_HOBBY)));
            }
            students.add(student);
        }
        cursor.close();
        return students;
    }
    public void deleteTable(){
        SQLiteDatabase database = studentDB.getReadableDatabase();
        database.execSQL("drop table " + TableConstant.TABLE_NAME);
        database.execSQL(TableConstant.CREATE_TABLE);
    }
}
