package com.kgp.salamat.api;

import com.kgp.salamat.model.ResponseDaftar;
import com.kgp.salamat.model.ResponseListPaslon;
import com.kgp.salamat.model.ResponseListRelawan;
import com.kgp.salamat.model.ResponseSpinnerTps;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    //endpoint list relawan
    @GET(Api.ENDPOINT_LIST_RELAWAN)
    Call<ResponseListRelawan> getListRelawan();

    //endpoint list paslon
    @GET(Api.ENDPOINT_LIST_PASLON)
    Call<ResponseListPaslon> getListPaslon();

    @GET(Api.ENDPOINT_SPINNER_TPS)
    Call<ResponseSpinnerTps> getspinnertps();

    @FormUrlEncoded
    @POST(Api.ENDPOINT_REGISTER_RELAWAN)
    Call<ResponseDaftar> add(
            @Field("nik") String nik,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("alamat") String alamat,
            @Field("no_hp") String no_hp,
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("tps") String tps
    );

//    @FormUrlEncoded
//    @POST(Api.ENDPOINT_REGISTER_RELAWAN)
//    Call<ResponseRegister> postRegisterRelawan(String nik, String nama_lengkap, String alamat, String no_hp, String email, String pass);

}