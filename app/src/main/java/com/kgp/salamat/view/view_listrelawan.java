package com.kgp.salamat.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kgp.salamat.api.ApiService;
import com.kgp.salamat.model.ResponseListRelawan;
import com.kgp.salamat.service.RetrofitServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class view_listrelawan extends ViewModel {
    private final MutableLiveData<ResponseListRelawan> liveData = new MutableLiveData<>();

    public void setListRelawanData(){
        Retrofit retrofit = RetrofitServiceApi.getRetrofitService();
        ApiService endpoint = retrofit.create(ApiService.class);
        Call<ResponseListRelawan> call = endpoint.getListRelawan();
        call.enqueue(new Callback<ResponseListRelawan>() {
            @Override
            public void onResponse(Call<ResponseListRelawan> call, Response<ResponseListRelawan> response) {
                liveData.setValue((ResponseListRelawan) response.body());
            }

            @Override
            public void onFailure(Call<ResponseListRelawan> call, Throwable t) {

            }

        });
    }
    public LiveData<ResponseListRelawan> getListRelawanData(){
        return liveData;
    }
}
