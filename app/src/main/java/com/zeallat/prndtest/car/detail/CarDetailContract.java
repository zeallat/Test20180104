package com.zeallat.prndtest.car.detail;


import com.zeallat.prndtest.base.BasePresenter;
import com.zeallat.prndtest.base.BaseView;

import java.util.List;

public interface CarDetailContract {
    interface View extends BaseView<Presenter> {
        void setCarImages(List<String> carImageUrls);

        void setTitle(String title);
    }

    interface Presenter extends BasePresenter {

    }
}


