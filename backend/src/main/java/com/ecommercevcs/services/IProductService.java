package com.ecommercevcs.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommercevcs.entities.ProductEntity;

public interface IProductService {
	
	public List<ProductEntity> findAll();
	public ProductEntity findById(Long id);
	public ProductEntity addProduct(Long id, ProductEntity product);
	public ResponseEntity<?> deleteById(Long id);
	public ProductEntity update(ProductEntity product, Long brandId);
	public Page<ProductEntity> pageProducts(Pageable pageable);
	public ProductEntity findByMask(String mask);
	public Page<ProductEntity> pageProductsByBrandId(Pageable pageable,Long brandId);
}
