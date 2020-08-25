package com.kgp.salamat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kgp.salamat.api.ApiService;
import com.kgp.salamat.model.ResponseSpinnerTps;
import com.kgp.salamat.model.SpinnerItem;
import com.kgp.salamat.service.RetrofitServiceApi;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    SearchableSpinner spinner;
    Button btnRegister;
    EditText etReg_NIK, etReg_Nama, etReg_Alamat, etReg_NoHP, etReg_Email, etReg_Password;
    TextView tvReg_Login;
    private List<String>listspineralamat = new ArrayList<>();
    private List<SpinnerItem> spinnerItems=new ArrayList<>();
    private static final String TAG = "SignUpActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnRegister = findViewById(R.id.btn_Reg);
        etReg_NIK = findViewById(R.id.etReg_NIK);
        etReg_Nama = findViewById(R.id.etReg_NamaLengkap);
        etReg_Alamat = findViewById(R.id.etReg_Alamat);
        etReg_NoHP = findViewById(R.id.etReg_NoHP);
        etReg_Email= findViewById(R.id.etReg_Email);
        etReg_Password = findViewById(R.id.etReg_Password);
        tvReg_Login = findViewById(R.id.btnReg_Login);
        spinner = findViewById(R.id.ss_TPS);
        ambilspinnertps();

        ArrayAdapter adapter=new ArrayAdapter(SignUpActivity.this,android.R.layout.simple_spinner_dropdown_item,spinnerItems);
        spinner.setAdapter(adapter);

        tvReg_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignin = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intentSignin);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        startActivity(intentLogin);
        finish();
    }

    private void ambilspinnertps() {

        ApiService request = RetrofitServiceApi.getRetrofitService().create(ApiService.class);

        Call<ResponseSpinnerTps> tampilData = request.getspinnertps();
        tampilData.enqueue(new Callback<ResponseSpinnerTps>() {
            @Override
            public void onResponse(Call<ResponseSpinnerTps> call, Response<ResponseSpinnerTps> response) {
            spinnerItems=response.body().getSpinner();
                for (int i = 0; i <spinnerItems.size() ; i++) {
                    listspineralamat.add(spinnerItems.get(i).getAlamatTps().trim());
                }
             tampilspinner(spinner,listspineralamat);
            }

            @Override
            public void onFailure(Call<ResponseSpinnerTps> call, Throwable t) {

            }

        });

    }
    private void tampilspinner(Spinner spinner, List<String>dataalamttps){
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.io_spinner, dataalamttps);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
}
