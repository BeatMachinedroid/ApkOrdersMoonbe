package com.example.moonbecafe.Model.StatusOrder;

import com.google.gson.annotations.SerializedName;

public class VaNumbersItem{

	@SerializedName("bank")
	private String bank;

	@SerializedName("va_number")
	private String vaNumber;

	public String getBank(){
		return bank;
	}

	public String getVaNumber(){
		return vaNumber;
	}
}