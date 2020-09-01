package com.kgp.salamat.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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

    //ArrayList<RelawanItem> relawanList = new ArrayList<>();
    public fragment_relawan() {
        // Required empty public constructor
    }

    private AdapterRelawan adapterRelawan;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvEmpty;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private List <RelawanItem>listRelawan = new ArrayList<>();


    private static final String TAG = "fragment_relawan";
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        tvEmpty = view.findViewById(R.id.tvEmptyListRelawan);
        progressBar = view.findViewById(R.id.pb_RelawanList);
        recyclerView = view.findViewById(R.id.relawan_listRelawan);

        adapterRelawan = new AdapterRelawan(listRelawan,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadRelawanData();
        recyclerView.setAdapter(adapterRelawan);
        
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshListRelawan);
        swipeRefreshLayout.setOnRefreshListener(this);
        loadRelawanData();

    }
    private void loadRelawanData(){
        view_listrelawan viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(view_listrelawan.class);
        viewModel.setListRelawanData();
       // refreshing(true);
        viewModel.getListRelawanData().observe(getViewLifecycleOwner(), new Observer<ResponseListRelawan>() {
            private static final String TAG = "fragment_relawan";
            @Override
            public void onChanged(ResponseListRelawan responseListRelawan) {
                progressBar.setVisibility(View.VISIBLE);
                if (responseListRelawan == null){
                    tvEmpty.setVisibility(View.VISIBLE);
                    refreshing(false);

                } else {

                    listRelawan = responseListRelawan.getRelawan();
                    adapterRelawan = new AdapterRelawan(listRelawan,getContext());
                    recyclerView.setAdapter(adapterRelawan);
                    refreshing(false);
                    tvEmpty.setVisibility(View.GONE);
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void refreshing(boolean b) {
        if (b){
            swipeRefreshLayout.setRefreshing(true);
        } else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_relawan, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        setHasOptionsMenu(true);
//        getActivity().getActionBar().setTitle(R.string.list_relawan);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_navigasi, menu);
        super.onCreateOptionsMenu(menu, inflater);
        //view cari
        MenuItem item = menu.findItem(R.id.nav_cari);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Cari Relawan");
        //SearchListener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.equals("")){
                    adapterRelawan.getFilter().filter(query);
                    adapterRelawan = new AdapterRelawan(listRelawan, getActivity());
                    recyclerView.setAdapter(adapterRelawan);
                    adapterRelawan.notifyDataSetChanged();
                } else {
                    loadRelawanData();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (!query.equals("")){
                    adapterRelawan.getFilter().filter(query);
                    Log.d(TAG, "onQueryTextChange: "+query);
                    adapterRelawan = new AdapterRelawan(listRelawan, getActivity());
                    recyclerView.setAdapter(adapterRelawan);
                    adapterRelawan.notifyDataSetChanged();
                } else {
                    loadRelawanData();
                }
                return false;

            }
        });
    }

    private void getAllUser() {
        loadRelawanData();
    }

    private void searchUsers(String query) {

        view_listrelawan view_listrelawan = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(view_listrelawan.class);
        view_listrelawan.setListRelawanData();
        view_listrelawan.getListRelawanData().observe(getViewLifecycleOwner(), new Observer<ResponseListRelawan>() {
            @Override
            public void onChanged(ResponseListRelawan responseListRelawan) {
                adapterRelawan.getFilter().filter(query);
                adapterRelawan = new AdapterRelawan(listRelawan, getActivity());
                recyclerView.setAdapter(adapterRelawan);

            }
        });
    }

    @Override
    public void onRefresh() {
        loadRelawanData();
    }
}
