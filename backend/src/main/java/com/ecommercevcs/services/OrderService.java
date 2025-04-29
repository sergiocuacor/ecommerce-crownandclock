package com.ecommercevcs.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.ecommercevcs.dtos.DiscountDTO;
import com.ecommercevcs.dtos.OrderCreateDTO;
import com.ecommercevcs.entities.OrderEntity;

import jakarta.mail.MessagingException;

public interface OrderService {

	public List<OrderEntity> findAll();
	public OrderEntity findById(Long id);
	public OrderEntity add(OrderCreateDTO order);
	public OrderEntity update(OrderEntity order, Long id);
	public void deleteById(Long id);
	List<?> findAllDiscountNamesAppliedByUser(@Param("userId") Long userId);
	public void sendMailToUser(OrderCreateDTO order) throws MessagingException;
}
