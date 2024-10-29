package com.varun.PDFGen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.varun.PDFGen.model.Invoice;
import com.varun.PDFGen.services.InvoicePDFGeneratorService;

@RestController
public class PDFController {

	@Autowired
	InvoicePDFGeneratorService invoicePdfGenerator;
	
	@PostMapping("/pdf/generate")
	public ResponseEntity<byte[]> generatePDF(@RequestBody Invoice invoice) {
		
		invoicePdfGenerator.generatePDF(invoice);
		
		return ResponseEntity.ok(null);
	}
	
	
}
