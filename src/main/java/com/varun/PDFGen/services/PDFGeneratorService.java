package com.varun.PDFGen.services;

public interface PDFGeneratorService<T> {
	String generatePDF(T data);
}
