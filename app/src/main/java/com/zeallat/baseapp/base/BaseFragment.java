package com.zeallat.baseapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HoJunLee on 2017-06-03.
 */

public abstract class BaseFragment extends Fragment {
    private Unbinder mUnbinder;
    private RequestManager mRequestManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestManager = Glide.with(this);
    }

    protected RequestManager getGlide() {
        return mRequestManager;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            //regist eventbus
            EventBus.getDefault().register(this);
        } catch (Throwable t) {
//            t.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            //unregist eventbus
            EventBus.getDefault().unregister(this);
        } catch (Throwable t) {
//            t.printStackTrace();
        }
    }

}
