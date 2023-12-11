package com.example.moonbecafe.Api;

import com.example.moonbecafe.Model.Category.ResponseCategory;
import com.example.moonbecafe.Model.Meja.ResponseMeja;
import com.example.moonbecafe.Model.Menu.ResponseMenu;
import com.example.moonbecafe.Model.OrderCancel.ResponseOrderCancel;
import com.example.moonbecafe.Model.Request.Order;
import com.example.moonbecafe.Model.ResponseOrder.ResponseOrder;
import com.example.moonbecafe.Model.StatusOrder.ResponseStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {
    @GET("meja")
    Call<ResponseMeja> Meja();

    @GET("meja/{meja}")
    Call<ResponseMeja> MejaSearch(
            @Path("meja") String meja
    );

    @GET("category")
    Call<ResponseCategory> Category();

    @GET("menu")
    Call<ResponseMenu> Menu();

    @GET("menu/{category}")
    Call<ResponseMenu> searchcate(
            @Path("category") int category
    );

    @GET("menu/search/{id}")
    Call<ResponseMenu> searchmenu(
            @Path("id") int idmenu
    );

    @POST("pelanggan/order")
    Call<ResponseOrder> createOrder (@Body Order order);

    @GET("pelanggan/order/status/{orderid}")
    Call<ResponseStatus> statusorder(
            @Path("orderid") String orderid
    );

    @POST("order/cancel")
    Call<ResponseOrderCancel> cancelOrder(
            @Path("order_code") String orderid
    );
}
