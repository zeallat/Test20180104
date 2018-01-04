package com.zeallat.baseapp.auth.signin;


import com.zeallat.baseapp.base.BasePresenter;
import com.zeallat.baseapp.base.BaseView;

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





