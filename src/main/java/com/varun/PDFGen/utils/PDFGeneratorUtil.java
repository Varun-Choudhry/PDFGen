package com.varun.PDFGen.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;

@Component
public class PDFGeneratorUtil {
	
	public byte[] generatePdfFromHtml(String htmlContent, String filePath) {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            
            renderer.setDocumentFromString(htmlContent);
            renderer.getSharedContext().setDPI(1110);
            renderer.layout();
            renderer.createPDF(outputStream);
            
            writePdfToFile(outputStream, filePath);

            return outputStream.toByteArray();
            
        } catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	  private static void writePdfToFile(ByteArrayOutputStream pdfStream, String filePath) throws IOException {
	        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
	            pdfStream.writeTo(fos);
	            fos.flush();
	        }
	    }
}	