package com.kgp.salamat.service;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kgp.salamat.api.Api;
import com.kgp.salamat.api.ApiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceApi {
    private static final String BASEURL = "http://nakulasadewaindonesia.com/tikep/service/daftar/add/";
    private static Retrofit retrofit;
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
    private RetrofitServiceApi() {
        OkHttpClient okHttpClients = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClients)
                .build();
    }

    public static synchronized RetrofitServiceApi getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitServiceApi();
        }
        return mInstance;
    }

    public ApiService getApi(){
        return retrofit.create(ApiService.class);
    }
}
