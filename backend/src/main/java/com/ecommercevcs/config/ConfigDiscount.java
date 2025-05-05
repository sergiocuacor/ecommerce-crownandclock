package com.ecommercevcs.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.ecommercevcs.dtos.DiscountDTO;

import jakarta.annotation.PostConstruct;

@Component
public class ConfigDiscount implements ApplicationRunner {
	
	private final ResourceLoader resourceLoader;
	
	
	private List<DiscountDTO> discounts = new ArrayList<DiscountDTO>();
	
	
	public ConfigDiscount(ResourceLoader resourceLoader) {
		super();
		this.resourceLoader = resourceLoader;
	}

	public List<DiscountDTO> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<DiscountDTO> discounts) {
		this.discounts = discounts;
	}
	

		
		

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Resource resource = resourceLoader.getResource("classpath:discounts.txt");
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
		
		String line;
		
		while((line = br.readLine()) != null) {
			String [] data = line.split(",");
			System.out.println(data[0]);
			System.out.println(data[1]);
			discounts.add(new DiscountDTO(data[0], Integer.parseInt(data[1])));
			
		}
		
		for(DiscountDTO d : this.discounts) {
			System.out.println("name: " + d.getName() + " discount: " + d.getDiscountPercentage());
		}
		
	}
		
	}

