package com.zeallat.baseapp.auth.signup;


import com.zeallat.baseapp.base.BasePresenter;
import com.zeallat.baseapp.base.BaseView;

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



