package com.varun.PDFGen.services;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.varun.PDFGen.model.Invoice;
import com.varun.PDFGen.utils.PDFGeneratorUtil;

@Service
public class InvoicePDFGeneratorService implements PDFGeneratorService<Invoice> {

	@Autowired
	PDFGeneratorUtil pdfGeneratorUtil;	
	private Integer uniqueInvoiceId;
	
	Context context = new Context();
	private final SpringTemplateEngine templateEngine;
	
	public InvoicePDFGeneratorService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
	@Override
	public byte[] generatePdf(Model model) throws FileNotFoundException {
		
		 	Context context = new Context();
	        model.asMap().forEach(context::setVariable);
	        String htmlContent = templateEngine.process("invoice_template", context);
	        return pdfGeneratorUtil.generatePdfFromHtml(htmlContent, "generated_invoice.pdf");
	        
	        
	}



	
	

}
