package com.varun.PDFGen.services;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface PDFGeneratorService<T> {
	byte[] generatePdf(Model model) throws FileNotFoundException;
	
	
}
