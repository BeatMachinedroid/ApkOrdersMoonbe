package com.example.moonbecafe.Model.Category;

import com.google.gson.annotations.SerializedName;

public class DataCategory {
    @SerializedName("id")
    private int idcate;

    @SerializedName("image")
    private String image;

    @SerializedName("name")
    private String name;

    public int getIdcate() {
        return idcate;
    }

    public void setIdcate(int idcate) {
        this.idcate = idcate;
    }

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
