package com.kgp.salamat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kgp.salamat.api.ApiService;
import com.kgp.salamat.model.ResponseRegister;
import com.kgp.salamat.model.ResponseSpinnerTps;
import com.kgp.salamat.model.SpinnerItem;
import com.kgp.salamat.service.RetrofitServiceApi;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    SearchableSpinner spinner;
    Button btnRegister, btnRegUpload;
    EditText etReg_NIK, etReg_Nama, etReg_Alamat, etReg_NoHP, etReg_Email, etReg_Password, etReg_UlangiPassword;
    TextView tvReg_Login;
    ImageView ivReg_KTP;
    private List<String>listspineralamat = new ArrayList<>();
    private List<SpinnerItem> spinnerItems=new ArrayList<>();
    private static final String TAG = "SignUpActivity";

    //permissions contact
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 200;
    private static final int IMAGE_PICK_GALLERY_REQUEST_CODE = 300;
    private static final int IMAGE_PICK_CAMERA_REQUEST_CODE = 400;


    //arrays of permissions to be request
    private String cameraPermissions[];
    private String storagePermissons[];

    //uri picked image
    private Uri image_uri;

    //Check foto profil atau cover
    private String profileOrCoverPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnRegister = findViewById(R.id.btn_Reg);
        btnRegUpload = findViewById(R.id.btnReg_Upload);
        etReg_NIK = findViewById(R.id.etReg_NIK);
        etReg_Nama = findViewById(R.id.etReg_NamaLengkap);
        etReg_Alamat = findViewById(R.id.etReg_Alamat);
        etReg_NoHP = findViewById(R.id.etReg_NoHP);
        etReg_Email= findViewById(R.id.etReg_Email);
        etReg_Password = findViewById(R.id.etReg_Password);
        etReg_UlangiPassword = findViewById(R.id.etReg_Password2);
        tvReg_Login = findViewById(R.id.btnReg_Login);

        cameraPermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        storagePermissons = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        spinner = findViewById(R.id.ss_TPS);
        ambilspinnertps();

        ArrayAdapter adapter=new ArrayAdapter(SignUpActivity.this,android.R.layout.simple_spinner_dropdown_item,spinnerItems);
        spinner.setAdapter(adapter);

        tvReg_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignin = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intentSignin);
                finish();
            }
        });
        btnRegUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambilGambar();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nik = etReg_NIK.getText().toString().trim();
                String nama_lengkap = etReg_Nama.getText().toString().trim();
                String alamat = etReg_Alamat.getText().toString().trim();
                String no_hp = etReg_NoHP.getText().toString().trim();
                String email = etReg_Email.getText().toString().trim();
                String pass = etReg_Password.getText().toString().trim();
                String confirmpass = etReg_UlangiPassword.getText().toString().trim();

