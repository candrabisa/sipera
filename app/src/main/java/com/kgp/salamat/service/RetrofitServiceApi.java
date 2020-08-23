package com.kgp.salamat.service;

import com.kgp.salamat.api.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceApi {
    private static final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build();
    public static Retrofit getRetrofitService(){
        return new Retrofit.Builder()
                .baseUrl(Api.BASE_URL_RELAWAN)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();
    }
}
