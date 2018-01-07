package com.zeallat.prndtest.data.network;

import com.zeallat.prndtest.data.model.Brand;
import com.zeallat.prndtest.data.model.ModelGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
차량 메타 데이터 조회 서비스
 */
public interface CarMetaService {
    @GET("/car_meta/brands/")
    Call<List<Brand>> getBrands();

    @GET("/car_meta/brand/{brandId}")
    Call<Brand> getBrand(@Path("brandId") int brandId);

    @GET("/car_meta/model_group/{modelGroupId}")
    Call<ModelGroup> getModelGroup(@Path("modelGroupId") int modelGroupId);
}