//                ImageView ktp = ivReg_KTP.

                if (nik.isEmpty()){
                    etReg_NIK.setError("NIK belum diisi");
                    etReg_NIK.requestFocus();
                    return;
                } else if(nik.length()<16){
                    etReg_NIK.setError("NIK KTP Harus memiliki 16 angka");
                    etReg_NIK.requestFocus();
                } else if (nama_lengkap.isEmpty()) {
                    etReg_Nama.setError("Nama belum diisi");
                    etReg_Nama.requestFocus();
                    return;
                } else if (alamat.isEmpty()){
                    etReg_Alamat.setError("Alamat belum diisi");
                    etReg_Alamat.requestFocus();
                    return;
                } else if (no_hp.isEmpty()){
                    etReg_NoHP.setError("No HP belum diisi");
                    etReg_NoHP.requestFocus();
                    return;
                } else if (no_hp.length()<10){
                    etReg_NoHP.setError("No HP setidaknya memiliki 11 angka");
                    etReg_NoHP.requestFocus();
                    return;
                } else if (email.isEmpty()){
                    etReg_Email.setError("Email belum diisi");
                    etReg_Email.requestFocus();
                    return;
                } else if(pass.isEmpty()){
                    etReg_Password.setError("Password belum diisi");
                    etReg_Password.requestFocus();
                    return;
                } else if (pass.length()<6){
                    etReg_Password.setError("Password harus 6-16 karakter");
                    etReg_Password.requestFocus();
                    return;
                } else if (confirmpass.isEmpty()){
                    etReg_UlangiPassword.setError("Ulangi password belum diisi");
                    etReg_UlangiPassword.requestFocus();
                    return;
                } else if (!confirmpass.equals(pass)){
                    etReg_UlangiPassword.setError("Password tidak sesuai");
                    etReg_UlangiPassword.requestFocus();
                    return;
                } else {
                    Call<ResponseRegister> call = RetrofitServiceApi.getApi().postRegisterRelawan(nik, nama_lengkap, alamat, no_hp, email, pass);
                    call.enqueue(new Callback<ResponseRegister>() {
                        @Override
                        public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                            if (response.body().isEror() == false){
                                startActivity( new Intent(SignUpActivity.this, LoginActivity.class));
                                Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseRegister> call, Throwable t) {
                            Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        startActivity(intentLogin);
        finish();
    }

    private void ambilspinnertps() {

        ApiService request = RetrofitServiceApi.getRetrofitService().create(ApiService.class);

        Call<ResponseSpinnerTps> tampilData = request.getspinnertps();
        tampilData.enqueue(new Callback<ResponseSpinnerTps>() {
            @Override
            public void onResponse(Call<ResponseSpinnerTps> call, Response<ResponseSpinnerTps> response) {
            spinnerItems=response.body().getSpinner();
                for (int i = 0; i <spinnerItems.size() ; i++) {
                    listspineralamat.add(spinnerItems.get(i).getAlamatTps().trim());
                }
             tampilspinner(spinner,listspineralamat);
            }

            @Override
            public void onFailure(Call<ResponseSpinnerTps> call, Throwable t) {

            }

        });

    }
    private void tampilspinner(Spinner spinner, List<String>dataalamttps){
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.io_spinner, dataalamttps);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private boolean checkStoragePermissions(){
        return ContextCompat.checkSelfPermission(Objects.requireNonNull(this), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
    }
    private void requestStoragePermissions(){
        requestPermissions(storagePermissons, STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermissions(){
        boolean result = ContextCompat.checkSelfPermission(Objects.requireNonNull(this), Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
        return  result && result1;
    }
    private void requestCameraPermissions(){
        requestPermissions(cameraPermissions, CAMERA_REQUEST_CODE);
    }

    private void ambilGambar(){
        String options[] = {"kamera", "Galeri"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih foto dari");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    if (checkCameraPermissions()){
                        requestCameraPermissions();
                    } else {
                        ambilDariCamera();
                    }
                } else if (i == 1){
                    if (checkStoragePermissions()){
                        requestStoragePermissions();
                    } else {
                        ambilDariGaleri();
                    }
                }
            }
        });
        builder.create().show();
    }

    private void ambilDariGaleri() {
        Intent intentGaleri = new Intent(Intent.ACTION_PICK);
        intentGaleri.setType("image/*");
        startActivityForResult(intentGaleri, IMAGE_PICK_GALLERY_REQUEST_CODE);
    }

    private void ambilDariCamera() {
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(intentCamera, IMAGE_PICK_CAMERA_REQUEST_CODE);

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp Pic");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Temp Description");
        image_uri = SignUpActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if (grantResults.length >0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && writeStorageAccepted){
                        //permissions enabled
                        ambilDariCamera();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Mohon izikan aplikasi membaca kamera dan penyimpanan", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                if (grantResults.length >0){
                    boolean writeStorageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageAccepted){
                        //permissions enabled
                        ambilDariGaleri();
                    } else {
                        Toast.makeText(this, "Mohon izikan aplikasi membaca penyimpanan internal", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if (requestCode == IMAGE_PICK_CAMERA_REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                assert data != null;
                image_uri = data.getData();

            }
        } if (requestCode == IMAGE_PICK_GALLERY_REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                assert data !=null;
                image_uri = data.getData();
                String[]filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(image_uri, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturesPath = cursor.getString(columnIndex);
                cursor.close();

                ivReg_KTP = findViewById(R.id.iv_RegFotoKTP);
                ivReg_KTP.setImageBitmap(BitmapFactory.decodeFile(picturesPath));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
