package com.kgp.salamat.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kgp.salamat.R;
import com.kgp.salamat.admin.Add.AddPaslonActivity;

public class DataPaslonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_paslon);
    }

    public void AddDataPaslon(View view) {
        startActivity(new Intent(DataPaslonActivity.this, AddPaslonActivity.class));
    }
}