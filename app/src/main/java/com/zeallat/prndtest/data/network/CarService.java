package com.zeallat.prndtest.data.network;

import com.zeallat.prndtest.data.model.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/*
차량 데이터 조회 서비스
 */
public interface CarService {
    @GET("/cars/")
    Call<List<Car>> getList(@Query("page") int page);

    @GET("/cars/")
    Call<List<Car>> getList(@Query("model") int modelId, @Query("page") int page);

    @GET("/cars/{carId}/")
    Call<Car> getDetail(@Path("carId") int carId);
}
