package com.zeallat.prndtest.auth.signup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BaseActivityConfig;
import com.zeallat.prndtest.base.BaseViewActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpActivity extends BaseViewActivity<SignUpContract.Presenter> implements SignUpContract.View {
    @BindView(R.id.editTextEmail) EditText mEditTextEmail;
    @BindView(R.id.editTextPassword) EditText mEditTextPassword;
    @BindView(R.id.editTextName) EditText mEditTextName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        new SignUpPresenter(this);
        setContentView(R.layout.activity_sign_up);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setConfig(BaseActivityConfig config) {
        config.setToolbarEnable(true);
        config.setToolbarTitle("회원가입");
        config.setToolbarNavigationType(NavigationType.BACK);
    }

    @OnClick(R.id.buttonSignUp)
    public void onViewClicked() {
        mPresenter.onClickButtonSignUp();
    }

    @Override
    public String getEmail() {
        return mEditTextEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEditTextPassword.getText().toString();
    }

    @Override
    public String getName() {
        return mEditTextName.getText().toString();
    }
}

