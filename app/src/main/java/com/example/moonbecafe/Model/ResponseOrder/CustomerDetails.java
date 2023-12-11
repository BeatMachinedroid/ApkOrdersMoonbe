package com.example.moonbecafe.Model.ResponseOrder;

import com.google.gson.annotations.SerializedName;

public class CustomerDetails{

	@SerializedName("name")
	private String name;

	@SerializedName("email")
	private String email;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}