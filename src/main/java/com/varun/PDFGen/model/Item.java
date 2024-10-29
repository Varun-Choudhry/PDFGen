package com.varun.PDFGen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

	@JsonProperty("name")
	private String itemName;
	
	@JsonProperty("quantity")
	private String itemQuantity;
	
	@JsonProperty("rate")
	private double itemRate;
	
	@JsonProperty("amount")
	private double itemTotalAmount;
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public double getItemRate() {
		return itemRate;
	}
	public void setItemRate(double itemRate) {
		this.itemRate = itemRate;
	}
	public double getItemTotalAmount() {
		return itemTotalAmount;
	}
	public void setItemTotalAmount(double itemTotalAmount) {
		this.itemTotalAmount = itemTotalAmount;
	}
	
	
	
	
}
