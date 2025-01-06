package com.ecommercevcs.dtos;

import com.ecommercevcs.entities.ProductEntity;

public class OrderCreateItemDTO {

	private Long productId;
	private Integer quantity;
	private Double unitPrice;
	
	
	
	public Long getProductId() {
		return productId;
	}



	public void setProductId(Long productId) {
		this.productId = productId;
	}



	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	public Double getUnitPrice() {
		return unitPrice;
	}



	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}



	public OrderCreateItemDTO() {
		super();
	}



	public OrderCreateItemDTO(Long productId, Integer quantity, Double unitPrice) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	
	
	
}
