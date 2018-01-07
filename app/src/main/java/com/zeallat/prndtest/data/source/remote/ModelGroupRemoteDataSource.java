package com.zeallat.prndtest.data.source.remote;

import android.support.annotation.NonNull;

import com.zeallat.prndtest.data.model.ModelGroup;
import com.zeallat.prndtest.data.model.PaginationInfo;
import com.zeallat.prndtest.data.model.specification.ModelGroupSpecificationById;
import com.zeallat.prndtest.data.network.Api;
import com.zeallat.prndtest.data.network.ApiCallback;
import com.zeallat.prndtest.data.network.CarMetaService;
import com.zeallat.prndtest.data.source.BaseDataSource;
import com.zeallat.prndtest.data.source.DefaultSpecification;
import com.zeallat.prndtest.data.source.Specification;

import java.util.Collections;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by HoJunLee on 2018-01-05.
 */

public class ModelGroupRemoteDataSource implements BaseDataSource<ModelGroup> {

    private CarMetaService mCarMetaService = Api.getInstance().getCarMetaService();

    @Override
    public void add(@NonNull ModelGroup data, @NonNull ResponseCallback callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(@NonNull Iterable<ModelGroup> datas, @NonNull ResponseCallback callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(@NonNull ModelGroup data, @NonNull ResponseCallback callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(@NonNull ModelGroup data, @NonNull ResponseCallback callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void query(@NonNull GetDataCallback<ModelGroup> callback) {
        query(new DefaultSpecification(), callback);
    }

    @Override
    public void query(@NonNull Specification specification, @NonNull GetDataCallback<ModelGroup> callback) {
        if (specification instanceof ModelGroupSpecificationById) {
            ModelGroupSpecificationById modelGroupSpecificationById = (ModelGroupSpecificationById) specification;
            mCarMetaService.getModelGroup(modelGroupSpecificationById.getId()).enqueue(new ApiCallback<ModelGroup>() {
                @Override
                public void onFailure(Call<ModelGroup> call, Throwable t) {
                    super.onFailure(call, t);
                    callback.onDataNotAvailable();
                }

                @Override
                public void onSuccess(Call<ModelGroup> call, Response<ModelGroup> response, ModelGroup data, PaginationInfo
                        paginationInfo) {
                    super.onSuccess(call, response, data, paginationInfo);
                    callback.onDataLoaded(Collections.singletonList(data), paginationInfo);
                }

                @Override
                public void onError(Call<ModelGroup> call, Response<ModelGroup> response) {
                    super.onError(call, response);
                    callback.onDataNotAvailable();
                }
            });
        }
    }
}
