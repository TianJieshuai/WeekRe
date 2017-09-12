package com.jiyun.shuaijie.weekre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CodeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton code_image;
    private Toolbar code_tb;
    private EditText code_num;
    private Button code_gain;
    private LinearLayout linearLayout;
    private Button code_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        initView();
    }

    private void initView() {
        code_image = (ImageButton) findViewById(R.id.code_image);
        code_tb = (Toolbar) findViewById(R.id.code_tb);
        code_num = (EditText) findViewById(R.id.code_num);
        code_gain = (Button) findViewById(R.id.code_gain);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        code_btn = (Button) findViewById(R.id.code_btn);

        code_image.setOnClickListener(this);
        code_gain.setOnClickListener(this);
        code_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.code_image:

                break;
            case R.id.code_gain:
                finish();
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


    }
}
