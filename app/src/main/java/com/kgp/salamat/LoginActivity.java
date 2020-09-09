package com.kgp.salamat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kgp.salamat.admin.AdminActivity;
import com.kgp.salamat.admin.Helper.RequestHAndler;
import com.kgp.salamat.api.Api;
import com.kgp.salamat.model.RegisterItem;
import com.kgp.salamat.model.RelawanItem;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {


    public static final String KEYUSER = "KEY";
    EditText etUsername, etPassword;
    Button btn_login;
    TextView tv_register;
    String user,pass;
    ProgressDialog progressDialog2;
    public static final String sharedprefren ="*hjshduundl*ldllkk";
    public static String useremail = "@gmail";
    String passss="psjdsojhd*";


    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.btn_register);


        loadDaata();
        if(user.equals(null) || pass.equals(null)){

        }else {

            etUsername.setText(user);
            etPassword.setText(pass);
            mlebet();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mlebet();
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intentRegister);
                finish();
            }
        });


    }


    public void mlebet(){
        user =etUsername.getText().toString();
        pass = etPassword.getText().toString();
        if(user.equals("")){
            etUsername.setError("Username kosong");
        }else if(pass.equals("")) {
            etPassword.setError("Password kosong");
        }else{
            progressDialog2 = new ProgressDialog(LoginActivity.this);
            progressDialog2.setMessage("Tunggu sebentar...");
            progressDialog2.setCancelable(false);
            progressDialog2.show();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.ENDPOINT_LOGIN+"?user="+user+"&password="+pass, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "cekrespon: "+response);
                    try {
                        JSONObject result = new JSONObject(response);
                        String status = result.getString("status");
                        String level = result.getString("level");
                        String username = result.getString("user");
                        Log.d(TAG, "status: "+status);
                        if(status.equals("true")){
                            if(level.equals("admin")){
                                saveData();
                                Intent intentLogin = new Intent(LoginActivity.this, AdminActivity.class);
                                startActivity(intentLogin);
                                finish();

                            }else if(level.equals("relawan")){
                                saveData();
                                Intent intentLogin = new Intent(LoginActivity.this, MainActivity.class);
                                intentLogin.putExtra(KEYUSER,username);
                                startActivity(intentLogin);
                                finish();

                            }
                        }else {
                            Toast.makeText(LoginActivity.this, "Username atau password salah", Toast.LENGTH_SHORT).show();
                            progressDialog2.dismiss();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Username atau password salah", Toast.LENGTH_SHORT).show();
                        progressDialog2.dismiss();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, "Gagal menghubungi server coba beberapa saat lagi", Toast.LENGTH_SHORT).show();
                    progressDialog2.dismiss();
                }
            }){

            };
            RequestHAndler.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
        }

   }
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(sharedprefren,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(useremail,user);
        editor.putString(passss,pass);
        editor.apply();
    }

    public void babad(){
        SharedPreferences preferences = getSharedPreferences(sharedprefren, MODE_PRIVATE);
        preferences.edit().remove(useremail).commit();
        preferences.edit().remove(passss).commit();
    }

    public void loadDaata(){
        SharedPreferences sharedPreferences = getSharedPreferences(sharedprefren,MODE_PRIVATE);
        user = sharedPreferences.getString(useremail,"");
        pass = sharedPreferences.getString(passss,"");

        Log.d(TAG, "cek user:  "+user +pass);

    }

}

