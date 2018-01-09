package com.zeallat.prndtest.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.blankj.utilcode.util.Utils;
import com.zeallat.prndtest.data.pref.PreferencesRepositoryImpl;

/**
 * BaseApplication.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-07-19.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        //utils init
        Utils.init(this);
        //pref init
        PreferencesRepositoryImpl.init(this);
        //Support for vector drawable
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //if multidex needed, uncomment this.
        MultiDex.install(this);
    }
}
