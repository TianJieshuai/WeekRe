package com.jiyun.shuaijie.weekre;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.jiyun.shuaijie.weekre.adapter.ImageAdapter;
import com.jiyun.shuaijie.weekre.adapter.bean.SPUtils;

import java.util.ArrayList;

public class MainActivity  extends AppCompatActivity {

    private ViewPager view_viewpager;
    private Button bt_com;
    private ArrayList<View> views;
    Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 100:
                    view_viewpager.setCurrentItem(1);
                    handler.sendEmptyMessageDelayed(200,2000);
                    break;
                case 200:
                    view_viewpager.setCurrentItem(2);
                    bt_com.setVisibility(View.VISIBLE);
                    SPUtils.put(MainActivity.this,"Vesion",1);
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件id
        initView();
        //添加视图
        addView();
        //根据版本号判断是否执行一次
        VesionOne();
        //设置适配器，并写滑动监听
        initAdapter();






    }

    private void initAdapter() {
        ImageAdapter imageAdapter = new ImageAdapter(views);
        view_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (position == views.size() - 1){
                    bt_com.setVisibility(View.VISIBLE);
                }else {
                    bt_com.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        view_viewpager.setAdapter(imageAdapter);
    }

    private void VesionOne() {
        int vesion = (int) SPUtils.get(MainActivity.this, "Vesion", 0);
        if (vesion==0){
            Message message = handler.obtainMessage();
            message.what=100;
            handler.sendMessageDelayed(message,2000);
        }else {

            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }

    private void addView() {
        views = new ArrayList<>();
        View image_item = LayoutInflater.from(MainActivity.this).inflate(R.layout.image_item, null);
        View image_item2 = LayoutInflater.from(MainActivity.this).inflate(R.layout.image_item2, null);
        View image_item3 = LayoutInflater.from(MainActivity.this).inflate(R.layout.image_item3, null);
        views.add(image_item);
        views.add(image_item2);
        views.add(image_item3);
    }

    private void initView() {
        view_viewpager = (ViewPager) findViewById(R.id.view_viewpager);
        bt_com = (Button) findViewById(R.id.bt_com);
        bt_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }
}

