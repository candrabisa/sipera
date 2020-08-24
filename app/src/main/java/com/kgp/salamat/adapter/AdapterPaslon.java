package com.kgp.salamat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kgp.salamat.R;
import com.kgp.salamat.model.PaslonItem;

import java.util.List;

public class AdapterPaslon extends RecyclerView.Adapter<AdapterPaslon.ViewHolder> {

    private final List<PaslonItem> paslonItems;
    private final Context context;

    public AdapterPaslon(List<PaslonItem> paslonItems, Context context){
        this.paslonItems = paslonItems;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterPaslon.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.relawan_listpaslon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNoPaslon.setText(String.valueOf(paslonItems.get(position).getNo_paslon()));
        holder.tvNamaPaslon.setText(String.valueOf(paslonItems.get(position).getNama_paslon()));
    }

    @Override
    public int getItemCount() {
        return paslonItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tvNoPaslon, tvNamaPaslon;

        ViewHolder(@NonNull View itemView){
            super(itemView);

            tvNamaPaslon = itemView.findViewById(R.id.tv_relawannamaPaslon);
            tvNoPaslon = itemView.findViewById(R.id.tv_relawannomorPaslon);
        }
    }
}
