package com.kgp.salamat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kgp.salamat.fragment.fragment_beranda;
import com.kgp.salamat.fragment.fragment_profil;
import com.kgp.salamat.fragment.fragment_relawan;

import static com.kgp.salamat.LoginActivity.KEYUSER;
import static com.kgp.salamat.LoginActivity.useremail;

public class MainActivity extends AppCompatActivity {
    public String usernamerelawan;

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
         usernamerelawan = extras.getString(KEYUSER);
        Log.d(TAG, "cek user: "+usernamerelawan);
        if (savedInstanceState == null){
            fragment_beranda fragmentBeranda = new fragment_beranda();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragmentBeranda)
                    .commit();
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelected);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelected = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.Navigation_Beranda:
                    fragment_beranda fragmentBeranda = new fragment_beranda();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragmentBeranda)
                            .commit();
                    return true;

                case R.id.Navigation_Relawan:
                    fragment_relawan fragmentRelawan = new fragment_relawan();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragmentRelawan)
                            .commit();
                    return true;

                case R.id.Navigation_Profil:
                    fragment_profil fragmentProfil = new fragment_profil();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragmentProfil)
                            .commit();
                    return true;
            }
            return false;
        }
    };
}
