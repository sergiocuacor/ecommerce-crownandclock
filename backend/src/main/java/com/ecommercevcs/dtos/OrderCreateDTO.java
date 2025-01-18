package com.ecommercevcs.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class OrderCreateDTO {

	private Long userId;
	private List<OrderCreateItemDTO> items;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String discount;
	
	
	public OrderCreateDTO() {
		super();
	}
	
	
	
	public OrderCreateDTO(Long userId, List<OrderCreateItemDTO> items, String discount) {
		super();
		this.userId = userId;
		this.items = items;
		this.discount = discount;
	}



	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<OrderCreateItemDTO> getItems() {
		return items;
	}
	public void setItems(List<OrderCreateItemDTO> items) {
		this.items = items;
	}


	public String getDiscount() {
		return discount;
	}


	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	
	
	
	
	
}
