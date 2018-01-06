package com.zeallat.prndtest.data.source;


import com.zeallat.prndtest.data.model.Car;
import com.zeallat.prndtest.data.source.remote.CarRemoteDataSource;

/**
 * Created by HoJunLee on 2018-01-05.
 */

public class CarRepository extends BaseRepository<Car, CarRemoteDataSource> {
    public CarRepository() {
        super(new CarRemoteDataSource());
    }
}
