package com.ecommercevcs.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    
    @GetMapping("/monthly-sales/pdf")
    public ResponseEntity<byte[]> generateMonthlySalesPDF() {
        return generateMonthlySalesPDFForMonth(null, null);
    }

    /**
     * Formato year/month: (2025/04)
     */
    @GetMapping("/monthly-sales/pdf/{year}/{month}")
    public ResponseEntity<byte[]> generateMonthlySalesPDFForMonth(
            @PathVariable(required = false) Integer year,
            @PathVariable(required = false) Integer month) {
        
        try {
            byte[] pdfBytes = reportService.generateMonthlySalesPdfReport(year, month);
            
            // Determinar año/mes para el nombre del archivo
            LocalDate now = LocalDate.now();
            int reportYear = (year != null) ? year : now.getYear();
            int reportMonth = (month != null) ? month : now.getMonthValue();
            
            String fileName = "informe_mensual_ventas_" + reportYear + "_" + 
                              String.format("%02d", reportMonth) + ".pdf";
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentLength(pdfBytes.length);
            
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            logger.error("Error al generar el informe mensual PDF", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/monthly-sales/excel")
    public ResponseEntity<byte[]> generateMonthlySalesExcel() {
        return generateMonthlySalesExcelForMonth(null, null);
    }

    @GetMapping("/monthly-sales/excel/{year}/{month}")
    public ResponseEntity<byte[]> generateMonthlySalesExcelForMonth(
            @PathVariable(required = false) Integer year,
            @PathVariable(required = false) Integer month) {
        
        try {
            byte[] excelBytes = reportService.generateMonthlySalesExcelReport(year, month);
            
            LocalDate now = LocalDate.now();
            int reportYear = (year != null) ? year : now.getYear();
            int reportMonth = (month != null) ? month : now.getMonthValue();
            
            String fileName = "informe_mensual_ventas_" + reportYear + "_" + 
                              String.format("%02d", reportMonth) + ".xlsx";
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentLength(excelBytes.length);
            
            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            logger.error("Error al generar el informe mensual Excel", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/daily-sales/pdf")
    public ResponseEntity<byte[]> generateDailySalesPDF() {
        return generateDailySalesPDFForDate(null);
    }
    
    /**
     * Formato de fecha esperado: YYYY-MM-DD
     */
    @GetMapping("/daily-sales/pdf/{date}")
    public ResponseEntity<byte[]> generateDailySalesPDFForDate(
            @PathVariable(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        try {
            byte[] pdfBytes = reportService.generateDailySalesPdfReport(date);
            
            LocalDate reportDate = (date != null) ? date : LocalDate.now();
            String fileName = "informe_ventas_" + reportDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + ".pdf";
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentLength(pdfBytes.length);
            
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            logger.error("Error al generar el informe PDF", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/daily-sales/excel")
    public ResponseEntity<byte[]> generateDailySalesExcel() {
        return generateDailySalesExcelForDate(null);
    }
    
    /**
     * Formato de fecha esperado: YYYY-MM-DD
     */
    @GetMapping("/daily-sales/excel/{date}")
    public ResponseEntity<byte[]> generateDailySalesExcelForDate(
            @PathVariable(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        try {
            byte[] excelBytes = reportService.generateDailySalesExcelReport(date);
            
            LocalDate reportDate = (date != null) ? date : LocalDate.now();
            String fileName = "informe_ventas_" + reportDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + ".xlsx";
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentLength(excelBytes.length);
            
            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            logger.error("Error al generar el informe Excel", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
