package com.ecommercevcs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommercevcs.entities.BrandEntity;
import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.repositories.BrandRepository;
import com.ecommercevcs.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	BrandRepository brandRepository;
	
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
	
	@Override
	public ProductEntity addProduct(Long brandId, ProductEntity product) {
		Optional<BrandEntity> brand = this.brandRepository.findById(brandId);
		
		if(brand.isPresent()) {
			brand.get().addProduct(product);
			 this.brandRepository.save(brand.get());
		}
		
		return this.productRepository.findByName(product.getName());
	}

	@Override
	public Page<ProductEntity> pageProducts(Pageable pageable) {
		
		return this.productRepository.findAll(pageable);
		 
	}
	
	@Override
	public ProductEntity findByMask(String mask) {
		return this.productRepository.findByMask(mask);
	}

	@Override
	public Page<ProductEntity> pageProductsByBrandId(Pageable pageable,Long brandId) {
		
		return this.productRepository.findByBrandId( pageable,brandId);
	}
	
	

	
}
