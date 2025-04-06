package com.ecommercevcs.dtos;

public class DiscountDTO {
	private String name;
	private Integer discountPercentage;
	
	
	
	public DiscountDTO(String name, Integer discountPercentage) {
		super();
		this.name = name;
		this.discountPercentage = discountPercentage;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPertenage(Integer discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
	
}
