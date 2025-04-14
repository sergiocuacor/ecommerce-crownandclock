package com.ecommercevcs.dtos;

public class BrandDTO {

	private Long id;
	private String name;
	private String description;
	private Integer numberOfProducts;
	public BrandDTO(Long id, String name, String description, Integer numberOfProducts) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.numberOfProducts = numberOfProducts;
	}
	public BrandDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getNumberOfProducts() {
		return numberOfProducts;
	}
	public void setNumberOfProducts(Integer numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}
	
	
	
}
