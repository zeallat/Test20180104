package com.zeallat.prndtest.data.source;

import android.support.annotation.NonNull;

import com.zeallat.prndtest.data.model.BaseModel;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by HoJunLee on 2017-11-15.
 */

public abstract class BaseRepository<T extends BaseModel, RS extends BaseDataSource<T>> implements BaseDataSource<T> {

    private final RS mRemoteDataSource;

    BaseRepository(@NonNull RS remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public void getList(@NonNull String groupId, boolean isRequestRealtime, @NonNull GetDataListCallback<T> callback) {
        checkNotNull(callback);
        mRemoteDataSource.getList(groupId, isRequestRealtime, callback);
    }

    @Override
    public void getList(boolean isRequestRealtime, @NonNull GetDataListCallback<T> callback) {
        checkNotNull(callback);
        mRemoteDataSource.getList(isRequestRealtime, callback);
    }

    @Override
    public void getList(@NonNull GetDataListCallback<T> callback) {
        checkNotNull(callback);
        mRemoteDataSource.getList(callback);
    }

    @Override
    public void getData(@NonNull String id, boolean isRequestRealtime, @NonNull GetDataCallback<T> callback) {
        checkNotNull(callback);
        mRemoteDataSource.getData(id, isRequestRealtime, callback);
    }

    @Override
    public void getData(@NonNull String id, @NonNull GetDataCallback<T> callback) {
        checkNotNull(callback);
        mRemoteDataSource.getData(id, callback);
    }

    @Override
    public void create(@NonNull T data, @NonNull GetDataCallback<T> callback) {
        checkNotNull(callback);
        mRemoteDataSource.create(data, callback);
    }

    @Override
    public void create(@NonNull String id, @NonNull T data, @NonNull GetDataCallback<T> callback) {
        checkNotNull(callback);
        mRemoteDataSource.create(id, data, callback);
    }

    @Override
    public void update(@NonNull String id, @NonNull Map<String, Object> updates, @NonNull GetDataCallback<T> callback) {
        checkNotNull(callback);
        mRemoteDataSource.update(id, updates, callback);
    }

    @Override
    public void delete(@NonNull String id, @NonNull ResponseCallback callback) {
        checkNotNull(callback);
        mRemoteDataSource.delete(id, callback);
    }

    @Override
    public void removeEventListeners() {
        mRemoteDataSource.removeEventListeners();
    }
}
