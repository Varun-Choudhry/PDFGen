package com.varun.PDFGen.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Invoice {
	
	@JsonProperty("seller")
	private String sellerName;
	
	@JsonProperty("sellerGstin")
	private String sellerGstin;
	
	@JsonProperty("sellerAddress")
	private String sellerAddress;
	
	@JsonProperty("buyer")
	private String buyerName;
	
	@JsonProperty("buyerGstin")
	private String buyerGstin;
	
	@JsonProperty("buyerAddress")
	private String buyerAddress;
	
	@JsonProperty("items")
	private List<Item> items;
	
	
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerGstin() {
		return sellerGstin;
	}
	public void setSellerGstin(String sellerGstin) {
		this.sellerGstin = sellerGstin;
	}
	public String getSellerAddress() {
		return sellerAddress;
	}
	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerGstin() {
		return buyerGstin;
	}
	public void setBuyerGstin(String buyerGstin) {
		this.buyerGstin = buyerGstin;
	}
	public String getBuyerAddress() {
		return buyerAddress;
	}
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	@Override
	public int hashCode()
	{
		this.items.sort((i1, i2) -> i1.getItemName().compareTo(i2.getItemName()));
		return Objects.hash(this.buyerGstin,this.items,this.sellerGstin);
	}
}
