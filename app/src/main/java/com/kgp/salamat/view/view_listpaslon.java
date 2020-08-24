package com.kgp.salamat.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kgp.salamat.api.ApiService;
import com.kgp.salamat.model.ResponseListPaslon;
import com.kgp.salamat.service.RetrofitServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class view_listpaslon extends ViewModel {
    private final MutableLiveData<ResponseListPaslon> liveData = new MutableLiveData<>();

    public void setListPaslonData(){
        Retrofit retrofit = RetrofitServiceApi.getRetrofitService();
        ApiService endpointpaslon = retrofit.create(ApiService.class);
        Call<ResponseListPaslon> call = endpointpaslon.getListPaslon();
        call.enqueue(new Callback<ResponseListPaslon>() {
            @Override
            public void onResponse(Call<ResponseListPaslon> call, Response<ResponseListPaslon> response) {
                liveData.setValue((ResponseListPaslon)response.body());
            }

            @Override
            public void onFailure(Call<ResponseListPaslon> call, Throwable t) {

            }
        });
    }
    public LiveData<ResponseListPaslon> getListPaslonData(){
        return liveData;
    }
}

