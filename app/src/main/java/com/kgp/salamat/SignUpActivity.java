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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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
import com.kgp.salamat.model.ResponseDaftar;
import com.kgp.salamat.model.ResponseSpinnerTps;
import com.kgp.salamat.model.SpinnerItem;
import com.kgp.salamat.service.RetrofitServiceApi;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class SignUpActivity extends AppCompatActivity {
    SearchableSpinner spinner;
    Button btnRegister, btnRegUpload;
    EditText etReg_NIK, etReg_Nama, etReg_Alamat, etReg_NoHP, etReg_Email, etReg_Password, etReg_UlangiPassword;
    TextView tvReg_Login;
    ImageView ivReg_KTP;
    ProgressDialog loading;
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

    final String url_Register = "http://nakulasadewaindonesia.com/tikep/service/daftar/add";

    //image resolution
    int bitmap_size = 40; // image quality 1-100;
    int max_resolution_image = 800;
    private String tps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnRegister = findViewById(R.id.btn_Reg);
        btnRegUpload = findViewById(R.id.btnReg_Upload);
        ivReg_KTP = (ImageView) findViewById(R.id.iv_RegFotoKTP);
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
//                ImagePicker.Companion.with(SignUpActivity.this)
//                        .crop()
//                        .compress(1024)
//                        .maxResultSize(1080, 1080)
//                        .start();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
    }

    private void signup(){
        loading = new ProgressDialog(this);
        loading.setMessage("Regristrasi...");
        loading.setCancelable(false);
        loading.show();
        String nik = etReg_NIK.getText().toString();
        String nama_lengkap = etReg_Nama.getText().toString();
        String alamat = etReg_Alamat.getText().toString();
        String no_hp = etReg_NoHP.getText().toString();
        String email = etReg_Email.getText().toString();
        String pass = etReg_Password.getText().toString();
        String confirmpass = etReg_UlangiPassword.getText().toString();

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
            try {
                tps = spinner.getSelectedItem().toString();
                Call<ResponseDaftar> call = RetrofitServiceApi
                        .getInstance()
                        .getApi()
                        .add(nik, nama_lengkap, alamat, no_hp, email, pass, tps);
                call.enqueue(new Callback<ResponseDaftar>() {
                    @Override
                    public void onResponse(Call<ResponseDaftar> call, Response<ResponseDaftar> response) {
                        ResponseDaftar responseDaftars = new ResponseDaftar();
                        responseDaftars = response.body();
                        String status = responseDaftars.getStatus();
                        if (status.equals("200")){
                            Toast.makeText(SignUpActivity.this, "Regristrasi berhasil", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            loading.dismiss();
                            Toast.makeText(SignUpActivity.this, "Registrasi gagal", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseDaftar> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            } catch (Exception e){
                Toast.makeText(this, "Pilih TPS Tempat Bertugas", Toast.LENGTH_SHORT).show();
            }

        }

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
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
    }
    private void requestStoragePermissions(){
        requestPermissions(storagePermissons, STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermissions(){
        boolean result = ContextCompat.checkSelfPermission(SignUpActivity.this, Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
        return  result && result1;
    }
    private void requestCameraPermissions(){
        requestPermissions(cameraPermissions, CAMERA_REQUEST_CODE);
    }

    private void ambilGambar(){
        ivReg_KTP.setImageResource(0);
        String options[] = {"Kamera", "Galeri", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih foto dari");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    if (!checkCameraPermissions()){
                        requestCameraPermissions();
                    } else {
                        ambilDariCamera();
                    }
                } else if (i == 1){
                    if (!checkStoragePermissions()){
                        requestStoragePermissions();
                    } else {
                        ambilDariGaleri();
                    }
                } else if (i == 2){
                    dialogInterface.dismiss();
                }
            }
        });
        builder.create().show();
    }

    private void ambilDariGaleri() {
        Intent intentGaleri = new Intent(Intent.ACTION_PICK);
        intentGaleri.setType("image/*");
        startActivityForResult(intentGaleri, IMAGE_PICK_GALLERY_REQUEST_CODE);
//        intentGaleri.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intentGaleri, "Pilih Gambar"), IMAGE_PICK_GALLERY_REQUEST_CODE);
    }

    private void ambilDariCamera() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp Pic");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Temp Description");
        image_uri = SignUpActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        image_uri = getOutputMediaFileUri();
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(intentCamera, IMAGE_PICK_CAMERA_REQUEST_CODE);
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
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CAMERA_REQUEST_CODE){
            Log.e("onActivityResult", "requestCode" + requestCode + ", resultCode" + resultCode );
            try {
                Log.e("CAMERA", image_uri.getPath());
                image_uri.getPath();
                assert data != null;
                ivReg_KTP.setImageURI(image_uri);
            } catch (Exception e){
                e.printStackTrace();
            }
        } if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_GALLERY_REQUEST_CODE){
            assert data != null;
            ivReg_KTP.setImageURI(data.getData());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        ivReg_KTP.setImageBitmap(decoded);
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize){
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio>1){
            width = maxSize;
            height = (int) (width/ bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (width / bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
    public Uri getOutputMediaFileUri(){
        return Uri.fromFile(getOutputMediaFile());
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "DeKa");
        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                Log.e("Monitoring", "Ops! Gagal membuat monitoring");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmms", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_DeKa_" + timeStamp + ".jpg");
        return mediaFile;
    }

//    private class RegisterUser extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... strings) {
//            String nik = strings[0];
//            String nama_lengkap = strings[1];
//            String alamat = strings[2];
//            String no_hp = strings[3];
//            String email = strings[4];
//            String pass = strings[5];
//
//            String finalURL = url_Register + "?nik=" + nik
//                    + "&nama_lengkap=" + nama_lengkap
//                    + "&alamat=" + alamat
//                    + "no_hp=" + no_hp
//                    + "email=" + email
//                    + "pass=" + pass;
//
//            try {
//                OkHttpClient okHttpClient = new OkHttpClient();
//                Request request = new Request.Builder()
//                        .url(finalURL)
//                        .get()
//                        .build();
//                okhttp3.Response response = null;
//
//                try {
//                    response = okHttpClient.newCall(request).execute();
//                    if (response.isSuccessful()){
//                        String result = response.body().string();
//                            showToast("Register successful");
//                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
//                            startActivity(intent);
//                            finish();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
//
//    private void showToast(final String Text) {
//        this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(SignUpActivity.this, Text, Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}