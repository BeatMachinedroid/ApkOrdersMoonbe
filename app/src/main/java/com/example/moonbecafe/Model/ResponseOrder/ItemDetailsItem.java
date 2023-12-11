package com.example.moonbecafe.Model.ResponseOrder;

import com.google.gson.annotations.SerializedName;

public class ItemDetailsItem{

	@SerializedName("image")
	private String image;

	@SerializedName("jumlah")
	private int jumlah;

	@SerializedName("price")
	private String price;

	@SerializedName("name")
	private String name;

	@SerializedName("category")
	private String category;

	@SerializedName("status")
	private int status;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setJumlah(int jumlah){
		this.jumlah = jumlah;
	}

	public int getJumlah(){
		return jumlah;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}