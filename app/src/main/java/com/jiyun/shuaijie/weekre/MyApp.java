package com.jiyun.shuaijie.weekre;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by Lenovo on 2017/9/12.
 */

public class MyApp extends Application {
    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("822030254", "dbf21d137f1fe15613f9ef4815c392ef", "http://sns.whalecloud.com");
    }
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        Config.DEBUG=true;
    }
}
