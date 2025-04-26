package com.ecommercevcs.dtos;

public class ProductSimpleDTO {
	
	private Long productId;
	private String productMask;
	private String productName;
	
	
	public ProductSimpleDTO(Long productId, String productMask, String productName) {
		super();
		this.productId = productId;
		this.productMask = productMask;
		this.productName = productName;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductMask() {
		return productMask;
	}
	public void setProductMask(String productMask) {
		this.productMask = productMask;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
