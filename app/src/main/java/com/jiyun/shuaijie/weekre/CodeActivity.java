package com.jiyun.shuaijie.weekre;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class CodeActivity extends AppCompatActivity implements View.OnClickListener {

    private String NAME = "user";
    private static final int NOTIFICATIONID=1;
    private ImageView code_image;
    private Toolbar code_tb;
    private EditText code_num;
    private Button code_gain;
    private LinearLayout linearLayout;
    private Button code_btn;
    private String name;
    private String pass;
    private int i=60;
    private Handler handler=new Handler();
    private Runnable run=new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void run() {
            i--;
            code_gain.setText("("+i+")重新获取");
            code_gain.setClickable(false);
            handler.postDelayed(run,1000);
            if (i==0){
                code_gain.setText("获取验证码");
                code_gain.setClickable(true);
            }
            if (i==40){
                Random random=new Random();
                r = random.nextInt();
                notification();
            }
        }
    };
    private int r;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        initView();

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        pass = intent.getStringExtra("pass");

        Context context=CodeActivity.this;
        SharedPreferences preferences = context.getSharedPreferences(NAME, MODE_PRIVATE);
        edit = preferences.edit();


    }

    private void initView() {
        code_image = (ImageView) findViewById(R.id.code_image);
        code_tb = (Toolbar) findViewById(R.id.code_tb);
        code_num = (EditText) findViewById(R.id.code_num);
        code_gain = (Button) findViewById(R.id.code_gain);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        code_btn = (Button) findViewById(R.id.code_btn);

        code_image.setOnClickListener(this);
        code_gain.setOnClickListener(this);
        code_btn.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.code_image:
                finish();
                break;
            case R.id.code_gain:
                handler.postDelayed(run,1000);
                break;
            case R.id.code_btn:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String num = code_num.getText().toString().trim();
        if (TextUtils.isEmpty(num)) {
            Toast.makeText(this, "验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        String s = Integer.toString(r);
        if (!s.equals(num)){
            Toast.makeText(this, "验证码输入有误", Toast.LENGTH_SHORT).show();
            return;
        }

        edit.putString("aaa",name);
        edit.putString("bbb",pass);
        edit.commit();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void notification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(CodeActivity.this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.hellokity);
        builder.setLargeIcon(bitmap)
                .setSmallIcon(R.mipmap.hellokity)
                .setContentTitle("验证码")
                .setContentText("获取到的验证码")
                .setSubText(Integer.toString(r))
                .setTicker("您有新的消息，请注意查收!")
                .setAutoCancel(true);
        Notification build = builder.build();
        manager.notify(NOTIFICATIONID,build);
    }
}
