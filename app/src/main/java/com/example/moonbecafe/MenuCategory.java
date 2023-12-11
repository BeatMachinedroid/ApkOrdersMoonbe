package com.example.moonbecafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonbecafe.Adapter.AdapterCategory;
import com.example.moonbecafe.Adapter.AdapterMenu;
import com.example.moonbecafe.Api.ApiClient;
import com.example.moonbecafe.Model.Menu.DataMenu;
import com.example.moonbecafe.Model.Menu.ResponseMenu;
import com.example.moonbecafe.Model.Request.Order;
import com.example.moonbecafe.Model.Request.OrderItem;
import com.example.moonbecafe.Model.ResponseOrder.Data;
import com.example.moonbecafe.Model.ResponseOrder.ResponseOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuCategory extends AppCompatActivity {

    private List<DataMenu> dataMenuList = new ArrayList<>();
    private RecyclerView recyclerViewmenu, recyclerViewcate;
    private AdapterCategory adapterCategory;
    private AdapterMenu adapterMenu;
    Button pesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_category);
        recyclerViewmenu = findViewById(R.id.menu);
        pesan = findViewById(R.id.btnpesan);
        TextView header = findViewById(R.id.textHeader);
        int categoryid = getIntent().getIntExtra("id" , 0);
        String category = getIntent().getStringExtra("cate");
        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String meja = sharedPreferences.getString("meja", null);
        header.setText(meja);
        TextView title = findViewById(R.id.title);
        title.setText("Aneka Menu "+category);
        showmenu(categoryid);
    }
    private void showmenu(int categoryid) {
//        Intent getid = getIntent();
        Call<ResponseMenu> menuCall = ApiClient.getUserService().searchcate(categoryid);
        menuCall.enqueue(new Callback<ResponseMenu>() {
            @Override
            public void onResponse(Call<ResponseMenu> call, Response<ResponseMenu> response) {
                if (response.isSuccessful()) {
                    dataMenuList = response.body().getData();
                    recyclerViewmenu = findViewById(R.id.menu);
                    recyclerViewmenu.setLayoutManager(new GridLayoutManager(MenuCategory.this, 2));
                    adapterMenu = new AdapterMenu(dataMenuList, MenuCategory.this);
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
                                public void onResponse(Call<ResponseOrder> call, Response<ResponseOrder> response) {
                                    if (response.isSuccessful()){
                                        ResponseOrder dataorder = response.body();
                                        Toast.makeText(MenuCategory.this, dataorder.getMessage(), Toast.LENGTH_SHORT).show();
                                        List<Data> data = Collections.singletonList(response.body().getData());
                                        if (dataorder.getMessage() == "berhasil"){
                                            for (Data data1 : data){
//                                                SharedPreferences.Editor editor = sharedPreferences.edit();
//                                                editor.putString("va_number", data1.getVaNumber());
                                            }
                                        }
                                    }else{
                                        Toast.makeText(MenuCategory.this, "gagal", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<ResponseOrder> call, Throwable t) {
                                    Toast.makeText(MenuCategory.this, "Error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<ResponseMenu> call, Throwable t) {
                Toast.makeText(MenuCategory.this, "gagal"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}