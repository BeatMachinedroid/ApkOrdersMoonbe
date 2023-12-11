package com.example.moonbecafe.Model.StatusOrder;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data{

	@SerializedName("transaction_id")
	private String transactionId;

	@SerializedName("fraud_status")
	private String fraudStatus;

	@SerializedName("status_message")
	private String statusMessage;

	@SerializedName("transaction_status")
	private String transactionStatus;

	@SerializedName("status_code")
	private String statusCode;

	@SerializedName("payment_amounts")
	private List<Object> paymentAmounts;

	@SerializedName("signature_key")
	private String signatureKey;

	@SerializedName("gross_amount")
	private String grossAmount;

	@SerializedName("merchant_id")
	private String merchantId;

	@SerializedName("va_numbers")
	private List<VaNumbersItem> vaNumbers;

	@SerializedName("payment_type")
	private String paymentType;

	@SerializedName("transaction_time")
	private String transactionTime;

	@SerializedName("currency")
	private String currency;

	@SerializedName("expiry_time")
	private String expiryTime;

	@SerializedName("order_id")
	private String orderId;

	public String getTransactionId(){
		return transactionId;
	}

	public String getFraudStatus(){
		return fraudStatus;
	}

	public String getStatusMessage(){
		return statusMessage;
	}

	public String getTransactionStatus(){
		return transactionStatus;
	}

	public String getStatusCode(){
		return statusCode;
	}

	public List<Object> getPaymentAmounts(){
		return paymentAmounts;
	}

	public String getSignatureKey(){
		return signatureKey;
	}

	public String getGrossAmount(){
		return grossAmount;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public List<VaNumbersItem> getVaNumbers(){
		return vaNumbers;
	}

	public String getPaymentType(){
		return paymentType;
	}

	public String getTransactionTime(){
		return transactionTime;
	}

	public String getCurrency(){
		return currency;
	}

	public String getExpiryTime(){
		return expiryTime;
	}

	public String getOrderId(){
		return orderId;
	}
}