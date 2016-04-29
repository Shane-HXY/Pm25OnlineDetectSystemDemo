package com.example.huangxiangyu.pm25onlinedetectsystemdemo.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by huangxiangyu on 16/4/15.
 * In Pm25OnlineDetectSystemDemo
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                        response.append("\r\n");
                    }
                    reader.close();

                    if (listener != null) {
//                       回调onFinish方法
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
//                       回调onError方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    public static void submitPostData(final Map<String, String> params, final String encode, final String address) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] data = "getRequestData(params, encode).toString()".getBytes();     //获得请求体
                HttpURLConnection httpURLConnection;
                try {
                    URL url = new URL(address);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setDoInput(true);                                 //打开输入流
                    httpURLConnection.setDoOutput(true);                                //打开输出流
                    httpURLConnection.setRequestMethod("POST");                         //设置以post方式提交数据
                    httpURLConnection.setUseCaches(false);                              //使用Post方式不能使用缓存
                    //httpURLConnection.connect();
                    //设置请求体的类型是文本类型
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    //设置请求体的长度
                    httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
                    //获得输出流,向服务器写入数据
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(data);
                    int response = httpURLConnection.getResponseCode();                 //获得服务器的响应码
                    if (response == httpURLConnection.HTTP_OK) {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

//    private static String dealResponseResult(InputStream inputStream) {
//        String resultData;       //储存处理结果
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        byte[] data = new byte[1024];
//        int len;
//        try {
//            while ((len = inputStream.read(data)) != -1) {
//                byteArrayOutputStream.write(data, 0, len);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        resultData = new String(byteArrayOutputStream.toByteArray());
//        return resultData;
//    }

    private static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();                          //储存封装好的请求信息
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);               //删除最后一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

}
