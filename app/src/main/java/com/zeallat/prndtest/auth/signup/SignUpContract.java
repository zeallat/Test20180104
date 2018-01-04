package com.zeallat.prndtest.auth.signup;


import com.zeallat.prndtest.base.BasePresenter;
import com.zeallat.prndtest.base.BaseView;

public interface SignUpContract {
    interface View extends BaseView<Presenter> {
        String getEmail();

        String getPassword();

        String getName();
    }

    interface Presenter extends BasePresenter {
        void onClickButtonSignUp();
    }
}



