package com.zeallat.prndtest.auth.signin;


import com.zeallat.prndtest.base.BasePresenter;
import com.zeallat.prndtest.base.BaseView;

public interface SignInContract {
    interface View extends BaseView<Presenter> {
        String getEmail();

        String getPassword();
    }

    interface Presenter extends BasePresenter {
        void onClickButtonSignIn();

        void onClickButtonSignUp();
    }
}





