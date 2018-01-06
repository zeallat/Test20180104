package com.zeallat.prndtest.data.source.remote;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.zeallat.prndtest.data.model.Car;
import com.zeallat.prndtest.data.model.specification.CarSpecificationById;
import com.zeallat.prndtest.data.model.specification.CarSpecificationByModelId;
import com.zeallat.prndtest.data.network.Api;
import com.zeallat.prndtest.data.source.BaseDataSource;
import com.zeallat.prndtest.data.source.Specification;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
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

            Api.getInstance().getCarService().getList(modelId).enqueue(new Callback<List<Car>>() {
                @Override
                public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                    if (response.code() == 200) {
                        callback.onDataLoaded(response.body());
                    } else {
                        callback.onDataNotAvailable();
                    }
                }

                @Override
                public void onFailure(Call<List<Car>> call, Throwable t) {
                    callback.onDataNotAvailable();
                }
            });
        } else if (specification instanceof CarSpecificationById) {
            CarSpecificationById carSpecificationById = (CarSpecificationById) specification;

            Api.getInstance().getCarService().getDetail(carSpecificationById.getId()).enqueue(new Callback<Car>() {
                @Override
                public void onResponse(Call<Car> call, Response<Car> response) {
                    if (response.code() == 200) {
                        callback.onDataLoaded(Collections.singletonList(response.body()));
                    } else {
                        callback.onDataNotAvailable();
                    }
                }

                @Override
                public void onFailure(Call<Car> call, Throwable t) {
                    callback.onDataNotAvailable();
                }
            });
        }
    }

    private void setCarId(Iterable<Car> cars) {
        Car car;
        for (Iterator<Car> carIterator = cars.iterator(); carIterator.hasNext(); ) {
            car = carIterator.next();
            car.setId(Integer.parseInt(Uri.parse(car.getAbsoluteUrl()).getLastPathSegment()));
        }
    }
}
