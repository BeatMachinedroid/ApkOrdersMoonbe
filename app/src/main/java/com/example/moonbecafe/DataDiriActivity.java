package com.example.moonbecafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DataDiriActivity extends AppCompatActivity {
    EditText nama, email;
    Button simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_diri);
        Intent getmeja = getIntent();
        TextView header = findViewById(R.id.textHeader);
        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String meja = sharedPreferences.getString("meja", null);
        header.setText(meja);
        nama = findViewById(R.id.edname);
        email = findViewById(R.id.edmail);
//        int idmeja = getmeja.getIntExtra("idmeja" , 0);
//        String name = nama.getText().toString();
        simpan = findViewById(R.id.btnsimpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nama", nama.getText().toString());
                editor.putString("email", email.getText().toString());
                editor.apply();
                String pelanggan =  sharedPreferences.getString("email" , null);
                if (pelanggan != null){
                    startActivity(new Intent(DataDiriActivity.this , ActivityHome.class));
                }else {
                    Toast.makeText(DataDiriActivity.this, "input your identity !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}