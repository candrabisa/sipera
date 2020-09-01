package com.kgp.salamat.admin.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import com.kgp.salamat.R;
import com.kgp.salamat.admin.Adapter.TpsAdapter;
import com.kgp.salamat.admin.Model.TpsItem;
import com.kgp.salamat.model.SpinnerItem;

import org.parceler.Parcels;

import static com.kgp.salamat.admin.Adapter.TpsAdapter.DATA_TPS;

public class DetailTpsActivity extends AppCompatActivity {
private TpsItem listtps;
private EditText nama_tps;
Spinner spprov,spkab,spkec,spkel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tps);
        nama_tps = findViewById(R.id.detailnamatps_admin);
        spprov = findViewById(R.id.detailspprovinsi_admin);
        spkab = findViewById(R.id.detailspkabupaten_admin);
        spkec = findViewById(R.id.detilspkecamatan_admin);
        spkel   = findViewById(R.id.detailspkeurahan_admin);
        Bundle bundle=getIntent().getBundleExtra(TpsAdapter.DATA_EXTRA);
        listtps = Parcels.unwrap(bundle.getParcelable(DATA_TPS));

        nama_tps.setText(listtps.getNamaTps());

    }
}