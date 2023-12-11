package com.example.moonbecafe.Model.Request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {
    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("menu")
    private List<OrderItem> menu;

    @SerializedName("meja")
    private int meja;

    public List<OrderItem> getMenu() {
        return menu;
    }

    public void setMenu(List<OrderItem> menu) {
        this.menu = menu;
    }

    public int getMeja() {
        return meja;
    }

    public void setMeja(int meja) {
        this.meja = meja;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @SerializedName("bank")
    private String bank;

    // Konstruktor, getter, setter lainnya


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OrderItem> getItems() {
        return menu;
    }

    public void setItems(List<OrderItem> menu) {
        this.menu = menu;
    }
}

