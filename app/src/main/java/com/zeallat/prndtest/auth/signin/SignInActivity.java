package com.zeallat.prndtest.auth.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.zeallat.prndtest.R;
import com.zeallat.prndtest.base.BaseActivityConfig;
import com.zeallat.prndtest.base.BaseViewActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInActivity extends BaseViewActivity<SignInContract.Presenter> implements SignInContract.View {
    @BindView(R.id.editTextEmail) EditText mEditTextEmail;
    @BindView(R.id.editTextPassword) EditText mEditTextPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        new SignInPresenter(this);
        setContentView(R.layout.activity_sign_in);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setConfig(BaseActivityConfig config) {
    }

    @OnClick({R.id.buttonSignIn, R.id.buttonSignUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonSignIn:
                mPresenter.onClickButtonSignIn();
                break;
            case R.id.buttonSignUp:
                mPresenter.onClickButtonSignUp();
                break;
        }
    }

    @Override
    public String getEmail() {
        return mEditTextEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEditTextPassword.getText().toString();
    }


}

