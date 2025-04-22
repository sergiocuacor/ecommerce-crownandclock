package com.ecommercevcs.services;

import java.time.LocalDate;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {
	
	byte[] generateDailySalesPdfReport(LocalDate date) throws  Exception;
	byte[] generateDailySalesExcelReport(LocalDate date) throws Exception;
	byte[] generateMonthlySalesPdfReport(Integer year, Integer month) throws Exception;
    byte[] generateMonthlySalesExcelReport(Integer year, Integer month) throws Exception;
}
