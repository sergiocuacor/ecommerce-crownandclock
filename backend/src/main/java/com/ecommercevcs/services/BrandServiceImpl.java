package com.ecommercevcs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommercevcs.entities.BrandEntity;
import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.repositories.BrandRepositoryJPA;
import com.ecommercevcs.repositories.ProductRepositoryJPA;

@Service
public class BrandServiceImpl implements IBrandService {
	
	@Autowired
	BrandRepositoryJPA brandRepository;
	
	@Autowired
	ProductRepositoryJPA productRepository;

	@Override
	public List<BrandEntity> findAll() {
		System.out.println("FIND ALL BRAND REPOSITORY");
		System.out.println(this.brandRepository.findAll());
		
		return this.brandRepository.findAll();
	}

	@Override
	public BrandEntity findById(Long id) {
		
		return this.brandRepository.findById(id).get();
	}

	@Override
	public BrandEntity add(BrandEntity category) {
		
		return this.brandRepository.save(category);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		this.brandRepository.deleteById(id);
		return ResponseEntity.ok("Category with id " + id + " was eliminated");
	}

	@Override
	public BrandEntity update(BrandEntity category, Long id) {
		Optional<BrandEntity> categoryUpdated = this.brandRepository.findById(id);
		if(categoryUpdated.isPresent()) {
			categoryUpdated.get().setName(category.getName());
		}
		
		return this.brandRepository.save(categoryUpdated.get());
	}

	@Override
	public ProductEntity addProduct(Long id, ProductEntity product) {
		Optional<BrandEntity> brand = this.brandRepository.findById(id);
		
		if(brand.isPresent()) {
			brand.get().addProduct(product);
			 this.brandRepository.save(brand.get());
		}
		
		return this.productRepository.findByName(product.getName());
	}

}
