package com.kgp.salamat.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kgp.salamat.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void HasilSuara(View view) {
        startActivity(new Intent(AdminActivity.this,HasilSuaraActivity.class));
    }

    public void TerimaRelawan(View view) {
        startActivity(new Intent(AdminActivity.this,TerimaRelawanActivity.class));
    }

    public void ListRelawan(View view) {
        startActivity(new Intent(AdminActivity.this,ListRelawanActivity.class));
    }

    public void DataPaslon(View view) {
        startActivity(new Intent(AdminActivity.this,DataPaslonActivity.class));
    }

    public void DataTps(View view) {
        startActivity(new Intent(AdminActivity.this,DataTpsActivity.class));
    }

    public void Profile(View view) {
        startActivity(new Intent(AdminActivity.this,ProfilAdminActivity.class));
    }
}