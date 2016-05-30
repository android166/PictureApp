package com.tom.pictureapp;

import android.app.Application;

import com.karumi.dexter.Dexter;

/**
 * Created by Administrator on 2016/5/30.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Dexter.initialize(getApplicationContext());
    }
}
