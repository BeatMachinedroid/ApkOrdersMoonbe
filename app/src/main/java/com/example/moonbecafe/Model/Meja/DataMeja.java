package com.example.moonbecafe.Model.Meja;

import com.google.gson.annotations.SerializedName;
public class DataMeja {
    @SerializedName("id")
    private int idmeja;

    @SerializedName("meja")
    private String meja;

    @SerializedName("qrcode")
    private String qrcode;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    public int getIdmeja() {
        return idmeja;
    }

    public void setIdmeja(int idmeja) {
        this.idmeja = idmeja;
    }

    public String getMeja() {
        return meja;
    }

    public void setMeja(String meja) {
        this.meja = meja;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
