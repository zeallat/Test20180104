package com.zeallat.prndtest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import kotlin.jvm.functions.Function0;

/**
 * BaseView.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-07-05.
 */
public interface BaseView<T> {
    void setPresenter(T presenter);

    void showToastShort(String message);

    void showToastShort(@StringRes int messageResId);

    void showToastLong(String message);

    void showToastLong(@StringRes int messageResId);

    void showAlertDialog(String message);

    void showAlertDialog(@StringRes int messageResId);

    void showAlertDialog(String message, Function0 positiveListener);

    void showAlertDialog(@StringRes int messageResId, Function0 positiveListener);

    void showAlertDialog(String message, Function0 positiveListener, Function0 negativeListener);

    void showAlertDialog(@StringRes int messageResId, Function0 positiveListener, Function0 negativeListener);

    void startActivity(Class<?> cls);

    void startActivityForResult(Class<?> cls, int requestCode);

    void startActivityForResult(Class<?> cls, Bundle extras, int requestCode);

    void startActivity(Class<?> cls, boolean isTransitionOverrideEnable);

    void startActivity(Class<?> cls, Bundle extras);

    void startActivity(Class<?> cls, Bundle extras, boolean isTransitionOverrideEnable);

    void showProgressDialog(@Nullable String message);

    void showProgressDialog();

    void dismissProgressDialog();

    boolean isDestroyed();
}
