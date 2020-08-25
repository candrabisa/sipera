package com.kgp.salamat.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kgp.salamat.LoginActivity;
import com.kgp.salamat.R;
import com.kgp.salamat.admin.Add.UbahProfileActivity;
import com.kgp.salamat.admin.Detail.DetailTpsActivity;

import static com.kgp.salamat.LoginActivity.sharedprefren;

public class ProfilAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_admin);
    }

    public void UbahProfileAdmin(View view) {
        startActivity(new Intent(ProfilAdminActivity.this, UbahProfileActivity.class));
    }

    public void Keluar(View view) {
        getSharedPreferences(sharedprefren, 0).edit().clear().commit();
        Intent intent = new Intent(ProfilAdminActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        ProfilAdminActivity.this.finish();
        finish();
    }
}