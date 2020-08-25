package com.kgp.salamat.api;

import com.kgp.salamat.model.ResponseListPaslon;
import com.kgp.salamat.model.ResponseListRelawan;
import com.kgp.salamat.model.ResponseSpinnerTps;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //endpoint list relawan
    @GET(Api.ENDPOINT_LIST_RELAWAN)
    Call<ResponseListRelawan> getListRelawan();

    //endpoint list paslon
    @GET(Api.ENDPOINT_LIST_PASLON)
    Call<ResponseListPaslon> getListPaslon();

    @GET(Api.ENDPOINT_SPINNER_TPS)
    Call<ResponseSpinnerTps> getspinnertps();

}
