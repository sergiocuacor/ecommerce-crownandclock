package com.ecommercevcs.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercevcs.entities.OrderDetailsEntity;
import com.ecommercevcs.entities.OrderEntity;
import com.ecommercevcs.repositories.OrderRepository;

@Service
public class ReportServiceImpl implements ReportService{

	private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Autowired
	private OrderRepository orderRepository;

	public Workbook generateDailySalesReport(LocalDate date) {

		LocalDate reportDate = (date != null) ? date : LocalDate.now();

		LocalDateTime startOfDay = reportDate.atStartOfDay();
		LocalDateTime endOfDay = reportDate.atTime(23, 59, 59);

		List<OrderEntity> orders = orderRepository.findByOrderDateBetween(startOfDay, endOfDay);

		logger.info("Generando informe de ventas para el día: {}", reportDate);

		Map<String, SalesReportItem> salesByProduct = new HashMap<String, SalesReportItem>();

		double totalSales = 0.0;

		for (OrderEntity order : orders) {

			totalSales += order.getTotal();

			for (OrderDetailsEntity detail : order.getOrderDetails()) {
				String productName = detail.getProduct().getName();
				double amount = detail.getSubTotal();
				int quantity = detail.getQuantity();

				SalesReportItem item = salesByProduct.getOrDefault(productName,
						new SalesReportItem(productName, 0, 0.0));

				item.addQuantity(quantity);
				item.addAmount(amount);
				salesByProduct.put(productName, item);
			}

		}
		return createExcelWorkbook(salesByProduct.values(), totalSales, reportDate);
	}

	private Workbook createExcelWorkbook(Collection<SalesReportItem> salesItems, double totalSales,
			LocalDate reportDate) {
		Workbook workbook = new HSSFWorkbook();
		
		Sheet sheet = workbook.createSheet("Informe de ventas");
		
	
		CellStyle headerStyle = workbook.createCellStyle();
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerStyle.setFont(headerFont);
		
		CellStyle totalStyle = workbook.createCellStyle();
        Font totalFont = workbook.createFont();
        totalFont.setBold(true);
        totalStyle.setFont(totalFont);
        
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("INFORME DE VENTAS - " + reportDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        titleCell.setCellStyle(headerStyle);
        
        
        Row headerRow = sheet.createRow(2);
        String[] headers = {"Producto", "Cantidad Vendida", "Importe Total (€)"};
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
        
        
        int rowNum = 3;
        for (SalesReportItem item : salesItems) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getProductName());
            row.createCell(1).setCellValue(item.getQuantity());
            row.createCell(2).setCellValue(item.getAmount());
        }
        
        
        Row totalRow = sheet.createRow(rowNum + 1);
        Cell labelCell = totalRow.createCell(0);
        labelCell.setCellValue("TOTAL VENTAS:");
        labelCell.setCellStyle(totalStyle);
        
        Cell totalCell = totalRow.createCell(2);
        totalCell.setCellValue(totalSales);
        totalCell.setCellStyle(totalStyle);
        
       
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        return workbook;
        
	}
	
	private static class SalesReportItem {
        private String productName;
        private int quantity;
        private double amount;
        
        public SalesReportItem(String productName, int quantity, double amount) {
            this.productName = productName;
            this.quantity = quantity;
            this.amount = amount;
        }
        
        public void addQuantity(int quantity) {
            this.quantity += quantity;
        }
        
        public void addAmount(double amount) {
            this.amount += amount;
        }
        
        public String getProductName() {
            return productName;
        }
        
        public int getQuantity() {
            return quantity;
        }
        
        public double getAmount() {
            return amount;
        }
    }
}
