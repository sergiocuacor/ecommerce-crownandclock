package com.ecommercevcs.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercevcs.services.ReportService;



@RestController
@RequestMapping("/reports")
public class ReportController {

	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/daily-sales")
    public ResponseEntity<byte[]> generateDailySalesReport() {
        return generateDailySalesReportForDate(null);
    }
	
	/*
	 * Formato {date}: YYYY-MM-dd
	 */
	@GetMapping("/daily-sales/{date}")
	public ResponseEntity<byte[]> generateDailySalesReportForDate(
	        @PathVariable(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
	    
	    try {
	        Workbook workbook = reportService.generateDailySalesReport(date);
	        
	        LocalDate reportDate = (date != null) ? date : LocalDate.now();
	        String fileName = "informe_ventas_" + reportDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + ".xlsx";
	        
	        // Cambiar a byte[] directamente
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        workbook.write(baos);
	        byte[] bytes = baos.toByteArray();
	        workbook.close();
	        baos.close();
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.setContentDisposition(ContentDisposition.attachment().filename(fileName).build());
	        
	        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
	        
	    } catch (IOException e) {
	        logger.error("Error al generar el informe de ventas", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
}
