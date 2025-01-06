package com.ecommercevcs.dtos;

import java.util.List;

public class OrderCreateDTO {

	private Long userId;
	private List<OrderCreateItemDTO> items;
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
	public OrderCreateDTO(Long userId, List<OrderCreateItemDTO> items) {
		super();
		this.userId = userId;
		this.items = items;
	}
	public OrderCreateDTO() {
		super();
	}
	
	
	
}
