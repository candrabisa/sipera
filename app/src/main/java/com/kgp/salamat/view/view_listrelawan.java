package com.kgp.salamat.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kgp.salamat.api.Api;
import com.kgp.salamat.api.ApiService;
import com.kgp.salamat.model.ModelListRelawan;
import com.kgp.salamat.service.RetrofitServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class view_listrelawan extends ViewModel {
    private final MutableLiveData<ArrayList<ModelListRelawan>> liveData = new MutableLiveData<>();

    public void setListRelawanData(){
        Retrofit retrofit = RetrofitServiceApi.getRetrofitService();
        ApiService endpoint = retrofit.create(ApiService.class);
        Call<List<ModelListRelawan>> call = endpoint.getListRelawan();
        call.enqueue(new Callback<List<ModelListRelawan>>() {
            @Override
            public void onResponse(Call<List<ModelListRelawan>> call, Response<List<ModelListRelawan>> response) {
                liveData.setValue((ArrayList<ModelListRelawan>)response.body());
            }

            @Override
            public void onFailure(Call<List<ModelListRelawan>> call, Throwable t) {

            }
        });
    }
    public LiveData<ArrayList<ModelListRelawan>> getListRelawanData(){
        return liveData;
    }
}
