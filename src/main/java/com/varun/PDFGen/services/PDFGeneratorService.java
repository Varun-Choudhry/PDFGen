package com.varun.PDFGen.services;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public interface PDFGeneratorService<T> {
	String generatePdf(T data);
	String parsePdfInput(Context context);
	
}
