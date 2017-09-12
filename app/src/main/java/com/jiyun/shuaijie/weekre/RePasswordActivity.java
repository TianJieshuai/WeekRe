package com.jiyun.shuaijie.weekre;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class RePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText regEt_name;
    private EditText editText;
    private Button button;
    private Handler handler = new Handler();
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
            Notification yanzheng = new Notification.Builder(RePasswordActivity.this).setSmallIcon(R.mipmap.ic_launcher_round).setTicker("验证码通知").setContentTitle("").setSubText(yanzhengma.toString()).build();
            systemService.notify(1, yanzheng);
        }
    };
    private Button regBtn_submit;
    private StringBuffer yanzhengma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_password);
        initView();
    }

    private void initView() {
        regEt_name = (EditText) findViewById(R.id.regEt_name);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);
        regBtn_submit = (Button) findViewById(R.id.regBtn_submit);
        regBtn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                handler.postDelayed(runnable, 100000);
                break;
            case R.id.regBtn_submit:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String name = regEt_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "帐号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String editTextString = editText.getText().toString().trim();
        if (TextUtils.isEmpty(editTextString)) {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        if (yanzhengma != null) {
            if (editTextString.equals(yanzhengma.toString())){
                Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
