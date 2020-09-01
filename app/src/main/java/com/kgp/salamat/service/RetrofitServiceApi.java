package com.kgp.salamat.service;

import com.kgp.salamat.api.Api;
import com.kgp.salamat.api.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceApi {
    private Retrofit retrofit;
    private static RetrofitServiceApi mInstance;

    private static final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();
    public static Retrofit getRetrofitService(){
        return new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();
    }

    public static synchronized RetrofitServiceApi getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitServiceApi();
        }
        return mInstance;
    }

    public static ApiService getApi(){
        return getRetrofitService().create(ApiService.class);
    }
}
