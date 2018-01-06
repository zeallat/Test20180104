package com.zeallat.prndtest.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zeallat.prndtest.data.model.PaginationInfo;

import java.util.List;

/**
 * Created by HoJunLee on 2018-01-05.
 */

public interface BaseDataSource<T> {

    interface GetDataCallback<T> {
        void onDataLoaded(List<T> datas, @Nullable PaginationInfo paginationInfo);

        void onDataNotAvailable();
    }

    interface ResponseCallback {
        void onComplete();

        void onFailure();
    }

    void add(@NonNull T data, @NonNull ResponseCallback callback);

    void add(@NonNull Iterable<T> datas, @NonNull ResponseCallback callback);

    void update(@NonNull T data, @NonNull ResponseCallback callback);

    void remove(@NonNull T data, @NonNull ResponseCallback callback);

    void query(@NonNull GetDataCallback<T> callback);

    void query(@NonNull Specification specification, @NonNull GetDataCallback<T> callback);
}
