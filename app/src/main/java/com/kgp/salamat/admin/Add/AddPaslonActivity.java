package com.kgp.salamat.admin.Add;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kgp.salamat.R;
import com.kgp.salamat.admin.AdminActivity;
import com.kgp.salamat.admin.Helper.ReqPasLon;
import com.kgp.salamat.admin.Helper.RequestHAndler;
import com.kgp.salamat.admin.Service.URL;

import java.util.HashMap;
import java.util.Map;

public class AddPaslonActivity extends AppCompatActivity {
ProgressDialog loding;
    private static final String TAG = "AddPaslonActivity";
    String no,nama;
    EditText txno,txnama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paslon);
        txnama=findViewById(R.id.id_nama_paslon_admin);
        txno=findViewById(R.id.id_no_paslon_admin);
        getSupportActionBar().setTitle("Tambah Paslon");
    }

    private void addpalon() {
        loding = new ProgressDialog(AddPaslonActivity.this);
        loding.setCancelable(false);
        loding.setMessage("Menambah data ...");
        loding.show();
        StringRequest req= new StringRequest(Request.Method.POST, URL.addpaslon, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse add: "+response);
                loding.dismiss();
                Toast.makeText(AddPaslonActivity.this, "Berhasil di tambahkan", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "eror : "+error);
                loding.dismiss();
                Toast.makeText(AddPaslonActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                // if(isActivityActive) Utils.errorResponse(context, error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("no_paslon", no);
                params.put("nama_paslon",nama );
                Log.d(TAG, "getParams: "+params);
                return params;
            }
        };
        ReqPasLon.getInstance(AddPaslonActivity.this).addToRequestQueue(req);

    }

    public void Tambahpalon(View view) {
        no = txno.getText().toString();
        nama = txnama.getText().toString();
        if(no.equals("")){
            txno.setError("Tidak boleh kosong");
        }else if(nama.equals("")){
            txnama.setError("Tidak boleh kosong");
        }else{
            addpalon();
        }

    }
}