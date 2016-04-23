package com.example.huangxiangyu.pm25onlinedetectsystemdemo.model;

/**
 * Created by huangxiangyu on 16/4/15.
 * In Pm25OnlineDetectSystemDemo
 */
public class Node {
    private int id;
    private String nodeName;
    private String nodeCode;
    private int cityId;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
