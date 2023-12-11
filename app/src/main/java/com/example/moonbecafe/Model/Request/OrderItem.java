package com.example.moonbecafe.Model.Request;

import com.google.gson.annotations.SerializedName;

public class OrderItem {
    @SerializedName("menu")
    private int menu;

    @SerializedName("jumlah")
    private int jumlah;


    public OrderItem(int menuid , int jumlah) {
        this.menu = menuid;
        this.jumlah = jumlah;
    }
    // Konstruktor, getter, setter lainnya


    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
