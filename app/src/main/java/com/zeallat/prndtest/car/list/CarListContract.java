package com.zeallat.prndtest.car.list;


import com.zeallat.prndtest.base.BasePresenter;
import com.zeallat.prndtest.base.BaseView;
import com.zeallat.prndtest.data.model.Car;

import java.util.List;

public interface CarListContract {
    interface View extends BaseView<Presenter> {
        void addCars(List<Car> cars);

        void setCars(List<Car> cars);

        void finishRefresh();

        void showSearchPage();

        int getSearhModelId();

        String getSearchModelName();

        void setSearchKeyword(String searchKeyword);

        void showCarDetailPage(int carId);

        void setNoItemViewVisible(boolean isVisible);
    }

    interface Presenter extends BasePresenter {
        void onRefreshCars();

        void reachBottomOfCars();

        void onClickSearchBox();

        void onClickCar(Car car);
    }
}


