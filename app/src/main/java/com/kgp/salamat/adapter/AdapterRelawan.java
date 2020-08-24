package com.kgp.salamat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kgp.salamat.R;
import com.kgp.salamat.model.RelawanItem;
import com.kgp.salamat.model.ResponseListRelawan;

import java.util.ArrayList;
import java.util.List;

public class AdapterRelawan extends RecyclerView.Adapter<AdapterRelawan.ViewHolder> {

    public AdapterRelawan(List<RelawanItem> relawanItems, Context context) {
        this.relawanItems = relawanItems;
        this.context = context;
    }

    private final List<RelawanItem>relawanItems;
    private final Context context;



    @NonNull
    @Override
    public AdapterRelawan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.relawan_listrelawan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRelawan.ViewHolder holder, int position) {

        holder.tvNama.setText(String.valueOf(relawanItems.get(position).getNamaLengkap()));
        holder.tvHP.setText(String.valueOf(relawanItems.get(position).getNoHp()));
        holder.tvTPS.setText(String.valueOf(relawanItems.get(position).getTps()));
//=======
//        holder.tvNama.setText(String.valueOf(relawanList.get(position).getAttributesRelawan().getNama_lengkap()));
//        holder.tvHP.setText(String.valueOf(relawanList.get(position).getAttributesRelawan().getNo_hp()));
//        holder.tvTPS.setText(String.valueOf(relawanList.get(position).getAttributesRelawan().getTps()));
//>>>>>>> Stashed changes
    }

    @Override
    public int getItemCount() {
        return relawanItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tvNama, tvHP, tvTPS;

        ViewHolder(@NonNull View itemView){
            super(itemView);

            tvNama = itemView.findViewById(R.id.tvNama_listRelawan);
            tvHP = itemView.findViewById(R.id.tvNOHP_listRelawan);
            tvTPS = itemView.findViewById(R.id.tvTPS_listRelawan);
        }
    }
}
