package com.kgp.salamat.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kgp.salamat.LoginActivity;
import com.kgp.salamat.R;
import com.kgp.salamat.SignUpActivity;
import com.kgp.salamat.adapter.AdapterRelawan;
import com.kgp.salamat.model.RelawanItem;
import com.kgp.salamat.model.ResponseListRelawan;
import com.kgp.salamat.service.RetrofitServiceApi;
import com.kgp.salamat.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;

import static com.kgp.salamat.LoginActivity.sharedprefren;
import static com.kgp.salamat.LoginActivity.useremail;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_profil extends Fragment {

    private AdapterRelawan adapterRelawan;
    private List<RelawanItem> relawanItems;

    public fragment_profil() {
        // Required empty public constructor
    }

    private TextView nama_lengkap;
    private EditText nik, alamat, no_hp, email, pass, tps;
    private ImageView imageView;
    Button btnmetu;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnmetu = view.findViewById(R.id.btn_Logout);
        btnmetu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().getSharedPreferences(sharedprefren, 0).edit().clear().commit();
                Intent intentRegister = new Intent(getContext(), LoginActivity.class);
                startActivity(intentRegister);
                getActivity().finish();

            }
        });

        nik = view.findViewById(R.id.etProfil_nik);
        nama_lengkap = view.findViewById(R.id.tvProfil_NamaRelawan);
        alamat = view.findViewById(R.id.etProfil_Alamat);
        no_hp = view.findViewById(R.id.etProfil_nohp);
        email = view.findViewById(R.id.etProfil_email);

        nik.setText(SharedPrefManager.getInstance(getContext()).getUser().getNik());
        nama_lengkap.setText(SharedPrefManager.getInstance(getContext()).getUser().getNamaLengkap());
        alamat.setText(SharedPrefManager.getInstance(getContext()).getUser().getAlamat());
        no_hp.setText(SharedPrefManager.getInstance(getContext()).getUser().getNoHp());
        email.setText(SharedPrefManager.getInstance(getContext()).getUser().getEmail());

//        Call<ResponseListRelawan> call = RetrofitServiceApi.getInstance().getApi().getListRelawan()

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false);


    }
}
