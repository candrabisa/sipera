package com.kgp.salamat.relawan;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kgp.salamat.R;
import com.kgp.salamat.adapter.AdapterRelawan;
import com.kgp.salamat.api.ApiService;
import com.kgp.salamat.model.ResponseListRelawan;
import com.kgp.salamat.service.RetrofitServiceApi;
import com.kgp.salamat.view.view_listrelawan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListRelawan extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private AdapterRelawan adapterRelawan;
    private SwipeRefreshLayout swipeRefreshRelawan;
    private TextView tvEmpty;
    private static final String TAG = "ListRelawan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relawan_listrelawan);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Daftar Relawan");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        tvEmpty = findViewById(R.id.tvEmptyListRelawan);
        RecyclerView recyclerView = findViewById(R.id.relawan_listRelawan);
      //  adapterRelawan = new AdapterRelawan(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterRelawan);

        swipeRefreshRelawan = findViewById(R.id.swipeRefreshListRelawan);
        swipeRefreshRelawan.setOnRefreshListener(this);

      loadRelawanData();
       // ambilData();

    }
    private void loadRelawanData(){
        view_listrelawan viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(view_listrelawan.class);
        viewModel.setListRelawanData();
        refreshing(true);
        viewModel.getListRelawanData().observe( this, new Observer<ResponseListRelawan>() {
            @Override
            public void onChanged(ResponseListRelawan responseListRelawan) {
                if (responseListRelawan == null){
                    tvEmpty.setVisibility(View.VISIBLE);
                    refreshing(false);
                } else {

                    refreshing(false);
                }
            }

        });
    }
    private void ambilData() {

        ApiService request = RetrofitServiceApi.getRetrofitService().create(ApiService.class);

        Call<ResponseListRelawan> tampilData = request.getListRelawan();
        tampilData.enqueue(new Callback<ResponseListRelawan>() {

            @Override
            public void onResponse(Call<ResponseListRelawan> call, Response<ResponseListRelawan> response) {
                Log.d(TAG, "onResponse: "+response);
            }

            @Override
            public void onFailure(Call<ResponseListRelawan> call, Throwable t) {

            }
        });

    }

    private void refreshing(boolean b) {
        if (b){
            swipeRefreshRelawan.setRefreshing(true);
        } else {
            swipeRefreshRelawan.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
       loadRelawanData();
    }
}
