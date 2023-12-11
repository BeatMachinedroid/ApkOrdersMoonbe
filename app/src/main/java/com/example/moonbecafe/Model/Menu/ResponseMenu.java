package com.example.moonbecafe.Model.Menu;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMenu {
    @SerializedName("data")
    private List<DataMenu> data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public List<DataMenu> getData() {
        return data;
    }

    public void setData(List<DataMenu> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
