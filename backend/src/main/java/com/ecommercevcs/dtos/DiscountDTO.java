package com.ecommercevcs.dtos;

public class DiscountDTO {
	private String name;
	private Integer discountPertenage;
	
	
	
	public DiscountDTO(String name, Integer discountPertenage) {
		super();
		this.name = name;
		this.discountPertenage = discountPertenage;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDiscountPertenage() {
		return discountPertenage;
	}
	public void setDiscountPertenage(Integer discountPertenage) {
		this.discountPertenage = discountPertenage;
	}
	
	
}
