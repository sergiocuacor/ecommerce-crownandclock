package com.ecommercevcs.services;

import java.util.List;

import com.ecommercevcs.dtos.OrderCreateDTO;
import com.ecommercevcs.entities.OrderEntity;

public interface IOrderService {

	public List<OrderEntity> findAll();
	public OrderEntity findById(Long id);
	public OrderEntity add(OrderCreateDTO order);
	public OrderEntity update(OrderEntity order, Long id);
	public void deleteById(Long id);
	
}
