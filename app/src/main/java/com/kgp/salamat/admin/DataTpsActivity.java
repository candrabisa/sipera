package com.kgp.salamat.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kgp.salamat.R;
import com.kgp.salamat.admin.Adapter.TpsAdapter;
import com.kgp.salamat.admin.Add.AddTpsActivity;
import com.kgp.salamat.admin.Helper.RetroConfig;
import com.kgp.salamat.admin.Model.ResponseTpsAdmin;
import com.kgp.salamat.admin.Model.TpsItem;
import com.kgp.salamat.admin.Service.AdminService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataTpsActivity extends AppCompatActivity {
    private RecyclerView rvTps;
    private List<TpsItem> listtps=new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private static final String TAG = "DataTpsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_tps);
        rvTps=findViewById(R.id.rctpsadmin);

//        TpsItem baru =new TpsItem();
//        baru.setIdTps("1");
//        baru.setNamaTps("Aku");
//        baru.setAlamatTps("Cinta kamu");
//        listtps.add(baru);
        jumuttps();

        rvTps.setAdapter(new TpsAdapter(DataTpsActivity.this,listtps));
        rvTps.setLayoutManager(new LinearLayoutManager(DataTpsActivity.this));
    }

    public void AddDataTps(View view) {
        startActivity(new Intent(DataTpsActivity.this, AddTpsActivity.class));
    }

    private void jumuttps() {
        final ProgressDialog loding = new ProgressDialog(DataTpsActivity.this);
        loding.setMessage("Tunggu Sebentar...");
        loding.setCancelable(false);
        loding.show();

        AdminService request = RetroConfig.getRetrofitService().create(AdminService.class);

        Call<ResponseTpsAdmin> tampilData = request.getTpsAdmin();
        tampilData.enqueue(new Callback<ResponseTpsAdmin>() {


            @Override
            public void onResponse(Call<ResponseTpsAdmin> call, Response<ResponseTpsAdmin> response) {
                Log.d(TAG, "onResponse: "+response);
                listtps = response.body().getTps();
                rvTps.setAdapter(new TpsAdapter(DataTpsActivity.this,listtps));
                rvTps.setLayoutManager(new LinearLayoutManager(DataTpsActivity.this));
                loding.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseTpsAdmin> call, Throwable t) {

            }
        });

    }
}