package com.zeallat.prndtest.data.network;

import com.zeallat.prndtest.data.model.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CarService {
    @GET("/cars/")
    Call<List<Car>> getList(@Query("model") String modelId);

    @GET("/cars/{carId}/")
    Call<Car> getDetail(@Path("carId") String carId);
}
