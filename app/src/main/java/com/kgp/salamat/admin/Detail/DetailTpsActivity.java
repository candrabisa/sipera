package com.kgp.salamat.admin.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.kgp.salamat.R;
import com.kgp.salamat.admin.Adapter.TpsAdapter;
import com.kgp.salamat.admin.Model.TpsItem;
import com.kgp.salamat.model.SpinnerItem;

import org.parceler.Parcels;

import static com.kgp.salamat.admin.Adapter.TpsAdapter.DATA_TPS;

public class DetailTpsActivity extends AppCompatActivity {
private TpsItem listtps;
private EditText nama_tps;
private TextView alamattps;
String idtps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tps);
        nama_tps = findViewById(R.id.detailnamatps_admin);
        alamattps = findViewById(R.id.id_alamatdetailtps);


        Bundle bundle=getIntent().getBundleExtra(TpsAdapter.DATA_EXTRA);
        listtps = Parcels.unwrap(bundle.getParcelable(DATA_TPS));

        nama_tps.setText(listtps.getNamaTps());
        alamattps.setText(listtps.getAlamatTps());
        idtps = listtps.getIdTps();



    }

    public void DeleteTPS(View view) {

    }

    public void UpdateTps(View view) {
    }
}