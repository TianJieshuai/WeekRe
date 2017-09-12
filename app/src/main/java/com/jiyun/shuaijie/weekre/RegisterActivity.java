package com.jiyun.shuaijie.weekre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.jiyun.shuaijie.weekre.utils.RegularUtils;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView register_image;
    private Toolbar register_tb;
    private EditText register_name;
    private EditText register_pass;
    private Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        register_image = (ImageView) findViewById(R.id.register_image);
        register_tb = (Toolbar) findViewById(R.id.register_tb);
        register_name = (EditText) findViewById(R.id.register_name);
        register_pass = (EditText) findViewById(R.id.register_pass);
        register_btn = (Button) findViewById(R.id.register_btn);

        register_image.setOnClickListener(this);
        register_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_image:
                finish();
                break;
            case R.id.register_btn:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String name = register_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入手机号或邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        String pass = register_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        if (!RegularUtils.isCheckName(name)){
            Toast.makeText(this, "账号输入有误", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!RegularUtils.isCheckPassword(pass)){
            Toast.makeText(this, "密码输入有误", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(RegisterActivity.this, CodeActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("pass",pass);
        startActivity(intent);
    }
}
