package com.kgp.salamat.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kgp.salamat.R;
import com.kgp.salamat.admin.Adapter.CalRelAdapter;
import com.kgp.salamat.admin.Adapter.PaslonAdapter;
import com.kgp.salamat.admin.Add.AddPaslonActivity;
import com.kgp.salamat.admin.Helper.RequestHAndler;
import com.kgp.salamat.admin.Model.CalRelModel;
import com.kgp.salamat.admin.Model.ModelPaslon;
import com.kgp.salamat.admin.Service.URL;
import com.kgp.salamat.model.PaslonItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataPaslonActivity extends AppCompatActivity {
    List<ModelPaslon>listpaslon = new ArrayList<>();
    RecyclerView rvpaslon;
    ProgressDialog dialog;
    String id_paslon,nama_paslon,no_paslon,url_image,cdd,udd,hash;
    private static final String TAG = "DataPaslonActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_paslon);
        rvpaslon = findViewById(R.id.id_rcpaslon);
        getSupportActionBar().setTitle("Data Paslon");
    }

    public void AddDataPaslon(View view) {
        startActivity(new Intent(DataPaslonActivity.this, AddPaslonActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getpaslon();
    }

    public void getpaslon(){
        dialog = new ProgressDialog(DataPaslonActivity.this);
        dialog.setMessage("Tunggu Sebentar ...");
        dialog.setCancelable(false);
        dialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.ambilpaslon, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);
                listpaslon.clear();
                try {
                    JSONObject get = new JSONObject(response);
                    JSONArray calrelnya = get.getJSONArray("paslon");
                    String eror = get.getString("error");
                    if(eror.equals("false")){
                        for (int i = 0; i < calrelnya.length(); i++) {
                            JSONObject nilai = calrelnya.getJSONObject(i);
                            id_paslon = nilai.getString("id_paslon");
                            nama_paslon = nilai.getString("nama_paslon");
                            url_image = nilai.getString("url_image");
                           // cdd = nilai.getString("cdd");
                            hash = nilai.getString("hash");
                            url_image = nilai.getString("url_image");
                            no_paslon = nilai.getString("no_paslon");
                            ModelPaslon baru = new ModelPaslon();
                            baru.setNama_paslon(nama_paslon);
                            baru.setNo_paslon(no_paslon);
                            baru.setId_paslon(id_paslon);
                            listpaslon.add(baru);
                        }
                        rvpaslon.setAdapter(new PaslonAdapter(DataPaslonActivity.this,listpaslon));
                        rvpaslon.setLayoutManager(new LinearLayoutManager(DataPaslonActivity.this));
                        dialog.dismiss();
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onResponse: "+e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(DataPaslonActivity.this, "Tidak terhubung ke server", Toast.LENGTH_SHORT).show();
            }
        });
        RequestHAndler.getInstance(DataPaslonActivity.this).addToRequestQueue(stringRequest);
    }
}