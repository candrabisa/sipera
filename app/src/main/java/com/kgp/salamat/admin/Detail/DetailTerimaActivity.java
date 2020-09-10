package com.kgp.salamat.admin.Detail;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.ybq.android.spinkit.SpinKitView;
import com.kgp.salamat.R;
import com.kgp.salamat.admin.Adapter.CalRelAdapter;
import com.kgp.salamat.admin.Adapter.PaslonAdapter;
import com.kgp.salamat.admin.Helper.RequestHAndler;
import com.kgp.salamat.admin.Model.CalRelModel;
import com.kgp.salamat.admin.Service.URL;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kgp.salamat.admin.Adapter.CalRelAdapter.DATACALON;
import static com.kgp.salamat.admin.Adapter.PaslonAdapter.DATA_PASLON;

public class DetailTerimaActivity extends AppCompatActivity {
    CalRelModel listcalon;
   private EditText nik,nama,alamat,hp,email;
   String idcalrel;
   String lv = "relawan";
   TextView tps;
   //SpinKitView loading;
   ProgressDialog loading;
    private static final String TAG = "DetailTerimaActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_terima);
        getSupportActionBar().setTitle("Detail Calon Relawan");
        nik = findViewById(R.id.nikdetterima);
        nama = findViewById(R.id.idnamadetter);
        alamat = findViewById(R.id.idalamatdetter);
        hp = findViewById(R.id.idteldetter);
        tps = findViewById(R.id.idtpsdetter);
        email = findViewById(R.id.idemaildetter);
      //  loading = findViewById(R.id.spin_kit);
        Bundle bundle=getIntent().getBundleExtra(CalRelAdapter.DATAjEXTRA);
        listcalon = Parcels.unwrap(bundle.getParcelable(DATACALON));
        idcalrel = listcalon.getId_daftar();
        nik.setText(listcalon.getNik());
        nama.setText(listcalon.getNama_lengkap());
        alamat.setText(listcalon.getAlamat());
        hp.setText(listcalon.getNo_hp());
        tps.setText(listcalon.getTps());
        email.setText(listcalon.getEmail());


    }
    private void Terima(){
        String nikk = listcalon.getNik();
        String namaa = listcalon.getNama_lengkap();
        String alamatt = listcalon.getAlamat();
        String hpp = listcalon.getNo_hp();
        String tpss = listcalon.getTps();
        String emaill = listcalon.getEmail();
        loading = new ProgressDialog(DetailTerimaActivity.this);
        loading.setCancelable(false);
        loading.setMessage("Menghubungi Server ...");
        loading.show();
        StringRequest strreq= new StringRequest(Request.Method.POST, URL.addrelawan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse add: "+response);
                loading.dismiss();
               // Toast.makeText(DetailTerimaActivity.this, namaa +" Diterima sebagai relawan", Toast.LENGTH_SHORT).show();
                Terima1();
                TolakCalrel();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "eror : "+error);
                Toast.makeText(DetailTerimaActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                loading.dismiss();
                // if(isActivityActive) Utils.errorResponse(context, error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nik", nikk);
                params.put("nama_lengkap",namaa );
                params.put("alamat", alamatt);
                params.put("no_hp", hpp);
                params.put("email", emaill);
                params.put("url_image", "");
                params.put("tps", tpss);
                Log.d(TAG, "param: "+params);
                return params;
            }
        };
        RequestHAndler.getInstance(DetailTerimaActivity.this).addToRequestQueue(strreq);
    }

    private void Terima1(){
        String level = lv;
        String namaa = listcalon.getNama_lengkap();
        String emaill = listcalon.getEmail();
        String pass = listcalon.getPass();
        String tps = listcalon.getTps();
        loading = new ProgressDialog(DetailTerimaActivity.this);
        loading.setCancelable(false);
        loading.setMessage("Menghubungi Server ...");
        loading.show();
        StringRequest strreq= new StringRequest(Request.Method.POST, URL.mlebuad, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse add: "+response);
                loading.dismiss();
                Toast.makeText(DetailTerimaActivity.this, namaa +" Diterima sebagai relawan", Toast.LENGTH_SHORT).show();
                TolakCalrel();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "eror : "+error);
              //  Toast.makeText(DetailTerimaActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                loading.dismiss();
                // if(isActivityActive) Utils.errorResponse(context, error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nama_lengkap", namaa);
                params.put("username",emaill );
                params.put("password", pass);
                params.put("level", lv);
                params.put("tps", tps);
                Log.d(TAG, "param: "+params);
                return params;
            }
        };
        RequestHAndler.getInstance(DetailTerimaActivity.this).addToRequestQueue(strreq);
    }

    private void TolakCalrel(){
       //loading.setVisibility(View.VISIBLE);
        loading = new ProgressDialog(DetailTerimaActivity.this);
        loading.setCancelable(false);
        loading.setMessage("Menghubungi Server ...");
        loading.show();
       String namaa = listcalon.getNama_lengkap();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL.tolakcalrel, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // loading.setVisibility(View.GONE);
                loading.dismiss();
               // Toast.makeText(DetailTerimaActivity.this, namaa+" Ditolak", Toast.LENGTH_SHORT).show();
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  loading.setVisibility(View.GONE);
                loading.dismiss();
                Toast.makeText(DetailTerimaActivity.this, "Tidak terhubung ke server", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_daftar", idcalrel);

                //   Log.d(TAG, "getParams: "+params);
                return params;
            }
        };
        RequestHAndler.getInstance(DetailTerimaActivity.this).addToRequestQueue(stringRequest);
    }

    public void Tolakcalrel(View view) {
        TolakCalrel();
    }

    public void TERIMA(View view) {
        Terima();
    }
}