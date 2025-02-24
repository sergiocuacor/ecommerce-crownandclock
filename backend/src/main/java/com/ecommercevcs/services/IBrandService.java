package com.ecommercevcs.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecommercevcs.entities.BrandEntity;
import com.ecommercevcs.entities.ProductEntity;


public interface IBrandService {
	
	public List<BrandEntity> findAll();
	public ProductEntity addProduct(Long id, ProductEntity product);
	public BrandEntity findById(Long id);
	public BrandEntity add(BrandEntity brand);
	public ResponseEntity<?> deleteById(Long id);
	public BrandEntity update(BrandEntity brand, Long id);
}
