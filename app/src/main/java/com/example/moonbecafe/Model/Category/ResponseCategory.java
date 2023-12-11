package com.example.moonbecafe.Model.Category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCategory {
    @SerializedName("data")
    private List<DataCategory> data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public List<DataCategory> getData() {
        return data;
    }

    public void setData(List<DataCategory> data) {
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
