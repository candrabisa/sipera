package com.kgp.salamat.admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kgp.salamat.R;
import com.kgp.salamat.admin.Model.RelawanItem;
import com.kgp.salamat.admin.Model.TpsItem;

import java.util.List;

public class RelAdapter extends RecyclerView.Adapter<RelAdapter.holder> {
    Context context;
    List<RelawanItem>listrelawan;

    public RelAdapter(Context context, List<RelawanItem> listrelawan) {
        this.context = context;
        this.listrelawan = listrelawan;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tps, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        RelawanItem dm = listrelawan.get(position);
        holder.nik.setText(dm.getNik());
        holder.nama.setText(dm.getNamaLengkap());
        holder.alamt.setText(dm.getAlamat());
        holder.tps.setText(dm.getTps());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
