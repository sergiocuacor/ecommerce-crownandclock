package com.ecommercevcs.utils.email;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ecommercevcs.dtos.EmailOrderDTO;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
	
	@Autowired
    private JavaMailSender emailSender;
 	
 	@Autowired
    private TemplateEngine templateEngine;
 	 
 	 public void sendHtmlEmailWelcome(String username,String discount, String to, String subject, String templateName) throws MessagingException {
	    	
	    	MimeMessage message = emailSender.createMimeMessage();
	    	MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
	    	
	    	helper.setFrom("carlosrose96@gmail.com");
	    	helper.setTo(to); 
	    	helper.setSubject(subject); 
	    	Context context = new Context();
	        context.setVariable("name", username); 
	        context.setVariable("discount", discount);
	        String htmlContent = templateEngine.process(templateName, context);
	    	
	    	helper.setText(htmlContent, true);
	    	
	    	emailSender.send(message);
	    }
 	public void sendHtmlEmailOrder(String username, List<EmailOrderDTO> products, String to, String subject, String templateName) throws MessagingException {
    	
    	MimeMessage message = emailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
    	
    	helper.setFrom("carlosrose96@gmail.com");
    	helper.setTo(to); 
    	helper.setSubject(subject);
    	
    	
    	Context context = new Context();
        context.setVariable("name", username); 
        context.setVariable("products", products);
        String htmlContent = templateEngine.process(templateName, context);
    	
    	helper.setText(htmlContent, true);
    	
    	emailSender.send(message);
    }
 	
	
}
