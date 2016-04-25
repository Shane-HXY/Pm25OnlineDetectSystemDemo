package com.example.huangxiangyu.pm25onlinedetectsystemdemo.model;


/**
 * Created by huangxiangyu on 16/4/18.
 * In Pm25OnlineDetectSystemDemo
 */
public class User {
    private int id;
    private String userName;
    private String password;
    private String email;
    private int alarm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }
}
