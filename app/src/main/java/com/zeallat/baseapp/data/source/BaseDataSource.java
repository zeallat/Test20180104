package com.zeallat.baseapp.data.source;

import android.support.annotation.NonNull;

import com.zeallat.baseapp.data.model.BaseModel;

import java.util.List;
import java.util.Map;

/**
 * Created by HoJunLee on 2017-11-15.
 */

public interface BaseDataSource<T extends BaseModel> {
    interface GetDataListCallback<T> {
        void onDataListLoaded(List<T> datas);

        void onDataNotAvailable();
    }

    interface GetDataCallback<T> {
        void onDataLoaded(T data);

        void onDataNotAvailable();
    }

    interface ResponseCallback {
        void onComplete();

        void onFailure();
    }

    String getPath();

    void getList(@NonNull String groupId, boolean isRequestRealtime, @NonNull GetDataListCallback<T> callback);

    void getList(boolean isRequestRealtime, @NonNull GetDataListCallback<T> callback);

    void getList(@NonNull GetDataListCallback<T> callback);

    void getData(@NonNull String id, boolean isRequestRealtime, @NonNull GetDataCallback<T> callback);

    void getData(@NonNull String id, @NonNull GetDataCallback<T> callback);

    void create(@NonNull T data, @NonNull GetDataCallback<T> callback);

    void create(@NonNull String id, @NonNull T data, @NonNull GetDataCallback<T> callback);

    void update(@NonNull String id, @NonNull Map<String, Object> updates, @NonNull GetDataCallback<T> callback);

    void delete(@NonNull String id, @NonNull ResponseCallback callback);

    void removeEventListeners();
}
