package com.zeallat.prndtest.data.source;


import com.zeallat.prndtest.data.model.Car;
import com.zeallat.prndtest.data.source.remote.CarRemoteDataSource;

/**
 * 차량 데이터 Repository
 */
public class CarRepository extends BaseRepository<Car, CarRemoteDataSource> {
    public CarRepository() {
        super(new CarRemoteDataSource());
    }
}
