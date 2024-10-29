package com.varun.PDFGen.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.varun.PDFGen.model.Invoice;

@RestController
public class PDFController {

	
	@PostMapping("/pdf/generate")
	public ResponseEntity<byte[]> generatePDF(@RequestBody Invoice invoice) {
		
		
		
		return ResponseEntity.ok(null);
	}
	
	
}
