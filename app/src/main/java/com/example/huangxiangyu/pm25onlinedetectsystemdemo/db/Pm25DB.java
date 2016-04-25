package com.example.huangxiangyu.pm25onlinedetectsystemdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.huangxiangyu.pm25onlinedetectsystemdemo.model.City;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.model.County;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.model.Node;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.model.Province;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.model.User;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.model.WeatherData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangxiangyu on 16/4/15.
 * In Pm25OnlineDetectSystemDemo
 */
public class Pm25DB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "pm25";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static Pm25DB pm25DB;

    private SQLiteDatabase db;

    /**
     * 私有化构造方法
     */
    private Pm25DB(Context context) {
        Pm25OpenHelper dbHelper = new Pm25OpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取Pm25DB的实例
     */
    public synchronized static Pm25DB getInstance(Context context) {
        if (pm25DB == null) {
            pm25DB = new Pm25DB(context);
        }
        return pm25DB;
    }

    /**
     * 将Province实例存储到数据库
     */
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }

    /**
     * 从数据库读取全国所有的省份信息
     */
    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将City实例存储到数据库
     */
    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City", null, values);
        }
    }

    /**
     * 从数据库读取某省下所有的城市信息
     */
    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?", new String[] {String.valueOf(provinceId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        } return list;
    }

    /**
     * 将County实例存储到数据库
     */
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("lat", county.getLat());
            values.put("lon", county.getLon());
            values.put("city_id", county.getCityId());
            db.insert("County", null, values);
        }
    }

    /**
     * 从数据库读取某城市下所有的县信息
     */
    public List<County> loadCounties(int cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", null, "city_id = ?", new String[] {String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setLat(cursor.getString(cursor.getColumnIndex("lat")));
                county.setLon(cursor.getString(cursor.getColumnIndex("lon")));
                county.setCityId(cityId);
                list.add(county);
            } while (cursor.moveToNext());
        } return list;
    }

    /**
     * 将Node实例存储到数据库
     */
    public void saveNode(Node node) {
        if (node != null) {
            ContentValues values = new ContentValues();
            values.put("node_name", node.getNodeName());
            values.put("node_code", node.getNodeCode());
            values.put("city_id", node.getCityId());
            db.insert("Node", null, values);
        }
    }

    /**
     * 从数据库读取某城市下所有的节点信息
     */
    public List<Node> loadNodes(int cityId) {
        List<Node> list = new ArrayList<Node>();
        Cursor cursor = db.query("Node", null, "city_id = ?" ,new String[] {String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Node node = new Node();
                node.setId(cursor.getInt(cursor.getColumnIndex("id")));
                node.setNodeName(cursor.getString(cursor.getColumnIndex("node_name")));
                node.setNodeCode(cursor.getString(cursor.getColumnIndex("node_code")));
                node.setCityId(cityId);
                list.add(node);
            } while (cursor.moveToNext());
        } return list;
    }

    /**
     * 将用户信息写入数据库
     */
    public void saveUser(User user) {
        if (user != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("user_name", user.getUserName());
            contentValues.put("user_password", user.getPassword());
            contentValues.put("email", user.getEmail());
            contentValues.put("alarm", user.getAlarm());
            db.insert("User", null, contentValues);
        }
    }
    /**
     * 从数据库读取用户名吻合的用户信息
     */
    public List<User> loadUsers(String userName) {
        List<User> list = new ArrayList<User>();
        Cursor cursor = db.query("User", null, "user_name = ?", new String[] {String.valueOf(userName)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setUserName(cursor.getString(cursor.getColumnIndex("user_name")));
                user.setPassword(cursor.getString(cursor.getColumnIndex("user_password")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                user.setAlarm(cursor.getInt(cursor.getColumnIndex("alarm")));
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将天气信息写入数据库
     */
    public void saveWeatherData(WeatherData weatherData) {
        if (weatherData != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("city_id", weatherData.getCityId());
            contentValues.put("city_name", weatherData.getCityName());
            contentValues.put("update", weatherData.getUpdate());
            contentValues.put("aqi", weatherData.getAqi());
            contentValues.put("pm25", weatherData.getPm25());
            contentValues.put("pm10", weatherData.getPm10());
            contentValues.put("so2", weatherData.getSo2());
            contentValues.put("no2", weatherData.getNo2());
            contentValues.put("co", weatherData.getCo());
            contentValues.put("o3", weatherData.getO3());
            contentValues.put("qlty", weatherData.getO3());
            contentValues.put("tmp", weatherData.getTmp());
            contentValues.put("hum", weatherData.getHum());
            contentValues.put("code", weatherData.getCode());
            db.insert("WeatherData", null, contentValues);
        }
    }
    /**
     * 从数据库读取城市名吻合的天气信息
     */
    public List<WeatherData> loadWeatherData(String cityName) {
        List<WeatherData> list = new ArrayList<WeatherData>();
        Cursor cursor = db.query("WeatherData", null, "city_name = ?", new String[] {String.valueOf(cityName)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                WeatherData weatherData = new WeatherData();
                weatherData.setId(cursor.getInt(cursor.getColumnIndex("id")));
                weatherData.setCityId(cursor.getString(cursor.getColumnIndex("city_id")));
                weatherData.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                weatherData.setUpdate(cursor.getString(cursor.getColumnIndex("update")));
                weatherData.setAqi(cursor.getInt(cursor.getColumnIndex("aqi")));
                weatherData.setPm25(cursor.getDouble(cursor.getColumnIndex("pm25")));
                weatherData.setPm10(cursor.getDouble(cursor.getColumnIndex("pm10")));
                weatherData.setSo2(cursor.getDouble(cursor.getColumnIndex("so2")));
                weatherData.setNo2(cursor.getDouble(cursor.getColumnIndex("no2")));
                weatherData.setCo(cursor.getDouble(cursor.getColumnIndex("co")));
                weatherData.setO3(cursor.getDouble(cursor.getColumnIndex("o3")));
                weatherData.setQlty(cursor.getString(cursor.getColumnIndex("qlty")));
                weatherData.setTmp(cursor.getDouble(cursor.getColumnIndex("tmp")));
                weatherData.setHum(cursor.getDouble(cursor.getColumnIndex("hum")));
                weatherData.setCode(cursor.getInt(cursor.getColumnIndex("code")));
            } while (cursor.moveToNext());
        }
        return list;
    }

}
