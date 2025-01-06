package com.ecommercevcs.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecommercevcs.entities.ProductEntity;

public interface IProductService {
	
	public List<ProductEntity> findAll();
	public ProductEntity findById(Long id);
	public ResponseEntity<?> deleteById(Long id);
	public ProductEntity update(ProductEntity product, Long id);
}
