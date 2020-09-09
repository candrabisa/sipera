package com.kgp.salamat.admin.Service;

import com.kgp.salamat.admin.Model.ResponseRelawanAdmin;
import com.kgp.salamat.admin.Model.ResponseTpsAdmin;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AdminService {
    @GET(URL.TPS)
    Call<ResponseTpsAdmin> getTpsAdmin();

    @GET(URL.getrelawan)
    Call<ResponseRelawanAdmin> getrelawan();

}
