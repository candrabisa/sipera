package com.kgp.salamat.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.kgp.salamat.R;
import com.kgp.salamat.adapter.AdapterRelawan;
import com.kgp.salamat.model.RelawanItem;
import com.kgp.salamat.model.ResponseListRelawan;
import com.kgp.salamat.view.view_listrelawan;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_relawan extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
   List <RelawanItem>listRelawan = new ArrayList<>();
  //ArrayList<RelawanItem> relawanList = new ArrayList<>();
    public fragment_relawan() {
        // Required empty public constructor
    }

    private AdapterRelawan adapterRelawan;
    //private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvEmpty;
    RecyclerView recyclerView;
    private static final String TAG = "fragment_relawan";
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        tvEmpty = view.findViewById(R.id.tvEmptyListRelawan);
        recyclerView = view.findViewById(R.id.relawan_listRelawan);
        loadRelawanData();
        adapterRelawan = new AdapterRelawan(listRelawan,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(adapterRelawan);
        
//        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshListRelawan);
//        swipeRefreshLayout.setOnRefreshListener(this);
//

    }
    private void loadRelawanData(){
        view_listrelawan viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(view_listrelawan.class);
        viewModel.setListRelawanData();
       // refreshing(true);
        viewModel.getListRelawanData().observe(getViewLifecycleOwner(), new Observer<ResponseListRelawan>() {
            private static final String TAG = "fragment_relawan";
            @Override
            public void onChanged(ResponseListRelawan responseListRelawan) {
                if (responseListRelawan == null){
                    tvEmpty.setVisibility(View.VISIBLE);
                  // refreshing(false);
                } else {

                   listRelawan = responseListRelawan.getRelawan();
                    adapterRelawan = new AdapterRelawan(listRelawan,getContext());
                    recyclerView.setAdapter(adapterRelawan);
                  // refreshing(false);
                }
            }
        });
    }

//    private void refreshing(boolean b) {
//        if (b){
//            swipeRefreshLayout.setRefreshing(true);
//        } else {
//            swipeRefreshLayout.setRefreshing(false);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_relawan, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_navigasi, menu);

        //view cari
        MenuItem item = menu.findItem(R.id.nav_cari);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        //SearchListener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!TextUtils.isEmpty(query.trim())) {
                    searchUsers(query);
                } else {
                    getAllUser();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if(!query.equals("")) {
                    searchUsers(query);
                } else {
                    getAllUser();
                }
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void getAllUser() {

    }

    private void searchUsers(String query) {
    }

    @Override
    public void onRefresh() {
        loadRelawanData();
    }
}
