package com.zeallat.prndtest.car.detail;


import android.support.annotation.Nullable;

import com.zeallat.prndtest.data.model.Car;
import com.zeallat.prndtest.data.model.PaginationInfo;
import com.zeallat.prndtest.data.model.specification.CarSpecificationById;
import com.zeallat.prndtest.data.source.BaseDataSource;
import com.zeallat.prndtest.data.source.CarRepository;

import java.util.List;

public class CarDetailPresenter implements CarDetailContract.Presenter {

    private CarDetailContract.View mView;
    private CarRepository mCarRepository = new CarRepository();
    private int mCarId;
    private Car mCar;

    public CarDetailPresenter(CarDetailContract.View view, int carId) {
        mView = view;
        mView.setPresenter(this);
        mCarId = carId;
    }

    @Override
    public void onCreate() {
        getCar();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    private void getCar() {
        mCarRepository.query(new CarSpecificationById(mCarId), new BaseDataSource.GetDataCallback<Car>() {
            @Override
            public void onDataLoaded(List<Car> datas, @Nullable PaginationInfo paginationInfo) {
                if (datas.size() > 0) {
                    mCar = datas.get(0);
                    mView.setCarImages(mCar.getImageUrls());
                    mView.setTitle(mCar.getFullName());
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
