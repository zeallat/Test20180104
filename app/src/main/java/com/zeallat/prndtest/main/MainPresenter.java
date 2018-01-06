package com.zeallat.prndtest.main;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.zeallat.prndtest.data.model.Car;
import com.zeallat.prndtest.data.source.BaseDataSource;
import com.zeallat.prndtest.data.source.CarRepository;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

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
        new CarRepository().query(null, new BaseDataSource.GetDataCallback<Car>() {
            @Override
            public void onDataLoaded(List<Car> datas) {
                Log.d("MainPresenter", "onDataLoaded");
                Log.d("MainPresenter", new GsonBuilder().setPrettyPrinting().create().toJson(datas));
            }

            @Override
            public void onDataNotAvailable() {
                Log.d("MainPresenter", "onDataNotAvailable");
            }
        });
    }
}
