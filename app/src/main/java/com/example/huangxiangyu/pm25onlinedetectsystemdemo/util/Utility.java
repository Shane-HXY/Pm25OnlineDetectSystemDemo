package com.example.huangxiangyu.pm25onlinedetectsystemdemo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.example.huangxiangyu.pm25onlinedetectsystemdemo.db.Pm25DB;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.model.City;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.model.County;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.model.Province;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by huangxiangyu on 16/4/15.
 * In Pm25OnlineDetectSystemDemo
 */
public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(Pm25DB pm25DB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
//                    将解析出来的数据存储到Province表
                    pm25DB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCitiesResponse(Pm25DB pm25DB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
//                    将解析出来的数据存储到City表
                    pm25DB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountiesResponse(Pm25DB pm25DB, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
//                    将解析出来的数据存储到County表
                    pm25DB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析服务器返回的JSON数据,并将解析出的数据存储到本地
     */
    public static void handlePm25Response(Context context,String response) {
        try {
//            JSONObject jsonObject = new JSONObject(response);
//            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather data service 3.0");
//            JSONObject data = jsonArray.getJSONObject(0);
//            JSONObject basic = data.getJSONObject("basic");
//            String cityName = basic.getString("city");
//            String loc_update = basic.getJSONObject("update").getString("loc");
//            JSONObject AQI = data.getJSONObject("aqi");
//            String aqi = AQI.getJSONObject("city").getString("aqi");
//            String co = AQI.getJSONObject("city").getString("co");
//            String no2 = AQI.getJSONObject("city").getString("no2");
//            String o3 = AQI.getJSONObject("city").getString("o3");
//            String pm10 = AQI.getJSONObject("city").getString("pm10");
//            String pm25 = AQI.getJSONObject("city").getString("pm25");
//            String quality = AQI.getJSONObject("city").getString("qlty");
//            String so2 = AQI.getJSONObject("city").getString("so2");
//            JSONObject now = data.getJSONObject("now");
//            String tmp = now.getString("tmp");
//            String hum = now.getString("hum");
            //test
            String cityName = "北京";
            String loc_update = "2016-04-17 22:00";
            String aqi = "68";
            String co = "0";
            String no2 = "26";
            String o3 = "69";
            String pm10 = "68";
            String pm25 = "36";
            String quality = "良";
            String so2 = "2";
            String tmp = "15";
            String hum = "25";
            //
            savePm25Info(context, cityName, loc_update, aqi, co, no2, o3, pm10, pm25, quality, so2, tmp, hum);
//            savePm25Info2(context, cityName, loc_update, tmp, hum);
            Log.i("Tag", "ffffffxxxxxxx");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Tag", "44444444rrrrrrrrr");
        }
    }

    private static void savePm25Info2(Context context, String cityName, String loc_update, String tmp, String hum) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("city_selected", true);
        editor.putString("city_name", cityName);
        editor.putString("loc_update", loc_update);
        editor.putString("tmp", tmp);
        editor.putString("hum", hum);
        editor.commit();
    }

    /**
     * 将服务器返回的所有空气信息
     */
    private static void savePm25Info(Context context, String cityName, String loc_update, String aqi, String co, String no2, String o3, String pm10, String pm25, String quality, String so2, String tmp, String hum) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("city_selected", true);
        editor.putString("city_name", cityName);
        editor.putString("loc_update", loc_update);
        editor.putString("aqi", aqi);
        editor.putString("co", co);
        editor.putString("no2", no2);
        editor.putString("o3", o3);
        editor.putString("pm10", pm10);
        editor.putString("pm25", pm25);
        editor.putString("quality", quality);
        editor.putString("so2", so2);
        editor.putString("tmp", tmp);
        editor.putString("hum", hum);
        Log.i("Tag", "0000000XXXXXXXX");
        editor.commit();
    }


}