package com.example.moonbecafe.Model.Meja;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMeja {
    @SerializedName("data")
    private List<DataMeja> data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public List<DataMeja> getData() {
        return data;
    }

    public void setData(List<DataMeja> data) {
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
