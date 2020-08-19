package com.kgp.salamat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
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
import com.kgp.salamat.app.AppController;
import com.kgp.salamat.util.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btn_login;
    TextView tv_register;
    ProgressDialog progressDialog;

    int success;
    ConnectivityManager conMgr;
    private String url = Server.URL + "login.php";

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_EMAIL = "email";
    public final static String TAG_PASSWORD = "password";

    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedPreferences;
    Boolean session = false;
    String email, password;

    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.btn_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentLogin);
                finish();
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

//        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (conMgr.getActiveNetworkInfo() !=null
//                && conMgr.getActiveNetworkInfo().isAvailable()
//                && conMgr.getActiveNetworkInfo().isConnected()){
//        } else{
//            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//        }
//        // cek session login jika True maka langsung kebuka
//        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
//        session = sharedPreferences.getBoolean(session_status, false);
//        email = sharedPreferences.getString(TAG_EMAIL, null);
//        password = sharedPreferences.getString(TAG_PASSWORD, null);
//
//        if (session) {
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            intent.putExtra(TAG_EMAIL, email);
//            intent.putExtra(TAG_PASSWORD, password);
//            startActivity(intent);
//            finish();
//        }
//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = etUsername.getText().toString();
//                String password = etPassword.getText().toString();
//
//                // mengecek kolom kosong
//                if (email.trim().length()>0 && password.trim().length()>0){
//                    if (conMgr.getActiveNetworkInfo() != null
//                    && conMgr.getActiveNetworkInfo().isAvailable()
//                    && conMgr.getActiveNetworkInfo().isConnected()){
//                        checkLogin(email, password);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//                    }
//                } else{
//                    Toast.makeText(getApplicationContext(), "Mohon isi semua kolom", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }
//    private void checkLogin(final String email, final String password){
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loggin in..");
//        showDialog();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e(TAG, "Login Response: " + response.toString());
//                hideDialog();
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    success = jsonObject.getInt(TAG_SUCCESS);
//
//                    //check for error node in json
//                    if (success == 1) {
//                        String email = jsonObject.getString(TAG_EMAIL);
//                        String password = jsonObject.getString(TAG_PASSWORD);
//
//                        Log.e("Sucessfullt Login", jsonObject.toString());
//                        Toast.makeText(getApplicationContext(), jsonObject.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//
//                        // Menyimpan login ke session
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putBoolean(session_status, true);
//                        editor.putString(TAG_EMAIL, email);
//                        editor.putString(TAG_PASSWORD, password);
//                        editor.apply();
//
//                        // Memanggil main activity
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        intent.putExtra(TAG_EMAIL, email);
//                        intent.putExtra(TAG_PASSWORD, password);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Toast.makeText(getApplicationContext(), jsonObject.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException e) {
//                    //json error
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "LOGIN Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                hideDialog();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams(){
//                // posting parameters to login url
//                Map<String, String>params = new HashMap<String, String>();
//                params.put("email", email);
//                params.put("password", password);
//
//                return params;
//            }
//        };
//        // Adding Request to request queue
//        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
//    }
//    private void showDialog(){
//        if (!progressDialog.isShowing()) progressDialog.show();
//    }
//    private void hideDialog(){
//        if (progressDialog.isShowing()) progressDialog.dismiss();
    }
}
