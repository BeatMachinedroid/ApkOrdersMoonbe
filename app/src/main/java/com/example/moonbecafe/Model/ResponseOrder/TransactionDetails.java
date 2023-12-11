package com.example.moonbecafe.Model.ResponseOrder;

import com.google.gson.annotations.SerializedName;

public class TransactionDetails{

	@SerializedName("payment_type")
	private String paymentType;

	@SerializedName("total")
	private String total;

	@SerializedName("payment_status")
	private String paymentStatus;

	@SerializedName("order_id")
	private String orderId;

	public void setPaymentType(String paymentType){
		this.paymentType = paymentType;
	}

	public String getPaymentType(){
		return paymentType;
	}

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	public void setPaymentStatus(String paymentStatus){
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentStatus(){
		return paymentStatus;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}
}