package com.example.moonbecafe.Model.OrderCancel;

import com.google.gson.annotations.SerializedName;

public class ResponseOrderCancel{

	@SerializedName("data")
	private DataCancel dataCancel;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(DataCancel dataCancel){
		this.dataCancel = dataCancel;
	}

	public DataCancel getData(){
		return dataCancel;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}