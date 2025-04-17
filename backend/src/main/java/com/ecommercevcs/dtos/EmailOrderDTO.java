package com.ecommercevcs.dtos;

public class EmailOrderDTO {
	
	public String name;
	public String mask;
	public Integer quantity;
	
	
	
	public EmailOrderDTO(String name, String mask, Integer quantity) {
		super();
		this.name = name;
		this.mask = mask;
		this.quantity = quantity;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMask() {
		return mask;
	}
	public void setMask(String mask) {
		this.mask = mask;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
