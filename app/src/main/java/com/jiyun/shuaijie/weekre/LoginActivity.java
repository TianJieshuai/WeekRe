package com.jiyun.shuaijie.weekre;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.shuaijie.weekre.utils.RegularUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

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
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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
            case R.id.iv_shareqq:
                UMShareAPI shareAPI = UMShareAPI.get(LoginActivity.this);
                shareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                     startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
                break;
            case R.id.iv_sharesina:
                UMShareAPI shareAPI1 = UMShareAPI.get(LoginActivity.this);
                shareAPI1.getPlatformInfo(this, SHARE_MEDIA.SINA, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
                break;
        }
    }

    private void submit() {
        SharedPreferences sp = getSharedPreferences("name", MODE_PRIVATE);
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
