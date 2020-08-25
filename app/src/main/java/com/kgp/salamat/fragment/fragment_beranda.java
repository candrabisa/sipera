package com.kgp.salamat.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kgp.salamat.R;
import com.kgp.salamat.adapter.AdapterPaslon;
import com.kgp.salamat.model.PaslonItem;
import com.kgp.salamat.model.ResponseListPaslon;
import com.kgp.salamat.view.view_listpaslon;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_beranda extends Fragment {

    List<PaslonItem>paslonItemList = new ArrayList<>();


    public fragment_beranda() {
        // Required empty public constructor
    }

    private AdapterPaslon adapterPaslon;
    RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.pb_RelawanBeranda);
        recyclerView = view.findViewById(R.id.recycler_listPaslon);
        loadPaslonData();
        adapterPaslon = new AdapterPaslon(paslonItemList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterPaslon);
    }

    private void loadPaslonData() {
        view_listpaslon viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(view_listpaslon.class);
        viewModel.setListPaslonData();
        viewModel.getListPaslonData().observe(getViewLifecycleOwner(), new Observer<ResponseListPaslon>() {
            @Override
            public void onChanged(ResponseListPaslon responseListPaslon) {
                progressBar.setVisibility(View.VISIBLE);
                if (responseListPaslon == null){
                    Toast.makeText(getContext(), "Gagal Memuat Paslon", Toast.LENGTH_SHORT).show();
                } else {
                    paslonItemList = responseListPaslon.getPaslon();
                    adapterPaslon = new AdapterPaslon(paslonItemList, getContext());
                    recyclerView.setAdapter(adapterPaslon);
                }
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false);
    }
}
