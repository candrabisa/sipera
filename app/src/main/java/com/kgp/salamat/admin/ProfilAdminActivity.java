package com.kgp.salamat.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kgp.salamat.R;
import com.kgp.salamat.admin.Add.UbahProfileActivity;
import com.kgp.salamat.admin.Detail.DetailTpsActivity;

public class ProfilAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_admin);
    }

    public void UbahProfileAdmin(View view) {
        startActivity(new Intent(ProfilAdminActivity.this, UbahProfileActivity.class));
    }
}