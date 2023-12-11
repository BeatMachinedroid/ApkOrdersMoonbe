package com.example.moonbecafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moonbecafe.Adapter.AdapterCategory;
import com.example.moonbecafe.Adapter.AdapterMenu;
import com.example.moonbecafe.Api.ApiClient;
import com.example.moonbecafe.Model.Category.DataCategory;
import com.example.moonbecafe.Model.Category.ResponseCategory;
import com.example.moonbecafe.Model.Menu.DataMenu;
import com.example.moonbecafe.Model.Menu.ResponseMenu;
import com.example.moonbecafe.Model.Request.Order;
import com.example.moonbecafe.Model.Request.OrderItem;
import com.example.moonbecafe.Model.ResponseOrder.Data;
import com.example.moonbecafe.Model.ResponseOrder.ResponseOrder;
import com.example.moonbecafe.Model.ResponseOrder.TransactionDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityHome extends AppCompatActivity {

    private List<DataCategory> dataCategoryList = new ArrayList<>();
    private List<DataMenu> dataMenuList = new ArrayList<>();
    private RecyclerView recyclerViewmenu, recyclerViewcate;
    //    private List<Data> dataListres = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private AdapterCategory adapterCategory;
    private AdapterMenu adapterMenu;
    Button pesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerViewcate = findViewById(R.id.category);
        recyclerViewmenu = findViewById(R.id.menu);
        pesan = findViewById(R.id.btnpesan);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        TextView header = findViewById(R.id.textHeader);
        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String meja = sharedPreferences.getString("meja", null);
        header.setText(meja);
        showCate();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Aksi yang akan dijalankan saat refresh
                // Misalnya, panggil metode untuk memuat ulang data

                showCate();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void showMenu() {
        Call<ResponseMenu> hasil = ApiClient.getUserService().Menu();
        hasil.enqueue(new Callback<ResponseMenu>() {
            @Override
            public void onResponse(Call<ResponseMenu> call, Response<ResponseMenu> response) {
                if (response.isSuccessful()){
                    dataMenuList = response.body().getData();
                    recyclerViewmenu = findViewById(R.id.menu);
                    recyclerViewmenu.setLayoutManager(new GridLayoutManager(ActivityHome.this , 2));
                    adapterMenu = new AdapterMenu(dataMenuList , ActivityHome.this);
                    recyclerViewmenu.setAdapter(adapterMenu);

                    List<OrderItem> orderItems = adapterMenu.getOrderItems();
                    SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
                    String nama = sharedPreferences.getString("nama", null);
                    String email = sharedPreferences.getString("email", null);
                    Integer meja = sharedPreferences.getInt("mejaId", 0);
                    Order order = new Order();
                    order.setName(nama);
                    order.setEmail(email);
                    order.setMeja(meja);
                    order.setBank("bca");
                    order.setItems(orderItems);
                    pesan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Call<ResponseOrder> orderCall = ApiClient.getUserService().createOrder(order);
                            orderCall.enqueue(new Callback<ResponseOrder>() {
                                @Override
                                public void onResponse(@NonNull Call<ResponseOrder> call, @NonNull Response<ResponseOrder> response) {
                                    if (response.isSuccessful()){
                                        ResponseOrder dataorder = response.body();
                                        assert dataorder != null;
                                        Toast.makeText(ActivityHome.this, dataorder.getMessage(), Toast.LENGTH_SHORT).show();
                                        assert response.body() != null;
                                        List<Data> data = Collections.singletonList(response.body().getData());
                                        List<TransactionDetails> detailsList = Collections.singletonList(response.body().getData().getTransactionDetails());
                                        for (TransactionDetails details : detailsList){
                                            startActivity(new Intent(ActivityHome.this, DetailMenuActivity.class).putExtra("orderid", details.getOrderId()));
                                        }
                                    }else{
                                        Toast.makeText(ActivityHome.this, "gagal", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onFailure(@NonNull Call<ResponseOrder> call, @NonNull Throwable t) {
                                    Toast.makeText(ActivityHome.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseMenu> call, Throwable t) {
                Toast.makeText(ActivityHome.this, "gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showCate() {
        Call<ResponseCategory> hasil = ApiClient.getUserService().Category();
        hasil.enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(@NonNull Call<ResponseCategory> call, @NonNull Response<ResponseCategory> response) {
                if(response.isSuccessful()){
//                    String pesan = response.body().getMessage();
                    Toast.makeText(ActivityHome.this , "Welcome to MoonBecafe" , Toast.LENGTH_SHORT).show();
                    dataCategoryList = response.body().getData();
                    recyclerViewcate = findViewById(R.id.category);
                    recyclerViewcate.setLayoutManager(new LinearLayoutManager(ActivityHome.this , LinearLayoutManager.HORIZONTAL, false));
                    adapterCategory = new AdapterCategory(dataCategoryList , ActivityHome.this);
                    recyclerViewcate.setAdapter(adapterCategory);
                    showMenu();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseCategory> call, @NonNull Throwable t) {
                Toast.makeText(ActivityHome.this, "failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}