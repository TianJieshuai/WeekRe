package com.jiyun.shuaijie.weekre;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.shuaijie.weekre.utils.RegularUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_password;
    private Button btn_login;
    private ImageView iv_shareqq;
    private ImageView iv_sharesina;
    private TextView tv_findpassword;
    private TextView tv_Register;
    private String name;
    private String loginpassword;
    private String loginname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();



    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
        iv_shareqq = (ImageView) findViewById(R.id.iv_shareqq);
        iv_shareqq.setOnClickListener(this);
        iv_sharesina = (ImageView) findViewById(R.id.iv_sharesina);
        iv_sharesina.setOnClickListener(this);
        tv_findpassword = (TextView) findViewById(R.id.tv_findpassword);
        tv_findpassword.setOnClickListener(this);
        tv_Register = (TextView) findViewById(R.id.tv_Register);
        tv_Register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                submit();
                break;
            case R.id.tv_findpassword:
                startActivity(new Intent(LoginActivity.this, RePasswordActivity.class));
                break;
            case R.id.tv_Register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void submit() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        loginname = sp.getString("aaa", "hehe");
        loginpassword = sp.getString("bbb", "haha");
        // validate
        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入手机号或邮箱", Toast.LENGTH_SHORT).show();
            return;

        }

        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (RegularUtils.isCheckName(name)) {
            if (name.equals(loginname)) {
                if (password.equals(loginpassword)) {
                    Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, RePasswordActivity.class));
                }


            } else {
                Toast.makeText(this, "该用户名不存在,请注册", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }

        } else {
            Toast.makeText(this, "用户名格式不正确", Toast.LENGTH_SHORT).show();
        }


        // TODO validate success, do something


    }
}
