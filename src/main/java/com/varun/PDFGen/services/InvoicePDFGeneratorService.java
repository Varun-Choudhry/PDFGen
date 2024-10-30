package com.varun.PDFGen.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.itextpdf.text.DocumentException;
import com.varun.PDFGen.model.Invoice;
import com.varun.PDFGen.utils.PDFGeneratorUtil;

@Service
public class InvoicePDFGeneratorService implements PDFGeneratorService<Invoice> {

	@Autowired
	PDFGeneratorUtil pdfGeneratorUtil;	
	
	Context context = new Context();
	
	@Override
	public String generatePdf(Invoice invoice) {
		
		
		context.setVariable("Invoice", invoice);
		String parsedHtml = parsePdfInput(context);
	    try {
	    	pdfGeneratorUtil.generatePdfFromHtml(parsedHtml, "Invoice");
		} catch (DocumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return null;
	}

	@Override
	public String parsePdfInput(Context context) {
		
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    templateResolver.setPrefix("templates/");
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver);

	    

	    return templateEngine.process("invoice_template", context);
		
	}
	

}
