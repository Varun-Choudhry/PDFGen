package com.varun.PDFGen.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.varun.PDFGen.model.GeneratedInvoice;

@Repository
public interface  GeneratedInvoiceRepository extends CrudRepository<GeneratedInvoice, String>{

	
}
