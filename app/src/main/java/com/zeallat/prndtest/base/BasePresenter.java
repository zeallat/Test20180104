package com.zeallat.prndtest.base;

/**
 * BasePresenter.java
 *
 * @author HoJunLee
 * @description 베이스 presenter.
 * Created by HoJunLee on 2017-07-05.
 */
public interface BasePresenter {
    void onCreate();

    void onResume();

    void onPause();

    void onDestroy();
}
