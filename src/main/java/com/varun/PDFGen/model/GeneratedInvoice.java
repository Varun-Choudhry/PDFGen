package com.varun.PDFGen.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GeneratedInvoice {

	@Id
	private String invoiceId;
	private String invoicePath;
	public GeneratedInvoice() {}
	
	public GeneratedInvoice(String invoiceId, String invoicePath) {
		super();
		this.invoiceId = invoiceId;
		this.invoicePath = invoicePath;
	}
	
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getInvoicePath() {
		return invoicePath;
	}
	public void setInvoicePath(String invoicePath) {
		this.invoicePath = invoicePath;
	}
	
	
	
}
