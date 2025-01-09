package com.ecommercevcs.dtos;

import com.ecommercevcs.entities.ProductEntity;

public class OrderCreateItemDTO {

	private Long productId;
	private Integer quantity;
	
	
	public OrderCreateItemDTO() {
		super();
	}



	public OrderCreateItemDTO(Long productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		
	}
	
	
	
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



	


	
	
	
	
}
