package com.kgp.salamat.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.kgp.salamat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_relawan extends Fragment {

    public fragment_relawan() {
        // Required empty public constructor
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
}
