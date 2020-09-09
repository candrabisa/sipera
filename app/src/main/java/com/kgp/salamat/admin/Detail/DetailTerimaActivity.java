package com.kgp.salamat.admin.Detail;

import android.app.ProgressDialog;
import android.os.Bundle;
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
   TextView tps;
   //SpinKitView loading;
   ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_terima);
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
                Toast.makeText(DetailTerimaActivity.this, namaa+" Ditolak", Toast.LENGTH_SHORT).show();
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
}