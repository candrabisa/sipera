package com.kgp.salamat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kgp.salamat.R;
import com.kgp.salamat.fragment.fragment_relawan;
import com.kgp.salamat.model.RelawanItem;
import com.kgp.salamat.model.ResponseListRelawan;

import java.util.ArrayList;
import java.util.List;

public class AdapterRelawan extends RecyclerView.Adapter<AdapterRelawan.ViewHolder> {

    public AdapterRelawan(List<RelawanItem> relawanItems, Context context) {
        this.relawanItems = relawanItems;
        this.context = context;
    }

    private List<RelawanItem>relawanItems;
    private List<RelawanItem>RelawanItemFull;
    private final Context context;



    @NonNull
    @Override
    public AdapterRelawan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.relawan_listrelawan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRelawan.ViewHolder holder, int position) {
        //get data
        final String relawanID = relawanItems.get(position).getIdRelawan();
        final String relawanNama = relawanItems.get(position).getNamaLengkap();
        final String relawanHP = relawanItems.get(position).getNoHp();
        final String relawanTPS = relawanItems.get(position).getTps();

        //set data
        holder.tvNama.setText(relawanNama);
        holder.tvHP.setText(relawanHP);
        holder.tvTPS.setText(relawanTPS);
    }

    @Override
    public int getItemCount() {
        return relawanItems.size();
    }

    public Filter getFilter(){
        return relawanFilter;
    }
    private Filter relawanFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<RelawanItem> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0){
                filteredList.addAll(relawanItems);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (RelawanItem item : relawanItems){
                    if (item.getNamaLengkap().toLowerCase().contains(filterPattern) ||
                            item.getTps().toLowerCase().contains(filterPattern) ||
                            item.getNoHp().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            relawanItems.clear();
            relawanItems.addAll((List<RelawanItem>)filterResults.values);
            notifyDataSetChanged();
        }
    };

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
