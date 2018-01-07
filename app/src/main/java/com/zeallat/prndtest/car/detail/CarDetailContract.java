package com.zeallat.prndtest.car.detail;


import com.zeallat.prndtest.base.BasePresenter;
import com.zeallat.prndtest.base.BaseView;
import com.zeallat.prndtest.data.model.Car;

import java.util.List;

public interface CarDetailContract {
    interface View extends BaseView<Presenter> {
        void setCarImages(List<String> carImageUrls);

        void setTitle(String title);

        void setPrice(String text);

        void setOriginalPrice(String text);

        void setStatus(Car.Status status, String statusText);

        void setNumber(String text);

        void setMileage(String text);

        void setRegistrationDate(String text);

        void setYear(String text);

        void setFuel(String text);

        void setNoItemViewVisible(boolean isVisible);
    }

    interface Presenter extends BasePresenter {
        void onClickButtonCall();
    }
}


