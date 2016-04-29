package com.example.huangxiangyu.pm25onlinedetectsystemdemo.activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.huangxiangyu.pm25onlinedetectsystemdemo.R;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.db.Pm25DB;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.util.HttpCallbackListener;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.util.HttpUtil;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.util.Utility;

/**
 * Created by huangxiangyu on 16/4/18.
 * In Pm25OnlineDetectSystemDemo
 */
public class SearchActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Pm25DB pm25DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_area);
        handleIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //通过请求获得城市信息
            queryFromServer(query);

            //Toast.makeText(this, address, Toast.LENGTH_SHORT).show();
        }
    }

    private void queryFromServer(final String code) {
        String addressHead = "http://api.heweather.com/x3/weather?city=";
        String addressBottom = "&key=b0c4cc786b5a4bd59fb6803479b59108";
        String address = addressHead + code + addressBottom;
        //showProgressDialog();
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                System.out.println(response);
                boolean result = false;
                result = Utility.handleNodesResponse(pm25DB, response);
                if (result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            queryNodes();
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                // 通过runOnUiThread()方法回到主线程处理逻辑
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        Toast.makeText(SearchActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void queryNodes() {
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
