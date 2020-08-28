package com.kgp.salamat.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.kgp.salamat.LoginActivity;
import com.kgp.salamat.model.RelawanItem;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_preff";
    public static final String sharedprefren ="*hjshduundl*ldllkk";
    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }


    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public RelawanItem getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(sharedprefren, Context.MODE_PRIVATE);
        return new RelawanItem(
                sharedPreferences.getString("idRelawan", null),
                sharedPreferences.getString("nik", null),
                sharedPreferences.getString("namaLengkap", null),
                sharedPreferences.getString("alamat", null),
                sharedPreferences.getString("noHp", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("tps", null)
        );
    }
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(sharedprefren, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("idRelawan", -1) != -1;
    }
}
