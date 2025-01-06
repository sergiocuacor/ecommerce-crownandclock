package com.ecommercevcs.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ecommercevcs.entities.CategoryEntity;
import com.ecommercevcs.entities.ProductEntity;


public interface ICategoryService {
	
	public List<CategoryEntity> findAll();
	public ProductEntity addProduct(Long id, ProductEntity product);
	public CategoryEntity findById(Long id);
	public CategoryEntity add(CategoryEntity category);
	public ResponseEntity<?> deleteById(Long id);
	public CategoryEntity update(CategoryEntity category, Long id);
}
