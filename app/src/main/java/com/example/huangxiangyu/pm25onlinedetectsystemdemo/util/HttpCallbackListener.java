package com.example.huangxiangyu.pm25onlinedetectsystemdemo.util;

/**
 * Created by huangxiangyu on 16/4/15.
 * In Pm25OnlineDetectSystemDemo
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
