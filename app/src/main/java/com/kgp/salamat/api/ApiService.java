package com.kgp.salamat.api;

import com.kgp.salamat.model.ResponseListRelawan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //endpoint list relawan
    @GET(Api.ENDPOINT_LIST_RELAWAN)
    Call<ResponseListRelawan> getListRelawan();
}
