package com.kgp.salamat.admin.Detail;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kgp.salamat.R;
import com.kgp.salamat.admin.Adapter.TpsAdapter;
import com.kgp.salamat.admin.AdminActivity;
import com.kgp.salamat.admin.Helper.RequestHAndler;
import com.kgp.salamat.admin.Model.TpsItem;
import com.kgp.salamat.admin.Service.URL;

import org.parceler.Parcels;

import java.util.HashMap;
import java.util.Map;

import static com.kgp.salamat.admin.Adapter.TpsAdapter.DATA_TPS;

public class DetailTpsActivity extends AppCompatActivity {
private TpsItem listtps;
private EditText nama_tps;
private TextView alamattps;
ProgressDialog loding;
String idtps;
    private static final String TAG = "DetailTpsActivity";
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tps);
        nama_tps = findViewById(R.id.detailnamatps_admin);
        alamattps = findViewById(R.id.id_alamatdetailtps);
        getSupportActionBar().setTitle("Detail TPS");


        Bundle bundle=getIntent().getBundleExtra(TpsAdapter.DATA_EXTRA);
        listtps = Parcels.unwrap(bundle.getParcelable(DATA_TPS));

        nama_tps.setText(listtps.getNamaTps());
        alamattps.setText(listtps.getAlamatTps());
        idtps = listtps.getIdTps();



    }
    private void updatetpsnya() {
        loding = new ProgressDialog(DetailTpsActivity.this);
        loding.setCancelable(false);
        loding.setMessage("Sedang Menyimpan ...");
        loding.show();
        StringRequest stringRequest= new StringRequest(Request.Method.PUT, URL.updatetps, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse add: "+response);
                loding.dismiss();
                Toast.makeText(DetailTpsActivity.this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailTpsActivity.this, AdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                DetailTpsActivity.this.finish();
                finish();
            }
        }, new Response.ErrorListener() {
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
                params.put("id_tps", idtps);
                params.put("nama_tps", name);

                Log.d(TAG, "getParams: "+params);
                return params;
            }
        };
        RequestHAndler.getInstance(DetailTpsActivity.this).addToRequestQueue(stringRequest);
    }

    private void Buangsajagaperlu() {
        loding = new ProgressDialog(DetailTpsActivity.this);
        loding.setCancelable(false);
        loding.setMessage("Sedang Menghapus ...");
        loding.show();

        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL.buangtps, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse add: "+response);
                loding.dismiss();
                Toast.makeText(DetailTpsActivity.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(DetailTpsActivity.this, AdminActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                DetailTpsActivity.this.finish();
                finish();
            }
        }, new Response.ErrorListener() {
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
                params.put("id_tps", idtps);

                Log.d(TAG, "getParams: "+params);
                return params;
            }
        };
        RequestHAndler.getInstance(DetailTpsActivity.this).addToRequestQueue(stringRequest);
    }


    public void DeleteTPS(View view) {
alertPeringat();
    }

    public void UpdateTps(View view) {
        try {
           name = nama_tps.getText().toString();
        }catch (Exception e){
            name = "";
        }
        if(name.equals("")){
            nama_tps.setError("Tidak Boleh Kosong");
        }else{
            updatetpsnya();
        }
    }





    private void alertPeringat(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailTpsActivity.this);
        builder.setTitle("Hapus TPS")
                .setMessage("Hapus data ini?")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Buangsajagaperlu();
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}