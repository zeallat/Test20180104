package com.zeallat.prndtest.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.zeallat.prndtest.data.pref.PreferencesRepositoryImpl;

import io.realm.Realm;

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
        //Realm 초기화.
        Realm.init(this);
        //utils init
        Utils.init(this);
        //pref init
        PreferencesRepositoryImpl.init(this);
        //init if need poi
        initApachePoi();
    }

    private void initApachePoi() {
        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //if multidex needed, uncomment this.
        MultiDex.install(this);
    }
}
