package com.kgp.salamat.admin.Detail;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kgp.salamat.R;
import com.kgp.salamat.admin.Adapter.PaslonAdapter;
import com.kgp.salamat.admin.Adapter.RelAdapter;
import com.kgp.salamat.admin.Helper.RequestHAndler;
import com.kgp.salamat.admin.ListRelawanActivity;
import com.kgp.salamat.admin.Model.RelawanItem;
import com.kgp.salamat.admin.Service.URL;

import org.parceler.Parcels;

import java.util.HashMap;
import java.util.Map;

import static com.kgp.salamat.admin.Adapter.PaslonAdapter.DATA_PASLON;
import static com.kgp.salamat.admin.Adapter.RelAdapter.DATA_REL;

public class DetailListRelawanActivity extends AppCompatActivity {
private RelawanItem listdata;
private EditText nik,nama,alamat,email,nohp;
private TextView tekstps;
private Button btnhpus;
private ProgressDialog loding;
String id;
    private static final String TAG = "DetailListRelawanActivi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_relawan);
        getSupportActionBar().setTitle("Detail Relawan");
        nik = findViewById(R.id.detlisrelnik);
        btnhpus = findViewById(R.id.btnhapusrelawan);
        nama = findViewById(R.id.detlistrelnama);
        alamat = findViewById(R.id.detlisalamat);
        email = findViewById(R.id.detlistelemail);
        nohp = findViewById(R.id.detlisrelhp);
        tekstps = findViewById(R.id.txtpsdetailrel);
        Bundle bundle=getIntent().getBundleExtra(RelAdapter.DATA_EXTRA_REL);
        listdata = Parcels.unwrap(bundle.getParcelable(DATA_REL));
        nik.setText(listdata.getNik().toString());
        nama.setText(listdata.getNamaLengkap().toString());
        email.setText(listdata.getEmail().toString());
        nohp.setText(listdata.getNoHp().toString());
        alamat.setText(listdata.getAlamat().toString());
        tekstps.setText(listdata.getTps().toString());
        id=listdata.getIdRelawan();
        hapus();
    }

    private void Hapsrelawan() {
        loding = new ProgressDialog(DetailListRelawanActivity.this);
        loding.setCancelable(false);
        loding.setMessage("Sedang Menghapus ...");
        loding.show();

        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL.relawanhapus, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse add: "+response);
                loding.dismiss();
                Toast.makeText(DetailListRelawanActivity.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "eror : "+error);
                loding.dismiss();
                // if(isActivityActive) Utils.errorResponse(context, error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_relawan", id);

                Log.d(TAG, "getParams: "+params);
                return params;
            }
        };
        RequestHAndler.getInstance(DetailListRelawanActivity.this).addToRequestQueue(stringRequest);
    }
    private void hapus(){
        btnhpus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hapsrelawan();
            }
        });
    }
}