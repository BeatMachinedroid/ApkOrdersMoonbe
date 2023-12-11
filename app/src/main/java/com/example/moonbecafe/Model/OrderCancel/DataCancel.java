package com.example.moonbecafe.Model.OrderCancel;

import com.google.gson.annotations.SerializedName;

public class DataCancel{

	@SerializedName("status_message")
	private String statusMessage;

	@SerializedName("transaction_id")
	private String transactionId;

	@SerializedName("fraud_status")
	private String fraudStatus;

	@SerializedName("payment_type")
	private String paymentType;

	@SerializedName("transaction_status")
	private String transactionStatus;

	@SerializedName("status_code")
	private String statusCode;

	@SerializedName("transaction_time")
	private String transactionTime;

	@SerializedName("currency")
	private String currency;

	@SerializedName("merchant_id")
	private String merchantId;

	@SerializedName("gross_amount")
	private String grossAmount;

	@SerializedName("order_id")
	private String orderId;

	public void setStatusMessage(String statusMessage){
		this.statusMessage = statusMessage;
	}

	public String getStatusMessage(){
		return statusMessage;
	}

	public void setTransactionId(String transactionId){
		this.transactionId = transactionId;
	}

	public String getTransactionId(){
		return transactionId;
	}

	public void setFraudStatus(String fraudStatus){
		this.fraudStatus = fraudStatus;
	}

	public String getFraudStatus(){
		return fraudStatus;
	}

	public void setPaymentType(String paymentType){
		this.paymentType = paymentType;
	}

	public String getPaymentType(){
		return paymentType;
	}

	public void setTransactionStatus(String transactionStatus){
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionStatus(){
		return transactionStatus;
	}

	public void setStatusCode(String statusCode){
		this.statusCode = statusCode;
	}

	public String getStatusCode(){
		return statusCode;
	}

	public void setTransactionTime(String transactionTime){
		this.transactionTime = transactionTime;
	}

	public String getTransactionTime(){
		return transactionTime;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setGrossAmount(String grossAmount){
		this.grossAmount = grossAmount;
	}

	public String getGrossAmount(){
		return grossAmount;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}
}