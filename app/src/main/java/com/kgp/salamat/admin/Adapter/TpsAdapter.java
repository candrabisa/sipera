package com.kgp.salamat.admin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kgp.salamat.R;
import com.kgp.salamat.admin.Detail.DetailTpsActivity;
import com.kgp.salamat.admin.Model.TpsItem;
import com.kgp.salamat.model.RelawanItem;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class TpsAdapter extends RecyclerView.Adapter<TpsAdapter.Holder> {
    public static final String DATA_TPS = "data_tps";
    public static final String DATA_EXTRA = "data_ekstra";
    Context context;
    List<TpsItem>listtps;

    public TpsAdapter(Context context, List<TpsItem> listtps) {
        this.context = context;
        this.listtps = listtps;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tps, parent, false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        TpsItem dm = listtps.get(position);
        holder.tv_idtps.setText(dm.getIdTps().toString());
        holder.tv_namatps.setText(dm.getNamaTps().toString());
        holder.tv_alamattps.setText(dm.getAlamatTps().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah=new Intent(context, DetailTpsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable(DATA_TPS, Parcels.wrap(listtps.get(position)));
                pindah.putExtra(DATA_EXTRA,bundle);
                context.startActivity(pindah);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listtps.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_idtps,tv_namatps, tv_alamattps;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_namatps = itemView.findViewById(R.id.nama_tps_admin);
            tv_idtps = itemView.findViewById(R.id.id_tps_admin);
            tv_alamattps = itemView.findViewById(R.id.alamat_tps_admin);
        }
    }
}
