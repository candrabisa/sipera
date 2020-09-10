package com.kgp.salamat.admin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kgp.salamat.R;
import com.kgp.salamat.admin.Detail.DetailListRelawanActivity;
import com.kgp.salamat.admin.Detail.DetailTpsActivity;
import com.kgp.salamat.admin.Model.RelawanItem;
import com.kgp.salamat.admin.Model.TpsItem;

import org.parceler.Parcels;

import java.util.List;

public class RelAdapter extends RecyclerView.Adapter<RelAdapter.holder> {
    public static final String DATA_REL = "reldat";
    public static final String DATA_EXTRA_REL = "extradat";
    Context context;
    List<RelawanItem>listrelawan;

    public RelAdapter(Context context, List<RelawanItem> listrelawan) {
        this.context = context;
        this.listrelawan = listrelawan;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_relawan, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        RelawanItem dm =listrelawan.get(position);
        holder.nik.setText(dm.getNik().toString());
        holder.nama.setText(dm.getNamaLengkap().toString());
        holder.alamt.setText(dm.getAlamat().toString());
        holder.tps.setText(dm.getTps().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah=new Intent(context, DetailListRelawanActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable(DATA_REL, Parcels.wrap(listrelawan.get(position)));
                pindah.putExtra(DATA_EXTRA_REL,bundle);
                context.startActivity(pindah);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listrelawan.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView nama,alamt,nik,tps;
        public holder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.namareladmin);
            alamt = itemView.findViewById(R.id.alamatreladmin);
            nik = itemView.findViewById(R.id.nikreladmin);
            tps = itemView.findViewById(R.id.tpsreladmin);
        }
    }
}
