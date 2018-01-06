package com.zeallat.prndtest.data.source.remote;

import android.support.annotation.NonNull;

import com.zeallat.prndtest.data.model.Car;
import com.zeallat.prndtest.data.model.PaginationInfo;
import com.zeallat.prndtest.data.model.specification.CarSpecificationById;
import com.zeallat.prndtest.data.model.specification.CarSpecificationByModelId;
import com.zeallat.prndtest.data.network.Api;
import com.zeallat.prndtest.data.network.ApiCallback;
import com.zeallat.prndtest.data.source.BaseDataSource;
import com.zeallat.prndtest.data.source.Specification;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by HoJunLee on 2018-01-05.
 */

public class CarRemoteDataSource implements BaseDataSource<Car> {
    @Override
    public void add(@NonNull Car data, @NonNull ResponseCallback callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(@NonNull Iterable<Car> datas, @NonNull ResponseCallback callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(@NonNull Car data, @NonNull ResponseCallback callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(@NonNull Car data, @NonNull ResponseCallback callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void query(Specification specification, @NonNull GetDataCallback<Car> callback) {

        if (specification == null || specification instanceof CarSpecificationByModelId) {
            String modelId = specification == null ? "" : ((CarSpecificationByModelId) specification).getModelId();

            Api.getInstance().getCarService().getList(modelId).enqueue(new ApiCallback<List<Car>>() {
                @Override
                public void onFailure(Call<List<Car>> call, Throwable t) {
                    super.onFailure(call, t);
                    callback.onDataNotAvailable();
                }

                @Override
                public void onSuccess(Call<List<Car>> call, Response<List<Car>> response, List<Car> data, PaginationInfo paginationInfo) {
                    super.onSuccess(call, response, data, paginationInfo);
                    callback.onDataLoaded(createCarId(response.body()), paginationInfo);
                }

                @Override
                public void onError(Call<List<Car>> call, Response<List<Car>> response) {
                    super.onError(call, response);
                    callback.onDataNotAvailable();
                }
            });
        } else if (specification instanceof CarSpecificationById) {
            CarSpecificationById carSpecificationById = (CarSpecificationById) specification;

            Api.getInstance().getCarService().getDetail(carSpecificationById.getId()).enqueue(new ApiCallback<Car>() {
                @Override
                public void onFailure(Call<Car> call, Throwable t) {
                    super.onFailure(call, t);
                    callback.onDataNotAvailable();
                }

                @Override
                public void onSuccess(Call<Car> call, Response<Car> response, Car data, PaginationInfo paginationInfo) {
                    super.onSuccess(call, response, data, paginationInfo);
                    callback.onDataLoaded(createCarId(Collections.singletonList(response.body())), paginationInfo);
                }

                @Override
                public void onError(Call<Car> call, Response<Car> response) {
                    super.onError(call, response);
                    callback.onDataNotAvailable();
                }
            });
        }
    }

    private List<Car> createCarId(List<Car> cars) {
        for (Iterator<Car> carIterator = cars.iterator(); carIterator.hasNext(); ) {
            carIterator.next().parseIdFromUrl();
        }
        return cars;
    }
}
