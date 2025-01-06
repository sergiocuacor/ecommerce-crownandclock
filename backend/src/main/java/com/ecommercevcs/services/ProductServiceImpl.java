package com.ecommercevcs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.repositories.ProductRepositoryJPA;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	ProductRepositoryJPA productRepository;

	@Override
	public List<ProductEntity> findAll() {
		
		return this.productRepository.findAll();
	}

	@Override
	public ProductEntity findById(Long id) {
		
		return this.productRepository.findById(id).get();
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		this.productRepository.deleteById(id);
		return ResponseEntity.ok("Deleted product with id " + id);
	}

	@Override
	public ProductEntity update(ProductEntity product, Long id) {
		Optional<ProductEntity> productUpdated = this.productRepository.findById(id);
		if(productUpdated.isPresent()) {
			productUpdated.get().setName(product.getName());
		}
		
		return this.productRepository.save(productUpdated.get());
	}

	
}
