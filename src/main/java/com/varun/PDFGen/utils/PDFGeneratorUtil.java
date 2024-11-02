package com.varun.PDFGen.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;

@Component
public class PDFGeneratorUtil {
	
	public Path generatePdfFromHtml(String htmlContent, String filePath) {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
            
            return writePdfToFile(outputStream, filePath);

        } catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	private static Path writePdfToFile(ByteArrayOutputStream pdfStream, String filePath) throws IOException {
	    File file = new File(filePath);   
		try (FileOutputStream fos = new FileOutputStream(file)) {
	            pdfStream.writeTo(fos);
	            fos.flush();
	        }
	   return Paths.get(file.getAbsolutePath());
	}
}	