package com.zeallat.prndtest.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zeallat.prndtest.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit;
    private static Api INSTANCE;
    private static final String BASE_API_URL = "http://recruit.heydealer.com";

    public static Api getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Api();
        }
        return INSTANCE;
    }

    private Api() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder().create();

            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(10, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(interceptor);
            }

            OkHttpClient client = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
    }
}
