package com.kgp.salamat.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kgp.salamat.R;
import com.kgp.salamat.admin.Add.AddTpsActivity;

public class DataTpsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_tps);
    }

    public void AddDataTps(View view) {
        startActivity(new Intent(DataTpsActivity.this, AddTpsActivity.class));
    }
}