package com.varun.PDFGen.utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;

@Component
public class PDFGeneratorUtil {
	
	public void generatePdfFromHtml(String html, String fileType) throws DocumentException, IOException
	{
	    String outputFolder = "thymeleaf.pdf";
	    FileOutputStream outputStream = new FileOutputStream(outputFolder);

	    ITextRenderer renderer = new ITextRenderer();
	    renderer.setDocumentFromString(html);
	    renderer.layout();
	    renderer.createPDF(outputStream);

	    outputStream.close();
	}
}	