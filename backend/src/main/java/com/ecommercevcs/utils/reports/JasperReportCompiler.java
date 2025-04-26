package com.ecommercevcs.utils.reports;

import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

@Component
public class JasperReportCompiler {

    private static final Logger logger = LoggerFactory.getLogger(JasperReportCompiler.class);
    
    
    private final ConcurrentHashMap<String, JasperReport> reportCache = new ConcurrentHashMap<>();
    
    public JasperReport getCompiledReport(String reportName) throws JRException {
        return reportCache.computeIfAbsent(reportName, this::compileReport);
    }
    
    private JasperReport compileReport(String reportName) {
        try {
            String reportPath = "/reports/" + reportName + ".jrxml";
            InputStream reportStream = getClass().getResourceAsStream(reportPath);
            if (reportStream == null) {
                throw new JRException("No se pudo encontrar el archivo: " + reportPath);
            }
            logger.info("Compilando informe: {}", reportName);
            return JasperCompileManager.compileReport(reportStream);
        } catch (JRException e) {
            logger.error("Error compilando informe {}: {}", reportName, e.getMessage());
            throw new RuntimeException("Error compilando informe: " + reportName, e);
        }
    }
}
