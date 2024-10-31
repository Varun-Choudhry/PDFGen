package com.varun.PDFGen.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
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
	public ResponseEntity<byte[]> generatePDF(@RequestBody Invoice invoice) throws FileNotFoundException {
		
		try {
            // Prepare the model data for the Thymeleaf template
            Model model = new ExtendedModelMap();
            model.addAttribute("invoice", invoice);

            // Generate the PDF as a byte array using the service
            byte[] pdfContent = invoicePdfGenerator.generatePdf(model);

            // Set headers to download the PDF as an attachment
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "invoice.pdf");
            headers.setContentLength(pdfContent.length);

            return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
		
		
	}
	
	
}
