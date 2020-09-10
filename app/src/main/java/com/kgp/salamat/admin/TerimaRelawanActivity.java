package com.kgp.salamat.admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.ybq.android.spinkit.SpinKitView;
import com.kgp.salamat.R;
import com.kgp.salamat.admin.Adapter.CalRelAdapter;
import com.kgp.salamat.admin.Adapter.TpsAdapter;
import com.kgp.salamat.admin.Helper.RequestHAndler;
import com.kgp.salamat.admin.Model.CalRelModel;
import com.kgp.salamat.admin.Model.TpsItem;
import com.kgp.salamat.admin.Service.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TerimaRelawanActivity extends AppCompatActivity {
    ProgressDialog dialog;
    private SpinKitView loading;
    private RecyclerView rvCalrel;
    private List<CalRelModel> listcalrel=new ArrayList<>();
    private String id,nama,nik,alamat,tps,pass,urlimage,cdd,hash,email,nohp;
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terima_relawan);
        loading = findViewById(R.id.lodingterima);
        swipe = findViewById(R.id.id_swipetrima);
        rvCalrel = findViewById(R.id.itemterimarel);
        getSupportActionBar().setTitle("Terima Relawan");
        ketikaDiSwipe();



    }

    private void ketikaDiSwipe() {
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                // ambilData();
               getcalrel();
                swipe.setRefreshing(false);
            }
        });
    }
    public void getcalrel(){
//        dialog = new ProgressDialog(TerimaRelawanActivity.this);
//        dialog.setMessage("Tunggu Sebentar ...");
//        dialog.setCancelable(false);
//        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.getcalrel, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listcalrel.clear();
                try {
                    JSONObject get = new JSONObject(response);
                    JSONArray calrelnya = get.getJSONArray("calon_relawan");
                    String eror = get.getString("error");
                    if(eror.equals("false")){
                        for (int i = 0; i < calrelnya.length(); i++) {
                            JSONObject nilai = calrelnya.getJSONObject(i);
                            id = nilai.getString("id_daftar");
                            nik = nilai.getString("nik");
                            nama = nilai.getString("nama_lengkap");
                            alamat = nilai.getString("alamat");
                            tps = nilai.getString("tps");
                            pass = nilai.getString("pass");
                            email = nilai.getString("email");
                            nohp = nilai.getString("no_hp");
                            cdd = nilai.getString("cdd");
                            hash = nilai.getString("hash");
                            urlimage = nilai.getString("url_image");
                            CalRelModel baru = new CalRelModel();
                            baru.setNik(nik);
                            baru.setId_daftar(id);
                            baru.setNama_lengkap(nama);
                            baru.setTps(tps);
                            baru.setAlamat(alamat);
                            baru.setNo_hp(nohp);
                            baru.setEmail(email);
                            baru.setPass(pass);
                            listcalrel.add(baru);
                        }
                        rvCalrel.setAdapter(new CalRelAdapter(TerimaRelawanActivity.this,listcalrel));
                        rvCalrel.setLayoutManager(new LinearLayoutManager(TerimaRelawanActivity.this));
//                        dialog.dismiss();
                        loading.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // dialog.dismiss();
                loading.setVisibility(View.GONE);
                Toast.makeText(TerimaRelawanActivity.this, "Tidak terhubung ke server", Toast.LENGTH_SHORT).show();
            }
        });
        RequestHAndler.getInstance(TerimaRelawanActivity.this).addToRequestQueue(stringRequest);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getcalrel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navcaritps,menu);
        MenuItem item = menu.findItem(R.id.navcaritps);
        android.widget.SearchView searchView = (android.widget.SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                List<CalRelModel>filtercatatan=fiterData(listcalrel, s);
                rvCalrel.setAdapter(new CalRelAdapter(TerimaRelawanActivity.this,filtercatatan));
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                List<CalRelModel>filtercatatan=fiterData(listcalrel, s);
                rvCalrel.setAdapter(new CalRelAdapter(TerimaRelawanActivity.this,filtercatatan));
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private List<CalRelModel> fiterData(List<CalRelModel> catatansaya, String newQuery) {
        String lowercase=newQuery.toLowerCase();
        List<CalRelModel>filterData=new ArrayList<>();
        for (int i = 0; i < catatansaya.size(); i++) {
            String nama=catatansaya.get(i).getNama_lengkap().toLowerCase();
            String alamat=catatansaya.get(i).getAlamat().toLowerCase();
            String tps=catatansaya.get(i).getTps().toLowerCase();
            String nik=catatansaya.get(i).getNik().toLowerCase();
            if(nama.contains(lowercase)||alamat.contains(lowercase)||tps.contains(lowercase)||nik.contains(lowercase)){
                filterData.add(catatansaya.get(i));
            }
        }
        return filterData;
    };
}