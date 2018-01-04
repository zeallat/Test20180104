package com.zeallat.prndtest.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zeallat.prndtest.base.BaseActivityConfig;
import com.zeallat.prndtest.base.BaseViewActivity;

public class MainActivity extends BaseViewActivity<MainContract.Presenter> implements MainContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        new MainPresenter(this);
        setContentView(null);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setConfig(BaseActivityConfig config) {

    }
}



