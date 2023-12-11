package com.example.moonbecafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.moonbecafe.Api.ApiClient;
import com.example.moonbecafe.Model.Meja.DataMeja;
import com.example.moonbecafe.Model.Meja.ResponseMeja;
import com.google.zxing.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanQr extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CAMERA = 0;
    private CodeScanner mCodeScanner;
    private CodeScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        CekPermission();
    }

    private void CekPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Izin kamera belum diberikan, perlu meminta izin
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CAMERA);
        }else{
            OpenCamera();
        }
    }

    private void OpenCamera() {
        scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this , scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Call<ResponseMeja> mejaCall = ApiClient.getUserService().MejaSearch(result.getText());
                        mejaCall.enqueue(new Callback<ResponseMeja>() {
                            @Override
                            public void onResponse( Call<ResponseMeja> call, Response<ResponseMeja> response) {
                                if (response.isSuccessful()){
                                    List<DataMeja> dataMejaList = response.body().getData();
                                    for (DataMeja dataMeja : dataMejaList){
                                        Toast.makeText(ScanQr.this, dataMeja.getMeja(), Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ScanQr.this , DataDiriActivity.class));
                                        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("meja", dataMeja.getMeja());
                                        editor.putInt("mejaId", dataMeja.getIdmeja());
                                        editor.apply();
                                    }
                                }
                            }

                            @Override
                            public void onFailure( Call<ResponseMeja> call, Throwable t) {
                                Toast.makeText(ScanQr.this, "Gagal Terhubung ke Server", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCodeScanner != null) {
            mCodeScanner.startPreview();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Apakah Anda yakin ingin kembali?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Kembali ke aktivitas sebelumnya
                ScanQr.super.onBackPressed();
                startActivity(new Intent(ScanQr.this, IntroSlider.class));
            }
        });
        builder.setNegativeButton("Tidak", null);
        builder.show();
    }

    @Override
    protected void onPause(){
        if (mCodeScanner != null) {
            mCodeScanner.startPreview();
        }
        super.onPause();
    }

    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Izin kamera diberikan, lanjutkan dengan operasi kamera
                OpenCamera();
            } else {
                // Izin kamera ditolak, tampilkan pesan atau aksi yang sesuai
                showPermissionDeniedDialog();
            }
        }
    }

    private void showPermissionDeniedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Izin Kamera Diperlukan");
        builder.setMessage("Aplikasi ini memerlukan izin kamera untuk membuka kamera. Mohon izinkan akses kamera di Pengaturan aplikasi.");
        builder.setPositiveButton("Pengaturan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Buka pengaturan aplikasi di layar pengaturan
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Tampilkan pesan atau lakukan tindakan lain jika pengguna menolak kembali meminta izin
                // ...
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}