package com.example.huangxiangyu.pm25onlinedetectsystemdemo.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by huangxiangyu on 16/4/19.
 * In Pm25OnlineDetectSystemDemo
 */
public class WeatherData implements Serializable {
    private int id;

    private String cityId;  //城市ID

    private String cityName;//城市名

    private String update;  //更新时间 24小时制

    private int aqi;        //空气质量指数

    private double pm25;     //pm2.5指数

    private double pm10;     //pm10指数

    private double so2;      //so2指数

    private double no2;      //no2指数

    private double co;       //co指数

    private double o3;       //o3指数

    private String qlty;    //空气质量类别

    private double tmp;        //当前温度

    private double hum;        //当前湿度

    private int code;    //天气代码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public double getPm25() {
        return pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public double getSo2() {
        return so2;
    }

    public void setSo2(double so2) {
        this.so2 = so2;
    }

    public double getNo2() {
        return no2;
    }

    public void setNo2(double no2) {
        this.no2 = no2;
    }

    public double getCo() {
        return co;
    }

    public void setCo(double co) {
        this.co = co;
    }

    public double getO3() {
        return o3;
    }

    public void setO3(double o3) {
        this.o3 = o3;
    }

    public String getQlty() {
        return qlty;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public double getTmp() {
        return tmp;
    }

    public void setTmp(double tmp) {
        this.tmp = tmp;
    }

    public double getHum() {
        return hum;
    }

    public void setHum(double hum) {
        this.hum = hum;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    //    public WeatherData(String cityName, String update, int aqi, float pm25, float pm10, float so2, float no2, float co, float o3, String qlty, float tmp, float hum) {
//        this.cityName = cityName;
//        this.update = update;
//        this.aqi = aqi;
//        this.pm25 = pm25;
//        this.pm10 = pm10;
//        this.so2 = so2;
//        this.no2 = no2;
//        this.co = co;
//        this.o3 = o3;
//        this.qlty = qlty;
//        this.tmp = tmp;
//        this.hum = hum;
//    }
}
