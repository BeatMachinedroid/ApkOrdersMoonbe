package com.example.moonbecafe.Model.ResponseOrder;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data{

	@SerializedName("customer_details")
	private CustomerDetails customerDetails;

	@SerializedName("item_details")
	private List<ItemDetailsItem> itemDetails;

	@SerializedName("va_number")
	private String vaNumber;

	@SerializedName("transaction_details")
	private TransactionDetails transactionDetails;

	public void setCustomerDetails(CustomerDetails customerDetails){
		this.customerDetails = customerDetails;
	}

	public CustomerDetails getCustomerDetails(){
		return customerDetails;
	}

	public void setItemDetails(List<ItemDetailsItem> itemDetails){
		this.itemDetails = itemDetails;
	}

	public List<ItemDetailsItem> getItemDetails(){
		return itemDetails;
	}

	public void setVaNumber(String vaNumber){
		this.vaNumber = vaNumber;
	}

	public String getVaNumber(){
		return vaNumber;
	}

	public void setTransactionDetails(TransactionDetails transactionDetails){
		this.transactionDetails = transactionDetails;
	}

	public TransactionDetails getTransactionDetails(){
		return transactionDetails;
	}
}