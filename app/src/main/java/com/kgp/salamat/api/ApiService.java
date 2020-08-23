package com.kgp.salamat.api;

import com.kgp.salamat.model.ModelListRelawan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //endpoint list relawan
    @GET(Api.ENDPOINT_LIST_RELAWAN)
    Call<List<ModelListRelawan>> getListRelawan();
}
