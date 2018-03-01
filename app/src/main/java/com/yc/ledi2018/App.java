package com.yc.ledi2018;

import android.app.Application;

import com.mob.MobSDK;

/**
 * Created by Administrator on 2018/3/1.
 */

public class App  extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
    }
}
