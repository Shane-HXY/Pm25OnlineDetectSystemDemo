package com.example.huangxiangyu.pm25onlinedetectsystemdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huangxiangyu.pm25onlinedetectsystemdemo.R;


/**
 * Created by huangxiangyu on 16/4/25.
 * In Pm25OnlineDetectSystemDemo
 */
public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText usernameEdit;
    EditText passwordEdit;

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
                if (checkAccount(user, password)) {
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "用户名密码不正确,请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    String test = new String("test");
    private boolean checkAccount(String user, String password) {
        Log.w("LoginActivity", "checkAccount" + user + password + test);
        if (password.equals(test)) {
            return true;
        } else return false;
    }
}
