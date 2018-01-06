package com.zeallat.prndtest.data.source;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by HoJunLee on 2018-01-05.
 */

public abstract class BaseRepository<T, RS extends BaseDataSource<T>> implements BaseDataSource<T> {

    private final RS mRemoteDataSource;

    public BaseRepository(@NonNull RS remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public void add(@NonNull T data, @NonNull ResponseCallback callback) {
        checkNotNull(callback);
        mRemoteDataSource.add(data, callback);
    }

    @Override
    public void add(@NonNull Iterable<T> datas, @NonNull ResponseCallback callback) {
        checkNotNull(callback);
        mRemoteDataSource.add(datas, callback);
    }

    @Override
    public void update(@NonNull T data, @NonNull ResponseCallback callback) {
        checkNotNull(callback);
        mRemoteDataSource.update(data, callback);
    }

    @Override
    public void remove(@NonNull T data, @NonNull ResponseCallback callback) {
        checkNotNull(callback);
        mRemoteDataSource.remove(data, callback);
    }

    @Override
    public void query(Specification specification, @NonNull GetDataCallback<T> callback) {
        checkNotNull(callback);
        mRemoteDataSource.query(specification, callback);
    }
}
