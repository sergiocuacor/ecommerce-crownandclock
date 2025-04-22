package com.ecommercevcs.services.impl;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ecommercevcs.entities.OrderDetailsEntity;
import com.ecommercevcs.entities.OrderEntity;
import com.ecommercevcs.repositories.OrderRepository;
import com.ecommercevcs.services.ReportService;
import com.ecommercevcs.utils.reports.JasperReportCompiler;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class ReportServiceImpl implements ReportService {

	private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

	private final OrderRepository orderRepository;
	private final JasperReportCompiler reportCompiler;

	public ReportServiceImpl(OrderRepository orderRepository, JasperReportCompiler reportCompiler) {
		this.orderRepository = orderRepository;
		this.reportCompiler = reportCompiler;

	}
	
	public byte[] generateMonthlySalesPdfReport(Integer year, Integer month) throws Exception {
	    JasperPrint jasperPrint = createMonthlyJasperPrint(year, month);
	    return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	public byte[] generateMonthlySalesExcelReport(Integer year, Integer month) throws Exception {
	    JasperPrint jasperPrint = createMonthlyJasperPrint(year, month);

	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    JRXlsxExporter exporter = new JRXlsxExporter();

	    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

	    SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
	    configuration.setOnePagePerSheet(false);
	    configuration.setRemoveEmptySpaceBetweenColumns(true);
	    configuration.setDetectCellType(true);
	    exporter.setConfiguration(configuration);

	    exporter.exportReport();

	    return outputStream.toByteArray();
	}

	public byte[] generateDailySalesPdfReport(LocalDate date) throws Exception {
		JasperPrint jasperPrint = createJasperPrint(date);
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	public byte[] generateDailySalesExcelReport(LocalDate date) throws Exception {
		JasperPrint jasperPrint = createJasperPrint(date);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		JRXlsxExporter exporter = new JRXlsxExporter();

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
		configuration.setOnePagePerSheet(false);
		configuration.setRemoveEmptySpaceBetweenColumns(true);
		configuration.setDetectCellType(true);
		exporter.setConfiguration(configuration);

		exporter.exportReport();

		return outputStream.toByteArray();
	}

	private JasperPrint createMonthlyJasperPrint(Integer year, Integer month) throws Exception {
	    
	    LocalDate now = LocalDate.now();
	    int reportYear = (year != null) ? year : now.getYear();
	    int reportMonth = (month != null) ? month : now.getMonthValue();
	    
	    
	    LocalDate firstDayOfMonth = LocalDate.of(reportYear, reportMonth, 1);
	    LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
	    
	    
	    LocalDate firstDayOfPreviousMonth = firstDayOfMonth.minusMonths(1);
	    LocalDate lastDayOfPreviousMonth = firstDayOfPreviousMonth.withDayOfMonth(
	            firstDayOfPreviousMonth.lengthOfMonth());
	    
	    
	    LocalDateTime startOfMonth = firstDayOfMonth.atStartOfDay();
	    LocalDateTime endOfMonth = lastDayOfMonth.atTime(23, 59, 59);
	    LocalDateTime startOfPreviousMonth = firstDayOfPreviousMonth.atStartOfDay();
	    LocalDateTime endOfPreviousMonth = lastDayOfPreviousMonth.atTime(23, 59, 59);
	    
	    
	    List<OrderEntity> currentMonthOrders = orderRepository.findByOrderDateBetween(startOfMonth, endOfMonth);
	    List<OrderEntity> previousMonthOrders = orderRepository.findByOrderDateBetween(startOfPreviousMonth, endOfPreviousMonth);
	    
	    logger.info("Generando informe mensual para: {}/{} con {} órdenes", 
	            reportYear, reportMonth, currentMonthOrders.size());
	    
	    
	    double totalSales = 0.0;
	    double previousMonthTotalSales = 0.0;
	    
	    
	    List<SalesReportItem> productSales = new ArrayList<>();
	    Map<String, SalesReportItem> productMap = new HashMap<>();
	    
	    
	    Map<Integer, Double> salesByDay = new HashMap<>();
	    
	    for (int day = 1; day <= lastDayOfMonth.getDayOfMonth(); day++) {
	        salesByDay.put(day, 0.0);
	    }
	    
	    
	    for (OrderEntity order : currentMonthOrders) {
	        totalSales += order.getTotal();
	        
	        
	        int day = order.getOrderDate().getDayOfMonth();
	        salesByDay.put(day, salesByDay.getOrDefault(day, 0.0) + order.getTotal());
	        
	        
	        for (OrderDetailsEntity detail : order.getOrderDetails()) {
	            String productName = detail.getProduct().getName();
	            double amount = detail.getSubTotal();
	            int quantity = detail.getQuantity();
	            
	            SalesReportItem item = productMap.getOrDefault(
	                    productName, new SalesReportItem(productName, 0, 0.0));
	            item.addQuantity(quantity);
	            item.addAmount(amount);
	            
	            productMap.put(productName, item);
	        }
	    }
	    
	    
	    productSales.addAll(productMap.values());
	    productSales.sort(Comparator.comparing(SalesReportItem::getAmount).reversed());
	    
	    
	    for (OrderEntity order : previousMonthOrders) {
	        previousMonthTotalSales += order.getTotal();
	    }
	    
	   
	    double salesVariation = 0.0;
	    if (previousMonthTotalSales > 0) {
	        salesVariation = ((totalSales - previousMonthTotalSales) / previousMonthTotalSales) * 100;
	    }
	    
	    
	    List<DailySalesItem> dailyData = new ArrayList<>();
	    for (int day = 1; day <= lastDayOfMonth.getDayOfMonth(); day++) {
	        String dayLabel = String.valueOf(day);
	        double amount = salesByDay.getOrDefault(day, 0.0);
	        dailyData.add(new DailySalesItem(dayLabel, amount));
	    }
	    
	    
	    JasperReport mainReport = reportCompiler.getCompiledReport("MonthlySalesReport");
	    JasperReport productsSubreport = reportCompiler.getCompiledReport("ProductsSubreport");
	    JasperReport dailySubreport = reportCompiler.getCompiledReport("DailyTrendSubreport");
	    
	    String[] monthNames = {
	            "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", 
	            "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"
	        };
	        String monthName = monthNames[reportMonth - 1];
	        
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("ReportTitle", "Informe de Ventas Mensuales");
	    parameters.put("ReportMonth", reportMonth);
	    parameters.put("ReportYear", reportYear);
	    parameters.put("MonthName", monthName);
	    parameters.put("TotalSales", totalSales);
	    parameters.put("OrderCount", currentMonthOrders.size());
	    parameters.put("AverageTicket", currentMonthOrders.size() > 0 ? totalSales / currentMonthOrders.size() : 0);
	    parameters.put("PreviousMonthSales", previousMonthTotalSales);
	    parameters.put("SalesVariation", salesVariation);
	    parameters.put("TOTAL_MONTHLY_SALES", totalSales);
	    String reportFolder = "/reports/";
	    parameters.put("SUBREPORT_DIR", reportFolder);
	    
	    parameters.put("ProductsSubreport", productsSubreport);
	    parameters.put("DailyTrendSubreport", dailySubreport);
	    
	    JRBeanCollectionDataSource productDataSource = new JRBeanCollectionDataSource(productSales);
	    parameters.put("ProductDataSource", productDataSource);
	    
	    JRBeanCollectionDataSource dailyDataSource = new JRBeanCollectionDataSource(dailyData);
	    parameters.put("DailyDataSource", dailyDataSource);
	    
	    // Generar informe
	    try {
	        return JasperFillManager.fillReport(mainReport, parameters, new JREmptyDataSource());
	    } catch (Exception e) {
	        logger.error("Error al generar el informe mensual: {}", e.getMessage());
	        throw new Exception("Error al generar el informe mensual: " + e.getMessage(), e);
	    }
	}
	
	private JasperPrint createJasperPrint(LocalDate date) throws Exception {
		// Si no le pasa fecha, usar la actual
		LocalDate reportDate = (date != null) ? date : LocalDate.now();
		LocalDate previousDate = reportDate.minusDays(1);

		LocalDateTime startOfDay = reportDate.atStartOfDay();
		LocalDateTime endOfDay = reportDate.atTime(23, 59, 59);
		LocalDateTime startOfPreviousDay = previousDate.atStartOfDay();
		LocalDateTime endOfPreviousDay = previousDate.atTime(23, 59, 59);

		List<OrderEntity> currentDayOrders = orderRepository.findByOrderDateBetween(startOfDay, endOfDay);
		List<OrderEntity> previousDayOrders = orderRepository.findByOrderDateBetween(startOfPreviousDay,
				endOfPreviousDay);

		logger.info("Generando informe de ventas para la fecha: {} con {} órdenes", reportDate,
				currentDayOrders.size());

		List<SalesReportItem> productSales = new ArrayList<>();
		Map<Integer, Double> salesByHour = new HashMap<>();

		for (int i = 0; i < 24; i++) {
			salesByHour.put(i, 0.0);
		}

		double totalSales = 0.0;
		double previousDayTotalSales = 0.0;

		// Pedidos del día
		Map<String, SalesReportItem> productMap = new HashMap<>();
		for (OrderEntity order : currentDayOrders) {
			totalSales += order.getTotal();

			int hour = order.getOrderDate().getHour();
			salesByHour.put(hour, salesByHour.getOrDefault(hour, 0.0) + order.getTotal());

			for (OrderDetailsEntity detail : order.getOrderDetails()) {
				String productName = detail.getProduct().getName();
				double amount = detail.getSubTotal();
				int quantity = detail.getQuantity();

				SalesReportItem item = productMap.getOrDefault(productName, new SalesReportItem(productName, 0, 0.0));
				item.addQuantity(quantity);
				item.addAmount(amount);

				productMap.put(productName, item);
			}
		}

		productSales.addAll(productMap.values());

		productSales.sort(Comparator.comparing(SalesReportItem::getAmount).reversed());

		for (OrderEntity order : previousDayOrders) {
			previousDayTotalSales += order.getTotal();
		}

		double salesVariation = 0.0;
		if (previousDayTotalSales > 0) {
			salesVariation = ((totalSales - previousDayTotalSales) / previousDayTotalSales) * 100;
		}

		
		List<HourlySalesItem> hourlyData = new ArrayList<>();
		for (int hour = 0; hour < 24; hour++) {
			String hourLabel = String.format("%02d:00 - %02d:59", hour, hour);
			double amount = salesByHour.getOrDefault(hour, 0.0);
			hourlyData.add(new HourlySalesItem(hourLabel, amount));
		}

		JasperReport mainReport = reportCompiler.getCompiledReport("SalesReport");
		JasperReport productsSubreport = reportCompiler.getCompiledReport("ProductsSubreport");
		JasperReport hourlySubreport = reportCompiler.getCompiledReport("HourlySubreport");

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("ReportTitle", "Informe de Ventas Diarias");
		parameters.put("ReportDate", java.sql.Date.valueOf(reportDate));
		parameters.put("TotalSales", totalSales);
		parameters.put("OrderCount", currentDayOrders.size());
		parameters.put("AverageTicket", currentDayOrders.size() > 0 ? totalSales / currentDayOrders.size() : 0);
		parameters.put("PreviousDaySales", previousDayTotalSales);
		parameters.put("SalesVariation", salesVariation);

		String reportFolder = "/reports/";
		parameters.put("SUBREPORT_DIR", reportFolder);

		parameters.put("ProductsSubreport", productsSubreport);
		parameters.put("HourlySubreport", hourlySubreport);

		JRBeanCollectionDataSource productDataSource = new JRBeanCollectionDataSource(productSales);
		parameters.put("ProductDataSource", productDataSource);

		JRBeanCollectionDataSource hourlyDataSource = new JRBeanCollectionDataSource(hourlyData);
		parameters.put("HourlyDataSource", hourlyDataSource);

		// Generar el informe
		try {
			return JasperFillManager.fillReport(mainReport, parameters, new JREmptyDataSource());
		} catch (Exception e) {
			logger.error("Error al generar el informe: {}", e.getMessage());
			throw new Exception("Error al generar el informe: " + e.getMessage(), e);
		}
	}

	// datos de ventas por producto
	public static class SalesReportItem {
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

	// datos de ventas por hora
	public static class HourlySalesItem {
		private String hourRange;
		private double sales;

		public HourlySalesItem(String hourRange, double sales) {
			this.hourRange = hourRange;
			this.sales = sales;
		}

		public String getHourRange() {
			return hourRange;
		}

		public double getSales() {
			return sales;
		}
	}
	
	// clase para los datos de ventas diarias (para el informe mensual)
		public static class DailySalesItem {
		    private String day;
		    private double sales;

		    public DailySalesItem(String day, double sales) {
		        this.day = day;
		        this.sales = sales;
		    }

		    public String getDay() {
		        return day;
		    }

		    public double getSales() {
		        return sales;
		    }
		}
}