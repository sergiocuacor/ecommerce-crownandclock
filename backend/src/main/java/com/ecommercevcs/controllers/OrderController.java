package com.ecommercevcs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercevcs.dtos.OrderCreateDTO;
import com.ecommercevcs.entities.OrderEntity;
import com.ecommercevcs.services.IOrderService;


@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	IOrderService orderService;
	
	
	@GetMapping
	public ResponseEntity<List<OrderEntity>> findAll(){
		
		return ResponseEntity.ok(orderService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderEntity> findById(@PathVariable Long id){
		return ResponseEntity.ok(orderService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<OrderCreateDTO> add(@RequestBody OrderCreateDTO order){
		orderService.add(order);
		return ResponseEntity.ok(order);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderEntity> update(@RequestBody OrderEntity order, @PathVariable Long id){
		return ResponseEntity.ok(orderService.update(order, id));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		orderService.deleteById(id);
	}
	
	
}
