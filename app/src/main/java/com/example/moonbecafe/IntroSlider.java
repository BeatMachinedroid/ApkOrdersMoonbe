package com.example.moonbecafe;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;
public class IntroSlider extends AppIntro {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showStatusBar(true);
        setSystemBackButtonLocked(true);
        setStatusBarColorRes(R.color.orange);
        addSlide(AppIntroFragment.newInstance("Scan", "Pertama lakukan scan pada qrcode yang telah disiapkan di meja anda",
                R.drawable.scan, ContextCompat.getColor(getApplicationContext(), R.color.orange)));

        // below line is for creating second slide.
        addSlide(AppIntroFragment.newInstance("Pesan", "Setelah berhasil, silahkan pilih pesan yang anda inginkan",
                R.drawable.pesan,
                ContextCompat.getColor(getApplicationContext(), R.color.orange)));

        // below line is use to create third slide.
        addSlide(AppIntroFragment.newInstance("Pembayaran", "Lakukan pembayaran dengan media transfer bank",
                R.drawable.bayar,
                ContextCompat.getColor(getApplicationContext(), R.color.orange)));

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(IntroSlider.this, ScanQr.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(IntroSlider.this, ScanQr.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Apakah Anda yakin ingin Keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Kembali ke aktivitas sebelumnya
                IntroSlider.super.onBackPressed();
                finish();
            }
        });
        builder.setNegativeButton("Tidak", null);
        builder.show();
    }


}
