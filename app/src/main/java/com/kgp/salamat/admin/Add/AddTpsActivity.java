package com.kgp.salamat.admin.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kgp.salamat.LoginActivity;
import com.kgp.salamat.R;
import com.kgp.salamat.admin.AdminActivity;
import com.kgp.salamat.admin.DataTpsActivity;
import com.kgp.salamat.admin.Helper.RequestHAndler;
import com.kgp.salamat.admin.ProfilAdminActivity;
import com.kgp.salamat.admin.Service.URL;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddTpsActivity extends AppCompatActivity {
    private SearchableSpinner spprov, spkab, spkec, spkel;
    private List<String> provinsi = new ArrayList<>();
    private List<String> idprovinsi = new ArrayList<>();
    private List<String> kabupaten = new ArrayList<>();
    private List<String> idkabupaten = new ArrayList<>();
    private List<String> kecamatan = new ArrayList<>();
    private List<String> idkecamatan = new ArrayList<>();
    private List<String> desa = new ArrayList<>();
    private List<String> iddesa = new ArrayList<>();
    ProgressDialog loding;
    String idprofinsi, idkabbupaten, idkeccamatan, iddessa;
    String[] listcoba = {"aku", "sayang"};
    private String token, succes;
    EditText namatps;
    private String namatepes,propin, kab,kec,kel;


    private static final String TAG = "AddTpsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tps);
        spprov = findViewById(R.id.addspprov_admin);
        spkab = findViewById(R.id.addspkab_admin);
        spkec = findViewById(R.id.addspkec_admin);
        spkel = findViewById(R.id.addspkel_admin);
        namatps = findViewById(R.id.namatps_add);
        getSupportActionBar().setTitle("Tambah TPS");
        AmbilToken();

        spprov.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                for (int j = 0; j < provinsi.size(); j++) {
                    if (provinsi.get(i).equals(provinsi.get(j))) {
                        idprofinsi = idprovinsi.get(j);
                    }
                }
                Log.d(TAG, "cek id: " + idprofinsi);
                kabupaten.clear();
                jumutkabupaten();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spkab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                for (int j = 0; j < kabupaten.size(); j++) {
                    if (kabupaten.get(i).equals(kabupaten.get(j))) {
                        idkabbupaten = idkabupaten.get(j);
                        Log.d(TAG, "kabupaten: " + idkabbupaten);

                    }
                }
                Log.d(TAG, "cek id kabupaten: " + idkabbupaten);
                kecamatan.clear();
                jummutkecamatan();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spkec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                for (int j = 0; j < kecamatan.size(); j++) {
                    if (kecamatan.get(i).equals(kecamatan.get(j))) {
                        idkeccamatan = idkecamatan.get(j);
                        Log.d(TAG, "kecamatan: " + idkeccamatan);

                    }
                }
                Log.d(TAG, "cek id kecamatan: " + idkecamatan);
                desa.clear();
                jummutdesa();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter adapter = new ArrayAdapter(AddTpsActivity.this, android.R.layout.simple_spinner_dropdown_item, provinsi);
        spprov.setAdapter(adapter);

    }

    private void tampilspinner(Spinner spinner, List<String> dataalamttps) {
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.io_spinner, dataalamttps);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private void AmbilToken() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.TokenWilayah, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jumut = new JSONObject(response);
                    token = jumut.getString("token");
                    jumutProvinsi();
                    Log.d(TAG, "token: " + token + response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddTpsActivity.this, "Terjadi maslaah di server", Toast.LENGTH_SHORT).show();
            }
        }) {

        };

        RequestHAndler.getInstance(AddTpsActivity.this).addToRequestQueue(stringRequest);
    }

    private void jumutProvinsi() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.Wilayah + token + "/m/wilayah/provinsi", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jumut = new JSONObject(response);
                    Log.d(TAG, "jumut: " + response);
                    succes = jumut.getString("success");
                    JSONArray getprovinsi = jumut.getJSONArray("data");
                    //  JSONObject objprov = new JSONObject("data");
                    //  Log.d(TAG, "objrespon: "+jumut);
                    //  Log.d(TAG, "obj: "+objprov);
                    Log.d(TAG, "data: " + getprovinsi);
                    for (int i = 0; i < getprovinsi.length(); i++) {
                        JSONObject nilai = getprovinsi.getJSONObject(i);
                        String nama = nilai.getString("name");
                        String id = nilai.getString("id");
                        Log.d(TAG, "cekid: " + idprovinsi);
                        //  provinsi.add(nilai.getString("name"));
                        Log.d(TAG, "onResponse: " + nama);
                        idprovinsi.add(id);
                        provinsi.add(nama);
                        tampilspinner(spprov, provinsi);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddTpsActivity.this, "Terjadi maslaah di server", Toast.LENGTH_SHORT).show();
            }
        }) {

        };

        RequestHAndler.getInstance(AddTpsActivity.this).addToRequestQueue(stringRequest);
    }

    private void jumutkabupaten() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.Wilayah + token + "/m/wilayah/kabupaten?idpropinsi=" + idprofinsi, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jumut = new JSONObject(response);
                    Log.d(TAG, "jumut: " + response);
                    succes = jumut.getString("success");
                    JSONArray getprovinsi = jumut.getJSONArray("data");
                    Log.d(TAG, "data: " + getprovinsi);
                    for (int i = 0; i < getprovinsi.length(); i++) {
                        JSONObject nilai = getprovinsi.getJSONObject(i);
                        String nama = nilai.getString("name");
                        String id = nilai.getString("id");
                        kabupaten.add(nama);
                        idkabupaten.add(id);
                        tampilspinner(spkab, kabupaten);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddTpsActivity.this, "Terjadi maslaah di server", Toast.LENGTH_SHORT).show();
            }
        }) {

        };

        RequestHAndler.getInstance(AddTpsActivity.this).addToRequestQueue(stringRequest);
    }

    private void jummutkecamatan() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.Wilayah + token + "/m/wilayah/kecamatan?idkabupaten=" + idkabbupaten, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jumut = new JSONObject(response);
                    Log.d(TAG, "jumut: " + response);
                    succes = jumut.getString("success");
                    JSONArray getprovinsi = jumut.getJSONArray("data");
                    Log.d(TAG, "data: " + getprovinsi);
                    for (int i = 0; i < getprovinsi.length(); i++) {
                        JSONObject nilai = getprovinsi.getJSONObject(i);
                        String nama = nilai.getString("name");
                        String id = nilai.getString("id");
                        kecamatan.add(nama);
                        idkecamatan.add(id);
                        tampilspinner(spkec, kecamatan);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddTpsActivity.this, "Terjadi maslaah di server", Toast.LENGTH_SHORT).show();
            }
        }) {

        };

        RequestHAndler.getInstance(AddTpsActivity.this).addToRequestQueue(stringRequest);
    }

    private void jummutdesa() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.Wilayah + token + "/m/wilayah/kelurahan?idkecamatan=" + idkeccamatan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jumut = new JSONObject(response);
                    Log.d(TAG, "jumut: " + response);
                    succes = jumut.getString("success");
                    JSONArray getprovinsi = jumut.getJSONArray("data");
                    Log.d(TAG, "data: " + getprovinsi);
                    for (int i = 0; i < getprovinsi.length(); i++) {
                        JSONObject nilai = getprovinsi.getJSONObject(i);
                        String nama = nilai.getString("name");
                        String id = nilai.getString("id");
                        desa.add(nama);
                        iddesa.add(id);
                        tampilspinner(spkel, desa);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddTpsActivity.this, "Terjadi maslaah di server", Toast.LENGTH_SHORT).show();
            }
        }) {

        };

        RequestHAndler.getInstance(AddTpsActivity.this).addToRequestQueue(stringRequest);
    }


    private void AddTps() {
        loding = new ProgressDialog(AddTpsActivity.this);
        loding.setCancelable(false);
        loding.setMessage("Menambah data ...");
        loding.show();
            StringRequest stringRequest= new StringRequest(Request.Method.POST, URL.TambahTps, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "onResponse add: "+response);
                    loding.dismiss();
                    Toast.makeText(AddTpsActivity.this, "Berhasil di tambahkan", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "eror : "+error);
                    Toast.makeText(AddTpsActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                    loding.dismiss();
                    // if(isActivityActive) Utils.errorResponse(context, error);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("nama_tps", namatepes);
                    params.put("provinsi",propin );
                    params.put("kabupaten", kab);
                    params.put("kecamatan", kec);
                    params.put("kelurahan", kel);
                    params.put("url_image_tps", "");
                    Log.d(TAG, "getParams: "+params);
                    return params;
                }
            };
            RequestHAndler.getInstance(AddTpsActivity.this).addToRequestQueue(stringRequest);

        }



    public void Send(View view) {
        try {
            namatepes = "TPS "+namatps.getText().toString() +" ";
            propin = spprov.getSelectedItem().toString();
            kab = spkab.getSelectedItem().toString();
            kec = spkec.getSelectedItem().toString();
            kel = spkel.getSelectedItem().toString();
        }catch (Exception e){

        }

        if(namatepes==null){
            namatps.setError("Tidak boleh kosong");
        }else if(propin==null || kab ==null || kec==null  || kel ==null ){
            Toast.makeText(this, "Isi semua dengan benar", Toast.LENGTH_SHORT).show();
        }else {
            AddTps();
        }
    }
}