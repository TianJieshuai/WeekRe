package com.jiyun.shuaijie.weekre;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class RePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText regEt_name;
    private EditText regEt_yanzhengma;
    private Button button;
    private int conut = 60;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (conut > 0) {
                    button.setText("重新获取 " + conut--);
                    handler.sendEmptyMessageDelayed(1, 1000);
                } else {
                    button.setText("获取验证码");
                    button.setClickable(true);
                    button.setSelected(false);
                }
            }
        }
    };
    private Runnable runnable = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void run() {
            Random random = new Random();
            yanzhengma = new StringBuffer();
            for (int x = 0; x < 6; x++) {
                yanzhengma.append(random.nextInt(9));
            }
            NotificationManager systemService = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification yanzheng = new Notification.Builder(RePasswordActivity.this)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setTicker("验证码通知").setContentTitle("验证码：" + yanzhengma.toString())
                    .setAutoCancel(true)
                    .build();
            systemService.notify(1, yanzheng);
        }
    };
    private Button regBtn_submit;
    private StringBuffer yanzhengma;
    private EditText regEt_password;
    private SharedPreferences user;
    private Toolbar regTB_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_password);
        initView();
        user = getSharedPreferences("user", MODE_PRIVATE);
    }

    private void initView() {
        regTB_toolbar = (Toolbar) findViewById(R.id.regTB_toolbar);
        regTB_toolbar.setTitle("");
        setSupportActionBar(regTB_toolbar);
        regTB_toolbar.setNavigationIcon(R.mipmap.u34);
        regTB_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        regEt_name = (EditText) findViewById(R.id.regEt_name);
        regEt_yanzhengma = (EditText) findViewById(R.id.regEt_yanzhengma);
        button = (Button) findViewById(R.id.button);
        regEt_password = (EditText) findViewById(R.id.regEt_password);

        button.setOnClickListener(this);
        regBtn_submit = (Button) findViewById(R.id.regBtn_submit);
        regBtn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                button.setClickable(false);
                button.setSelected(true);
                handler.sendEmptyMessageDelayed(1, 50);
                handler.postDelayed(runnable, 10000);
                break;
            case R.id.regBtn_submit:
                submit();
                break;
        }
    }

    private void submit() {
        SharedPreferences.Editor edit = user.edit();
        // validate
        String name = regEt_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "帐号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String yanzhengma = regEt_yanzhengma.getText().toString().trim();
        if (TextUtils.isEmpty(yanzhengma)) {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String password = regEt_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String aaa = user.getString("aaa", null);
        if (TextUtils.isEmpty(aaa)) {
            Toast.makeText(this, "用户不存在", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        if (this.yanzhengma != null) {
            if (yanzhengma.equals(this.yanzhengma.toString()) && name.equals(aaa)) {
                Toast.makeText(this, "找回成功，密码修改为：" + password, Toast.LENGTH_SHORT).show();
                edit.putString("bbb", password);
                edit.clear();
                finish();
            } else {
                if (!(yanzhengma.equals(this.yanzhengma.toString()))) {
                    Toast.makeText(this, "验证码不正确", Toast.LENGTH_SHORT).show();
                }
                if (!(name.equals(aaa))) {
                    Toast.makeText(this, "用户不存在", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
