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
import com.kgp.salamat.admin.Detail.DetailPaslonActivity;
import com.kgp.salamat.admin.Detail.DetailTpsActivity;
import com.kgp.salamat.admin.Model.ModelPaslon;

import org.parceler.Parcels;

import java.util.List;

import static com.kgp.salamat.admin.Adapter.TpsAdapter.DATA_TPS;

public class PaslonAdapter extends RecyclerView.Adapter<PaslonAdapter.holder> {
    public static final String DATA_PASLON = "dhks";
    public static final String DATA_EXSSTRA = "ll";
    Context context;
    List<ModelPaslon>listpaslon;

    public PaslonAdapter(Context context, List<ModelPaslon> listpaslon) {
        this.context = context;
        this.listpaslon = listpaslon;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_paslon, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        ModelPaslon dm = listpaslon.get(position);
        holder.nama.setText(dm.getNama_paslon());
        holder.id.setText(dm.getId_paslon());
        holder.nomer.setText(dm.getNo_paslon());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah=new Intent(context, DetailPaslonActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelable(DATA_PASLON, Parcels.wrap(listpaslon.get(position)));
                pindah.putExtra(DATA_EXSSTRA,bundle);
                context.startActivity(pindah);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listpaslon.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView nama,nomer,id;
        public holder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama_paslon_admin);
            nomer = itemView.findViewById(R.id.no_paslon_admin);
            id= itemView.findViewById(R.id.id_admin_paslon);
        }
    }
}
