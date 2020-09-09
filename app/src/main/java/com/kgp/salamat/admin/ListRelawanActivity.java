package com.kgp.salamat.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kgp.salamat.R;
import com.kgp.salamat.admin.Adapter.CalRelAdapter;
import com.kgp.salamat.admin.Adapter.RelAdapter;
import com.kgp.salamat.admin.Model.RelawanItem;

import java.util.ArrayList;
import java.util.List;

public class ListRelawanActivity extends AppCompatActivity {
private List<RelawanItem>listrelawan = new ArrayList<>();
private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_relawan);
        recyclerView = findViewById(R.id.idrcreladmin);
        RelawanItem baru = new RelawanItem();
       
        recyclerView.setAdapter(new RelAdapter(ListRelawanActivity.this,listrelawan));
        recyclerView.setLayoutManager(new LinearLayoutManager(ListRelawanActivity.this));
    }
}