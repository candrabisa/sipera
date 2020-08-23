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
import com.kgp.salamat.model.ModelListRelawan;

import java.util.ArrayList;

public class AdapterRelawan extends RecyclerView.Adapter<AdapterRelawan.ViewHolder> {

    private final ArrayList<ModelListRelawan> relawanList = new ArrayList<>();
    private final Context context;

    public AdapterRelawan(Context context) { this.context = context;}

    public void setRelawanList(ArrayList<ModelListRelawan> relawanitem){
        if (relawanitem != null){
            if (relawanitem.size()>0){
                relawanitem.clear();
            }
            relawanitem.addAll(relawanitem);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterRelawan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.relawan_listrelawan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRelawan.ViewHolder holder, int position) {

        holder.tvNama.setText(String.valueOf(relawanList.get(position).getNama_lengkap()));
        holder.tvHP.setText(String.valueOf(relawanList.get(position).getNo_hp()));
        holder.tvTPS.setText(String.valueOf(relawanList.get(position).getTps()));
    }

    @Override
    public int getItemCount() {
        return relawanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final ImageView ivProfil;
        final TextView tvNama, tvHP, tvTPS;

        ViewHolder(@NonNull View itemView){
            super(itemView);
            ivProfil = itemView.findViewById(R.id.ivProfile_listRelawan);
            tvNama = itemView.findViewById(R.id.tvNama_listRelawan);
            tvHP = itemView.findViewById(R.id.tvNOHP_listRelawan);
            tvTPS = itemView.findViewById(R.id.tvTPS_listRelawan);
        }
    }
}
