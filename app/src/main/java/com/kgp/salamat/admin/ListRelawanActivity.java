package com.kgp.salamat.admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kgp.salamat.R;
import com.kgp.salamat.admin.Adapter.RelAdapter;
import com.kgp.salamat.admin.Adapter.TpsAdapter;
import com.kgp.salamat.admin.Detail.DetailTpsActivity;
import com.kgp.salamat.admin.Helper.RequestHAndler;
import com.kgp.salamat.admin.Helper.RetroConfig;
import com.kgp.salamat.admin.Model.RelawanItem;
import com.kgp.salamat.admin.Model.ResponseRelawanAdmin;
import com.kgp.salamat.admin.Model.TpsItem;
import com.kgp.salamat.admin.Service.AdminService;
import com.kgp.salamat.admin.Service.URL;
import com.kgp.salamat.relawan.ListRelawan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRelawanActivity extends AppCompatActivity {
private List<RelawanItem>listrelawan = new ArrayList<>();
private RecyclerView recyclerView;
private SwipeRefreshLayout swipeRefreshLayout;

    private static final String TAG = "ListRelawanActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_relawan);
        recyclerView = findViewById(R.id.idrcreladmin);

        swipeRefreshLayout = findViewById(R.id.swipepunyarel);
        ketikadiswipe();

       
    }






    private void ambilrelawan() {
        final ProgressDialog loding = new ProgressDialog(ListRelawanActivity.this);
        loding.setMessage("Tunggu Sebentar...");
        loding.setCancelable(false);
        loding.show();

        AdminService request = RetroConfig.getRetrofitService().create(AdminService.class);

        Call<ResponseRelawanAdmin> tampilData = request.getrelawan();
        tampilData.enqueue(new Callback<ResponseRelawanAdmin>() {


            @Override
            public void onResponse(Call<ResponseRelawanAdmin> call, Response<ResponseRelawanAdmin> response) {
               listrelawan.clear();
                listrelawan = response.body().getRelawan();
                recyclerView.setAdapter(new RelAdapter(ListRelawanActivity.this,listrelawan));
                recyclerView.setLayoutManager(new LinearLayoutManager(ListRelawanActivity.this));
                loding.dismiss();
                
            }

            @Override
            public void onFailure(Call<ResponseRelawanAdmin> call, Throwable t) {
                Toast.makeText(ListRelawanActivity.this, "Tidak terhubung ke server", Toast.LENGTH_SHORT).show();
                loding.dismiss();
                listrelawan.clear();
            }
        });

    }
    private void ketikadiswipe(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                ambilrelawan();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private List<RelawanItem> fiterData(List<RelawanItem> catatansaya, String newQuery) {
        String lowercase=newQuery.toLowerCase();
        List<RelawanItem> filterData=new ArrayList<>();
        for (int i = 0; i < catatansaya.size(); i++) {
            String text=catatansaya.get(i).getNamaLengkap().toLowerCase();
            String tanggal=catatansaya.get(i).getNik().toLowerCase();
            String tps=catatansaya.get(i).getTps().toLowerCase();
            String alamat=catatansaya.get(i).getAlamat().toLowerCase();
            if(text.contains(lowercase)||tanggal.contains(lowercase) ||tps.contains(lowercase)||alamat.contains(lowercase)){
                filterData.add(catatansaya.get(i));
            }
        }
        return filterData;
    };

    @Override
    protected void onResume() {
        super.onResume();
        ambilrelawan();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navcaritps,menu);
        MenuItem item = menu.findItem(R.id.navcaritps);
        getSupportActionBar().setTitle("Data Relawan    ");
        android.widget.SearchView searchView = (android.widget.SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                List<RelawanItem>filtercatatan=fiterData(listrelawan, s);
                recyclerView.setAdapter(new RelAdapter(ListRelawanActivity.this,filtercatatan));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<RelawanItem>filtercatatan=fiterData(listrelawan, s);
                recyclerView.setAdapter(new RelAdapter(ListRelawanActivity.this,filtercatatan));
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}