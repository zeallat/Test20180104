package com.zeallat.prndtest.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import kotlin.jvm.functions.Function0;

import static android.text.TextUtils.isEmpty;

/**
 * BaseViewDelegate.java
 *
 * @author HoJunLee
 * @description comment here
 * Created by HoJunLee on 2017-09-12.
 */
public class BaseViewDelegate<T extends BasePresenter> implements BaseView<T> {

    private T mPresenter;
    private Activity mActivity;
    private Fragment mFragment;

    public BaseViewDelegate(Activity activity) {
        mActivity = activity;
    }

    public BaseViewDelegate(Fragment fragment) {
        mFragment = fragment;
    }

    private Activity getActivity() {
        if (mActivity != null) return mActivity;
        return mFragment.getActivity();
    }

    @Override
    public void showToastShort(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    @Override
    public void showToastLong(String message) {
        showToast(message, Toast.LENGTH_LONG);
    }

    @Override
    public void showToastShort(int messageResId) {
        showToast(getActivity().getResources().getString(messageResId), Toast.LENGTH_SHORT);
    }

    @Override
    public void showToastLong(int messageResId) {
        showToast(getActivity().getResources().getString(messageResId), Toast.LENGTH_LONG);
    }

    private void showToast(String message, int duration) {
        Toast.makeText(getActivity(), message, duration).show();
    }

    @Override
    public void showAlertDialog(String message) {
        showAlertDialog(message, null);
    }

    @Override
    public void showAlertDialog(int messageResId) {
        showAlertDialog(getActivity().getResources().getString(messageResId), null);
    }

    @Override
    public void showAlertDialog(String message, Function0 positiveListener) {
        if (isEmpty(message)) return;
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("확인", (dialogInterface, i) -> {
                    if (positiveListener != null) positiveListener.invoke();
                })
                .show();
    }

    @Override
    public void showAlertDialog(int messageResId, Function0 positiveListener) {
        showAlertDialog(getActivity().getResources().getString(messageResId), positiveListener);
    }

    @Override
    public void showAlertDialog(String message, Function0 positiveListener, Function0 negativeListener) {
        if (isEmpty(message)) return;
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("확인", (dialogInterface, i) -> {
                    if (positiveListener != null) positiveListener.invoke();
                })
                .setNegativeButton("취소", (dialogInterface, i) -> {
                    if (negativeListener != null) negativeListener.invoke();
                })
                .show();
    }

    @Override
    public void showAlertDialog(int messageResId, Function0 positiveListener, Function0 negativeListener) {
        showAlertDialog(getActivity().getResources().getString(messageResId), positiveListener, negativeListener);
    }

    @Override
    public void setPresenter(T presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    @Override
    public void startActivity(Class<?> cls, boolean isTransitionOverrideEnable) {
        startActivity(cls, null, isTransitionOverrideEnable);
    }

    @Override
    public void startActivity(Class<?> cls, Bundle extras, boolean isTransitionOverrideEnable) {
        Intent intent = new Intent(getActivity(), cls);
        if (extras != null) intent.putExtras(extras);
        intent.putExtra(BaseActivity.EXTRA_IS_TRANSITION_OVERRIDE_ENABLE, isTransitionOverrideEnable);
        if (mFragment != null) mFragment.startActivity(intent);
        else mActivity.startActivity(intent);
    }

    @Override
    public void startActivity(Class<?> cls, Bundle extras) {
        startActivity(cls, extras, true);
    }

    @Override
    public void startActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        if (mFragment != null) mFragment.startActivityForResult(intent, requestCode);
        else mActivity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityForResult(Class<?> cls, Bundle extras, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        if (extras != null) intent.putExtras(extras);
        if (mFragment != null) mFragment.startActivityForResult(intent, requestCode);
        else mActivity.startActivityForResult(intent, requestCode);
    }

    private ProgressDialog mProgressDialog = null;

    @Override
    public void showProgressDialog(@Nullable String message) {
        if (isEmpty(message)) message = "잠시만 기다려주세요";
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage(message);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.show();
        }
    }

    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    public void showProgressDialog() {
        showProgressDialog("");
    }
}
