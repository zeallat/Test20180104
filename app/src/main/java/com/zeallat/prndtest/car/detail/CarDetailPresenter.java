package com.zeallat.prndtest.car.detail;


import android.support.annotation.Nullable;

import com.zeallat.prndtest.data.model.Car;
import com.zeallat.prndtest.data.model.PaginationInfo;
import com.zeallat.prndtest.data.model.specification.CarSpecificationById;
import com.zeallat.prndtest.data.source.BaseDataSource;
import com.zeallat.prndtest.data.source.CarRepository;
import com.zeallat.prndtest.util.DateUtil;

import java.util.List;
import java.util.Locale;

import static com.zeallat.prndtest.util.DateUtil.formatDate;
import static com.zeallat.prndtest.util.StringUtil.formatPrice;

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
                    mView.setPrice(formatPrice(mCar.getRealPrice()));
                    mView.setOriginalPrice(formatPrice(mCar.getPrice()));
                    mView.setStatus(mCar.getStatusEnum(), mCar.getStatusDisplay());
                    mView.setNumber(mCar.getCarNumber());
                    mView.setMileage(String.format(Locale.KOREA, "%,dkm", mCar.getMileage()));
                    mView.setRegistrationDate(formatDate(mCar.getInitialRegistrationDate().getTime(), DateUtil.Format.MONTH));
                    mView.setYear(String.format(Locale.KOREA, "%d년", mCar.getYear()));
                    mView.setFuel(mCar.getFuel());
                    mView.setNoItemViewVisible(mCar.getImageUrls() == null || mCar.getImageUrls().isEmpty());
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void onClickButtonCall() {
        //TODO: call
    }
}
