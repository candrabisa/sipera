package com.kgp.salamat.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kgp.salamat.LoginActivity;
import com.kgp.salamat.R;
import com.kgp.salamat.SignUpActivity;

import static com.kgp.salamat.LoginActivity.sharedprefren;
import static com.kgp.salamat.LoginActivity.useremail;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_profil extends Fragment {

    public fragment_profil() {
        // Required empty public constructor
    }

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
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false);


    }
}
