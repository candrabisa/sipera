package com.kgp.salamat.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.kgp.salamat.R;
import com.kgp.salamat.adapter.AdapterRelawan;
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
    private static final String TAG = "DataTpsActivity";
    private SwipeRefreshLayout swipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_tps);
        rvTps=findViewById(R.id.rctpsadmin);
        swipe=findViewById(R.id.id_swipe);
        ketikaDiSwipe();


    }
    public void AddDataTps(View view) {
        startActivity(new Intent(DataTpsActivity.this, AddTpsActivity.class));
    }
    private void ketikaDiSwipe() {
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                // ambilData();
                jumuttps();
                swipe.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        jumuttps();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navcaritps,menu);
        MenuItem item = menu.findItem(R.id.navcaritps);
        getSupportActionBar().setTitle("Data TPS");
        android.widget.SearchView searchView = (android.widget.SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                List<TpsItem>filtercatatan=fiterData(listtps, s);
                rvTps.setAdapter(new TpsAdapter(DataTpsActivity.this,filtercatatan));
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                List<TpsItem>filtercatatan=fiterData(listtps, s);
                rvTps.setAdapter(new TpsAdapter(DataTpsActivity.this,filtercatatan));
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);



    }
    private List<TpsItem> fiterData(List<TpsItem> catatansaya, String newQuery) {
        String lowercase=newQuery.toLowerCase();
        List<TpsItem>filterData=new ArrayList<>();
        for (int i = 0; i < catatansaya.size(); i++) {
            String text=catatansaya.get(i).getNamaTps().toLowerCase();
            String tanggal=catatansaya.get(i).getAlamatTps().toLowerCase();
            if(text.contains(lowercase)||tanggal.contains(lowercase)){
                filterData.add(catatansaya.get(i));
            }
        }
        return filterData;
    };

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
                listtps.clear();
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