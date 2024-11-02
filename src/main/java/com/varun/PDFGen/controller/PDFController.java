package com.varun.PDFGen.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	public ResponseEntity<Resource> generatePDF(@RequestBody Invoice invoice) throws FileNotFoundException {
		
		try {
            Resource pdfContent = invoicePdfGenerator.getPdfInvoiceStream(invoice);
			
			
		
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "invoice.pdf");
            headers.setContentLength(pdfContent.contentLength());

            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
		
		
	}
	
	
}
