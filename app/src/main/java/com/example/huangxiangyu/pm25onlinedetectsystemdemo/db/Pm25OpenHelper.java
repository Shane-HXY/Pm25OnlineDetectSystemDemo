package com.example.huangxiangyu.pm25onlinedetectsystemdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huangxiangyu on 16/4/15.
 * In Pm25OnlineDetectSystemDemo
 */
public class Pm25OpenHelper extends SQLiteOpenHelper {
    //    建数据表
    /**
     * Province表建表语句
     */
    public static final String CREATE_PROVINCE = "create table Province ("
            + "id integer primary key autoincrement, "
            + "province_name text, "
            + "province_code text)";

    /**
     * City表建表语句
     */
    public static final String CREATE_CITY = "create table City ("
            + "id integer primary key autoincrement, "
            + "city_name text, "
            + "city_code text, "
            + "province_id integer)";

    /**
     * County表建表语句
     */
    public static final String CREATE_COUNTY = "create table County ("
            + "id integer primary key autoincrement, "
            + "county_name text, "
            + "county_code text, "
            + "lat text, "
            + "lon text, "
            + "city_id integer, "
            + "user_name text)";

    /**
     * Node表建表语句
     */
    public static final String CREATE_NODE = "create table Node ("
            + "id integer primary key autoincrement, "
            + "node_name text, "
            + "node_code text, "
            + "city_id integer)";

    /**
     * User表建表语句
     */
    public static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement, "
            + "user_name text, "
            + "email text, "
            + "alarm integer)";


    /**
     * WeatherData表建表语句
     */
    public static final String CREATE_WEATHERDATA = "create table WeatherData ("
            + "id integer primary key autoincrement, "
            + "city_id text, "
            + "city_name text, "
            + "update text, "
            + "loc text, "
            + "aqi integer, "
            + "pm25 real, "
            + "pm10 real"
            + "so2 real, "
            + "no2 real, "
            + "co real, "
            + "o3 real, "
            + "qlty real, "
            + "tmp real, "
            + "hum real, "
            + "code text)";

    public Pm25OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PROVINCE);    //创建Province表
        sqLiteDatabase.execSQL(CREATE_CITY);        //创建City表
        sqLiteDatabase.execSQL(CREATE_COUNTY);      //创建County表
        sqLiteDatabase.execSQL(CREATE_NODE);        //创建Node表
        sqLiteDatabase.execSQL(CREATE_USER);        //创建User表
        sqLiteDatabase.execSQL(CREATE_WEATHERDATA); //创建WeatherData表
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
