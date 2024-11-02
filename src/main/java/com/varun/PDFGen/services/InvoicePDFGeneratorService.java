package com.varun.PDFGen.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.varun.PDFGen.PdfGenApplication;
import com.varun.PDFGen.model.GeneratedInvoice;
import com.varun.PDFGen.model.Invoice;
import com.varun.PDFGen.model.Item;
import com.varun.PDFGen.persistence.GeneratedInvoiceRepository;
import com.varun.PDFGen.utils.PDFGeneratorUtil;

@Service
public class InvoicePDFGeneratorService {

	
	private final PDFGeneratorUtil pdfGeneratorUtil;	
	private final GeneratedInvoiceRepository generatedInvoiceRepository;
	
	//Context context = new Context();
	private final SpringTemplateEngine templateEngine;
	
	public InvoicePDFGeneratorService(SpringTemplateEngine templateEngine,PDFGeneratorUtil pdfGeneratorUtil, 
            GeneratedInvoiceRepository generatedInvoiceRepository) {
        this.pdfGeneratorUtil = pdfGeneratorUtil;
		this.generatedInvoiceRepository = generatedInvoiceRepository;
		this.templateEngine = templateEngine;
	
    }
		
	
	public Resource getPdfbyId(String id) throws IOException
	{
		Optional<GeneratedInvoice> existingInvoice = generatedInvoiceRepository.findById(id);
		if(existingInvoice.isPresent())
		{
			Path path = Paths.get(existingInvoice.get().getInvoicePath());
			Resource resource = new UrlResource(path.toUri());
			return resource;
		}
		return null;
	}
	
	
	
	public Resource generatePdf(Model model, String filePath) throws FileNotFoundException, MalformedURLException {
		
		 	Context context = new Context();
	        model.asMap().forEach(context::setVariable);
	        String htmlContent = templateEngine.process("invoice_template", context);
	        Resource resource = new UrlResource(pdfGeneratorUtil.generatePdfFromHtml(htmlContent, filePath).toUri());
	        return resource;
	}        
	
	public Resource generateAndInsertPdf(Model model, String id, String invoicePath) throws FileNotFoundException, MalformedURLException
	{
		GeneratedInvoice invoice = new GeneratedInvoice(id, invoicePath);
		generatedInvoiceRepository.save(invoice);
		return generatePdf(model, invoicePath);
	}
	
	public static String generateInvoiceId(String buyerGstin, String sellerGstin, List<Item> items) {
        
		String itemsString = items.stream().sorted((i1, i2) -> i1.getItemName().compareTo(i2.getItemName())).map(item -> item.getItemName() + "-" + item.getItemQuantity() + "-" + item.getItemRate() + "-" + item.getItemTotalAmount()).collect(Collectors.joining("_"));
        String dataString = buyerGstin + "-" + sellerGstin + "-" + itemsString;

        return hashString(dataString);
    }
	private static String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }





	public Resource getPdfInvoiceStream(Invoice invoice) throws IOException {
		
		String invoiceId = generateInvoiceId(invoice.getBuyerGstin(),  invoice.getSellerGstin(), invoice.getItems());
		Resource pdfContentResource= getPdfbyId(invoiceId);
		if (pdfContentResource!=null)
			return pdfContentResource;
		Model model = new ExtendedModelMap();
        model.addAttribute("invoice", invoice);
        String invoicePath = createInvoicePath(invoiceId);
        pdfContentResource = generateAndInsertPdf(model, invoiceId, invoicePath);
		
		return pdfContentResource;
	}


	private String createInvoicePath(String invoiceId) {
		ApplicationHome applicationHome = new ApplicationHome(PdfGenApplication.class);
		String jarFolder = applicationHome.getDir().getAbsolutePath();
		
		return "Invoice_"+invoiceId.toString()+".pdf";
	}
	        
	



	
	

}
