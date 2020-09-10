package com.kgp.salamat.admin.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kgp.salamat.R;
import com.kgp.salamat.admin.Adapter.PaslonAdapter;
import com.kgp.salamat.admin.AdminActivity;
import com.kgp.salamat.admin.Helper.RequestHAndler;
import com.kgp.salamat.admin.Model.ModelPaslon;
import com.kgp.salamat.admin.Service.URL;

import org.parceler.Parcels;

import java.util.HashMap;
import java.util.Map;

import static com.kgp.salamat.admin.Adapter.PaslonAdapter.DATA_PASLON;

public class DetailPaslonActivity extends AppCompatActivity {
    ProgressDialog loding;
    private EditText no,nama;
   private String id;
ModelPaslon listpaslon;
    private static final String TAG = "DetailPaslonActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_paslon);
        getSupportActionBar().setTitle("Detail Paslon");
        no = findViewById(R.id.idnomerdetailpaslon);
        nama = findViewById(R.id.idnamapaslondetail);
        Bundle bundle=getIntent().getBundleExtra(PaslonAdapter.DATA_EXSSTRA);
        listpaslon = Parcels.unwrap(bundle.getParcelable(DATA_PASLON));
        id=listpaslon.getId_paslon();
        no.setText(listpaslon.getNo_paslon());
        nama.setText(listpaslon.getNama_paslon());

    }

    public void HapusDetailPaslon(View view) {
        delete();
    }

    private void delete() {
        loding = new ProgressDialog(DetailPaslonActivity.this);
        loding.setCancelable(false);
        loding.setMessage("Sedang Menghapus ...");
        loding.show();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL.deletepaslon, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse add: "+response);
                loding.dismiss();
                Toast.makeText(DetailPaslonActivity.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "eror : "+error);
                loding.dismiss();
                Toast.makeText(DetailPaslonActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                // if(isActivityActive) Utils.errorResponse(context, error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_paslon", id);

                Log.d(TAG, "getParams: "+params);
                return params;
            }
        };
        RequestHAndler.getInstance(DetailPaslonActivity.this).addToRequestQueue(stringRequest);
    }

    public void Updatepaslon(View view) {
        update();
    }
    private void update() {
            String nomer = no.getText().toString();
            String namaa = nama.getText().toString();
            if(nomer.isEmpty()){
                no.setError("No Paslon tidak boleh kosong");
                no.requestFocus();
            }else if(namaa.isEmpty()){
                nama.setError("Nama Paslon tidak boleh kosong");
                nama.requestFocus();
            }else{
                loding = new ProgressDialog(DetailPaslonActivity.this);
                loding.setCancelable(false);
                loding.setMessage("Menghubungi server ...");
                loding.show();
                StringRequest stringRequest= new StringRequest(Request.Method.PUT, URL.updatepaslon, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse add: "+response);
                        loding.dismiss();
                        Toast.makeText(DetailPaslonActivity.this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "eror : "+error);
                        loding.dismiss();
                        Toast.makeText(DetailPaslonActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                        // if(isActivityActive) Utils.errorResponse(context, error);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("id_paslon", id);
                        params.put("no_paslon", nomer);
                        params.put("nama_paslon", namaa);
                        Log.d(TAG, "getParams: "+params);
                        return params;
                    }
                };
                RequestHAndler.getInstance(DetailPaslonActivity.this).addToRequestQueue(stringRequest);
            }

    }
}