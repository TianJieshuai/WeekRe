package com.jiyun.shuaijie.weekre.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-08-25.
 */
public class ImageAdapter extends PagerAdapter {
    private ArrayList<View> views;

    public ImageAdapter(ArrayList<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return  view==object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
      container.removeView(views.get(position));
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       container.addView(views.get(position));
        return views.get(position);
    }
}
