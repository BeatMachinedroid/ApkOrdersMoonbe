package com.example.moonbecafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moonbecafe.Api.ApiClient;
import com.example.moonbecafe.Model.OrderCancel.DataCancel;
import com.example.moonbecafe.Model.OrderCancel.ResponseOrderCancel;
import com.example.moonbecafe.Model.StatusOrder.Data;
import com.example.moonbecafe.Model.StatusOrder.ResponseStatus;
import com.example.moonbecafe.Model.StatusOrder.VaNumbersItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMenuActivity extends AppCompatActivity {
    TextView judul, idorder,cusdetail,status,nova, cusdetailemail, total, textheader;
    Button btnselesai,btncancel;
    private List<Data> dataListStatus = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        judul = findViewById(R.id.judul);
        idorder = findViewById(R.id.idorder);
        status = findViewById(R.id.status);

        textheader = findViewById(R.id.textHeader);
        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String meja = sharedPreferences.getString("meja", null);
        textheader.setText(meja);

        nova = findViewById(R.id.nova);
        cusdetail = findViewById(R.id.cusdetailname);
        cusdetailemail = findViewById(R.id.cusdetailemail);
        total = findViewById(R.id.total);

        Intent getintent = getIntent();
        String idorders = getintent.getStringExtra("orderid");
        idorder.setText("Order id : "+getintent.getStringExtra("orderid"));
        String nama = sharedPreferences.getString("nama", null);
        String email = sharedPreferences.getString("email", null);
        cusdetail.setText("Name : "+nama);
        cusdetailemail.setText("Email : "+email);

        Call<ResponseStatus> statusCall = ApiClient.getUserService().statusorder(idorders);
        statusCall.enqueue(new Callback<ResponseStatus>() {
            @Override
            public void onResponse(@NonNull Call<ResponseStatus> call, @NonNull Response<ResponseStatus> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    Data datastatus = response.body().getData();
                    total.setText("Total : Rp."+datastatus.getGrossAmount());
                    status.setText("status : "+datastatus.getTransactionStatus());
                    List<VaNumbersItem> vaNumbersItems = response.body().getData().getVaNumbers();
                    for (VaNumbersItem vaNumbersItem : vaNumbersItems){
                        nova.setText("Virtual Numbers : "+vaNumbersItem.getVaNumber());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseStatus> call, Throwable t) {

            }
        });

        btncancel = findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseOrderCancel> cancelCall = ApiClient.getUserService().cancelOrder(getintent.getStringExtra("orderid"));
                cancelCall.enqueue(new Callback<ResponseOrderCancel>() {
                    @Override
                    public void onResponse(Call<ResponseOrderCancel> call, Response<ResponseOrderCancel> response) {
                        if (response.isSuccessful()){
                            List<DataCancel> cancels = Collections.singletonList(response.body().getData());
                            for (DataCancel dataCancel : cancels){
                                Toast.makeText(DetailMenuActivity.this, dataCancel.getStatusMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseOrderCancel> call, Throwable t) {
                        Toast.makeText(DetailMenuActivity.this, "gagal"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}