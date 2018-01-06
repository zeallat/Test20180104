package com.zeallat.prndtest.main;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.zeallat.prndtest.data.model.Car;
import com.zeallat.prndtest.data.model.PaginationInfo;
import com.zeallat.prndtest.data.source.BaseDataSource;
import com.zeallat.prndtest.data.source.CarRepository;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private CarRepository mCarRepository = new CarRepository();

    public MainPresenter(MainContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void onCreate() {
        getCars();
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

    private void getCars() {
        mCarRepository.query(new BaseDataSource.GetDataCallback<Car>() {
            @Override
            public void onDataLoaded(List<Car> datas, @Nullable PaginationInfo paginationInfo) {
                Log.d("MainPresenter", "onDataLoaded");
                Log.d("MainPresenter", new GsonBuilder().setPrettyPrinting().create().toJson(paginationInfo));
                Log.d("MainPresenter", new GsonBuilder().setPrettyPrinting().create().toJson(datas));
                mView.addCars(datas);
            }

            @Override
            public void onDataNotAvailable() {
                Log.d("MainPresenter", "onDataNotAvailable");
            }
        });
    }

    @Override
    public void onRefreshCars() {

    }

    @Override
    public void reachBottomOfCars() {

    }
}
