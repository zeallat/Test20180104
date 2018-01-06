package com.zeallat.prndtest.data.network;

import com.zeallat.prndtest.data.model.PaginationInfo;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public abstract class ApiCallback<T> implements Callback<T> {

    public ApiCallback() {
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.body() != null) {
            PaginationInfo paginationInfo = null;
            okhttp3.Headers headers = response.headers();

            String link = headers.get("Link");
            String paginationPageSize = headers.get("X-Pagination-Page-Size");
            String paginationCount = headers.get("X-Pagination-Count");
            if (!isEmpty(link) && !isEmpty(paginationPageSize) && !isEmpty(paginationCount)) {
                paginationInfo = new PaginationInfo(link, Integer.parseInt(paginationCount), Integer.parseInt(paginationPageSize));
            }

            if (response.code() == HttpURLConnection.HTTP_OK) {
                onSuccess(call, response, response.body(), paginationInfo);
            } else {
                onError(call, response);
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
    }

    public void onSuccess(Call<T> call, Response<T> response, T data, PaginationInfo paginationInfo) {

    }

    public void onError(Call<T> call, Response<T> response) {

    }
}