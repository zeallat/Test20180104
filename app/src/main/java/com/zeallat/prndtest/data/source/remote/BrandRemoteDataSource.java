package com.zeallat.prndtest.data.source.remote;

import android.support.annotation.NonNull;

import com.zeallat.prndtest.data.model.Brand;
import com.zeallat.prndtest.data.model.PaginationInfo;
import com.zeallat.prndtest.data.model.specification.BrandSpecificationById;
import com.zeallat.prndtest.data.network.Api;
import com.zeallat.prndtest.data.network.ApiCallback;
import com.zeallat.prndtest.data.network.CarMetaService;
import com.zeallat.prndtest.data.source.BaseDataSource;
import com.zeallat.prndtest.data.source.DefaultSpecification;
import com.zeallat.prndtest.data.source.Specification;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by HoJunLee on 2018-01-05.
 */

public class BrandRemoteDataSource implements BaseDataSource<Brand> {

    private CarMetaService mCarMetaService = Api.getInstance().getCarMetaService();

    @Override
    public void add(@NonNull Brand data, @NonNull ResponseCallback callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(@NonNull Iterable<Brand> datas, @NonNull ResponseCallback callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(@NonNull Brand data, @NonNull ResponseCallback callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(@NonNull Brand data, @NonNull ResponseCallback callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void query(@NonNull GetDataCallback<Brand> callback) {
        query(new DefaultSpecification(), callback);
    }

    @Override
    public void query(@NonNull Specification specification, @NonNull GetDataCallback<Brand> callback) {
        if (specification instanceof DefaultSpecification) {
            mCarMetaService.getBrands().enqueue(new ApiCallback<List<Brand>>() {
                @Override
                public void onFailure(Call<List<Brand>> call, Throwable t) {
                    super.onFailure(call, t);
                    callback.onDataNotAvailable();
                }

                @Override
                public void onSuccess(Call<List<Brand>> call, Response<List<Brand>> response, List<Brand> data, PaginationInfo
                        paginationInfo) {
                    super.onSuccess(call, response, data, paginationInfo);
                    callback.onDataLoaded(data, paginationInfo);
                }

                @Override
                public void onError(Call<List<Brand>> call, Response<List<Brand>> response) {
                    super.onError(call, response);
                    callback.onDataNotAvailable();
                }
            });
        } else if (specification instanceof BrandSpecificationById) {
            BrandSpecificationById brandSpecificationById = (BrandSpecificationById) specification;
            mCarMetaService.getBrand(brandSpecificationById.getId()).enqueue(new ApiCallback<Brand>() {
                @Override
                public void onFailure(Call<Brand> call, Throwable t) {
                    super.onFailure(call, t);
                    callback.onDataNotAvailable();
                }

                @Override
                public void onSuccess(Call<Brand> call, Response<Brand> response, Brand data, PaginationInfo paginationInfo) {
                    super.onSuccess(call, response, data, paginationInfo);
                    callback.onDataLoaded(Collections.singletonList(data), paginationInfo);
                }

                @Override
                public void onError(Call<Brand> call, Response<Brand> response) {
                    super.onError(call, response);
                    callback.onDataNotAvailable();
                }
            });
        }
    }
}
