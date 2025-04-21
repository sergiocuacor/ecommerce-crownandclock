package com.ecommercevcs.services;

import java.time.LocalDate;

import org.apache.poi.ss.usermodel.Workbook;

public interface ReportService {
	
	Workbook generateDailySalesReport(LocalDate date);
	
}
