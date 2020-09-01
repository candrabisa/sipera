package com.kgp.salamat.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kgp.salamat.LoginActivity;
import com.kgp.salamat.R;
import com.kgp.salamat.admin.Helper.RequestHAndler;
import com.kgp.salamat.admin.Service.AdminService;
import com.kgp.salamat.admin.Service.URL;
import com.kgp.salamat.api.Api;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminActivity extends AppCompatActivity {
ProgressDialog progressDialog;
TextView jumlahtps;
    private String totaltps;
    private static final String TAG = "AdminActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        jumlahtps=findViewById(R.id.jumlahtps_admin);


    }

    @Override
    protected void onResume() {
        super.onResume();
        JumlahTps();
    }

    public void HasilSuara(View view) {
        startActivity(new Intent(AdminActivity.this,HasilSuaraActivity.class));
    }

    public void TerimaRelawan(View view) {
        startActivity(new Intent(AdminActivity.this,TerimaRelawanActivity.class));
    }

    public void ListRelawan(View view) {
        startActivity(new Intent(AdminActivity.this,ListRelawanActivity.class));
    }

    public void DataPaslon(View view) {
        startActivity(new Intent(AdminActivity.this,DataPaslonActivity.class));
    }

    public void DataTps(View view) {
        startActivity(new Intent(AdminActivity.this,DataTpsActivity.class));
    }

    public void Profile(View view) {
        startActivity(new Intent(AdminActivity.this,ProfilAdminActivity.class));
    }

    private void JumlahTps(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.JUMLAH_TPS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jumut = new JSONObject(response);
                    totaltps = jumut.getString("Total");
                    jumlahtps.setText(totaltps);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminActivity.this, "Terjadi maslaah di server", Toast.LENGTH_SHORT).show();
            }
        }){

        };

        RequestHAndler.getInstance(AdminActivity.this).addToRequestQueue(stringRequest);
    }
}