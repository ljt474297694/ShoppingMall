package com.atguigu.sqlitedemo.bean;

/**
 * Created by 李金桐 on 2017/2/27.
 * QQ: 474297694
 * 功能: Student Bean
 */

public class Student {

    private int  _id;

    private String name;

    private String hobby;

    public Student() {
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Student(int _id, String name) {
        this._id = _id;
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
