package com.ecommercevcs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercevcs.dtos.OrderCreateDTO;
import com.ecommercevcs.dtos.OrderCreateItemDTO;
import com.ecommercevcs.entities.OrderDetailsEntity;
import com.ecommercevcs.entities.OrderEntity;
import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.entities.UserEntity;
import com.ecommercevcs.repositories.OrderRepository;
import com.ecommercevcs.repositories.ProductRepositoryJPA;
import com.ecommercevcs.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements IOrderService{

	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepositoryJPA productRepository;
	
	@Override
	public List<OrderEntity> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public OrderEntity findById(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Order with id "+ id+ " not found."));
	}

	@Override
	@Transactional
	public OrderEntity add(OrderCreateDTO orderCreateDTO) {
		//Instanciamos order entity
		OrderEntity order = new OrderEntity();
		UserEntity user = userRepository.findById(orderCreateDTO.getUserId()).orElseThrow(()-> new EntityNotFoundException());
		order.setUser(user); 
		
		
		Double totalOrder = 0.0;
		
		for(OrderCreateItemDTO orderItem : orderCreateDTO.getItems()) {
			OrderDetailsEntity orderDetails = new OrderDetailsEntity();
			ProductEntity product = productRepository.findById(orderItem.getProductId()).orElseThrow(()-> new EntityNotFoundException("Product with id "+ orderItem.getProductId()+" not found"));
			
			orderDetails.setProduct(product);
			// esto deberia ser removeStock (hacer helper methods)
			product.setStock(product.getStock()- orderItem.getQuantity());
			
			orderDetails.setQuantity(orderItem.getQuantity()); 
			if(product.getPrice() != orderItem.getUnitPrice()) {
				new Exception("Price of product and item are different.");
			}
			orderDetails.setUnitPrice(product.getPrice());

			orderDetails.setSubTotal(product.getPrice()*orderItem.getQuantity());
			totalOrder += orderDetails.getSubTotal();
			order.addOrderDetail(orderDetails);
		}
		
		
		order.setTotal(totalOrder);
	
		
		
		
		return orderRepository.save(order);
	}

	@Override
	public OrderEntity update(OrderEntity order, Long id) {
		OrderEntity existingOrder= orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Order with id "+ id+ " not found."));
		
		existingOrder.setStatus(order.getStatus());
		existingOrder.setTotal(order.getTotal());
		return orderRepository.save(existingOrder);
	}

	@Override
	public void deleteById(Long id) {
		OrderEntity existingOrder = orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Order with id "+ id+ " not found."));

		orderRepository.deleteById(id);
	}

	
	
}
