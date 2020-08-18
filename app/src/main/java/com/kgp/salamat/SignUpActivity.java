package com.kgp.salamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    Button btnRegister;
    EditText etReg_NIK, etReg_Nama, etReg_Alamat, etReg_NoHP, etReg_Email, etReg_Password;
    TextView tvReg_Login;

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
}
