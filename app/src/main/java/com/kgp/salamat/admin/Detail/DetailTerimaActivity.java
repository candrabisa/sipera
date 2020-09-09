package com.kgp.salamat.admin.Detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kgp.salamat.R;
import com.kgp.salamat.admin.Adapter.CalRelAdapter;
import com.kgp.salamat.admin.Adapter.PaslonAdapter;
import com.kgp.salamat.admin.Model.CalRelModel;

import org.parceler.Parcels;

import java.util.List;

import static com.kgp.salamat.admin.Adapter.CalRelAdapter.DATACALON;
import static com.kgp.salamat.admin.Adapter.PaslonAdapter.DATA_PASLON;

public class DetailTerimaActivity extends AppCompatActivity {
    List<CalRelModel>listcalon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_terima);
        Bundle bundle=getIntent().getBundleExtra(CalRelAdapter.DATAEXTRA);
        listcalon = Parcels.unwrap(bundle.getParcelable(DATACALON));

    }
}