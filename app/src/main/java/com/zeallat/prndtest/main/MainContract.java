package com.zeallat.prndtest.main;


import com.zeallat.prndtest.base.BasePresenter;
import com.zeallat.prndtest.base.BaseView;
import com.zeallat.prndtest.data.model.Car;

import java.util.List;

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void addCars(List<Car> cars);

        void setCars(List<Car> cars);

        void finishRefresh();
    }

    interface Presenter extends BasePresenter {
        void onRefreshCars();

        void reachBottomOfCars();
    }
}


