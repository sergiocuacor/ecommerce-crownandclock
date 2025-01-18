package com.ecommercevcs.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ecommercevcs.dtos.DiscountDTO;

import jakarta.annotation.PostConstruct;

@Component
public class ConfigDiscount {

	private List<DiscountDTO> discounts = new ArrayList<DiscountDTO>();

	public List<DiscountDTO> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<DiscountDTO> discounts) {
		this.discounts = discounts;
	}
	
	@PostConstruct
	public void getDiscountsForFile() throws IOException {
		
		String fileDiscounts = "src/main/resources/discounts.txt";
		
		BufferedReader br = new BufferedReader(new FileReader(fileDiscounts));
		
		String line;
		
		while((line = br.readLine()) != null) {
			String [] data = line.split(",");
			System.out.println(data[0]);
			System.out.println(data[1]);
			discounts.add(new DiscountDTO(data[0], Integer.parseInt(data[1])));
			
		}
		
		for(DiscountDTO d : this.discounts) {
			System.out.println("name: " + d.getName() + " discount: " + d.getDiscountPertenage());
		}
		
	}
}
