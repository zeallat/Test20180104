package com.zeallat.prndtest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import kotlin.jvm.functions.Function0;

/**
 * Created by HoJunLee on 2017-06-03.
 */

public abstract class BaseViewFragment<T extends BasePresenter> extends BaseFragment implements BaseView<T> {

    protected T mPresenter;
    private BaseViewDelegate<T> mBaseViewDelegate = new BaseViewDelegate<>(this);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBaseViewDelegate = new BaseViewDelegate<>(this);
        mPresenter.onCreate();
    }

    @Override
    public void showToastShort(String message) {
        mBaseViewDelegate.showToastShort(message);
    }

    @Override
    public void showToastLong(String message) {
        mBaseViewDelegate.showToastLong(message);
    }

    @Override
    public void showToastShort(int messageResId) {
        mBaseViewDelegate.showToastShort(messageResId);
    }

    @Override
    public void showToastLong(int messageResId) {
        mBaseViewDelegate.showToastLong(messageResId);
    }

    @Override
    public void showAlertDialog(String message) {
        mBaseViewDelegate.showAlertDialog(message);
    }

    @Override
    public void showAlertDialog(int messageResId) {
        mBaseViewDelegate.showAlertDialog(messageResId);
    }

    @Override
    public void showAlertDialog(String message, Function0 positiveListener) {
        mBaseViewDelegate.showAlertDialog(message, positiveListener);
    }

    @Override
    public void showAlertDialog(int messageResId, Function0 positiveListener) {
        mBaseViewDelegate.showAlertDialog(messageResId, positiveListener);
    }

    @Override
    public void showAlertDialog(String message, Function0 positiveListener, Function0 negativeListener) {
        mBaseViewDelegate.showAlertDialog(message, positiveListener, negativeListener);
    }

    @Override
    public void showAlertDialog(int messageResId, Function0 positiveListener, Function0 negativeListener) {
        mBaseViewDelegate.showAlertDialog(messageResId, positiveListener, negativeListener);
    }

    @Override
    public void setPresenter(T presenter) {
        this.mPresenter = presenter;
        mBaseViewDelegate.setPresenter(mPresenter);
    }

    @Override
    public void startActivity(Class<?> cls) {
        mBaseViewDelegate.startActivity(cls);
    }

    @Override
    public void startActivity(Class<?> cls, boolean isTransitionOverrideEnable) {
        mBaseViewDelegate.startActivity(cls, isTransitionOverrideEnable);
    }

    @Override
    public void startActivity(Class<?> cls, Bundle extras, boolean isTransitionOverrideEnable) {
        mBaseViewDelegate.startActivity(cls, extras, isTransitionOverrideEnable);
    }

    @Override
    public void startActivity(Class<?> cls, Bundle extras) {
        mBaseViewDelegate.startActivity(cls, extras);
    }

    @Override
    public void startActivityForResult(Class<?> cls, int requestCode) {
        mBaseViewDelegate.startActivityForResult(cls, requestCode);
    }

    @Override
    public void startActivityForResult(Class<?> cls, Bundle extras, int requestCode) {
        mBaseViewDelegate.startActivityForResult(cls, extras, requestCode);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void showProgressDialog(@Nullable String message) {
        mBaseViewDelegate.showProgressDialog(message);
    }

    @Override
    public void dismissProgressDialog() {
        mBaseViewDelegate.dismissProgressDialog();
    }

    @Override
    public void showProgressDialog() {
        mBaseViewDelegate.showProgressDialog();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
