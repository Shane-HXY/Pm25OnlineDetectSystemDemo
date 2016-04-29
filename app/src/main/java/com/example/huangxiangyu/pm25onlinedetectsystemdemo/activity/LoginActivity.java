package com.example.huangxiangyu.pm25onlinedetectsystemdemo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huangxiangyu.pm25onlinedetectsystemdemo.R;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.db.Pm25DB;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.model.User;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.util.HttpUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.huangxiangyu.pm25onlinedetectsystemdemo.util.HttpUtil.submitPostData;


/**
 * Created by huangxiangyu on 16/4/25.
 * In Pm25OnlineDetectSystemDemo
 */
public class LoginActivity extends AppCompatActivity {

    public static final String address = "http://10.0.2.2:8080/MyHttp/Login";

    public static final int SHOW_RESPONSE = 0;

    Button loginButton;
    EditText usernameEdit;
    EditText passwordEdit;

    private Pm25DB pm25DB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);
        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        usernameWrapper.setHint("请输入用户名");
        passwordWrapper.setHint("请输入密码");

        usernameEdit = (EditText) findViewById(R.id.user_name_edit);
        passwordEdit = (EditText) findViewById(R.id.user_password);
        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usernameEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", user);
                params.put("password", password);
                //submitPostData(params, "uft-8", address);
                if (true) {
                    setResult(RESULT_OK);
                    // TODO: 传用户名密码至服务器中,判定是否符合条件
                    //saveLoginState(user, password);
                    SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                    editor.putString("username", user);
                    editor.putString("password", password);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, FrontActivity.class);
                    intent.putExtra("user_name", user);
                    intent.putExtra("from_login_activity", true);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "用户名密码不正确,请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void saveLoginState(String username, String password) {
        User user = new User(username, password);
        pm25DB = Pm25DB.getInstance(this);
        pm25DB.saveUser(user);
    }

}
