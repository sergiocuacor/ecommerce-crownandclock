package com.ecommercevcs.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.services.IProductService;
import com.ecommercevcs.validation.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	
	@Autowired
	IProductService productService;
	
	@Autowired
	ValidationUtils validation;
	
	@GetMapping
	public Page<ProductEntity> pageProduct(@PageableDefault(page = 0, size = 4, sort = "name") Pageable pageable,
			@RequestParam(name = "brand",required = false ) Long brandId) {
		if(brandId != null) {
			return this.productService.pageProductsByBrandId(pageable, brandId);
		}
	    return this.productService.pageProducts(pageable);
	}
	
	@GetMapping("/all")
	public List<ProductEntity> findAll() {
		return this.productService.findAll();
	}
	
	@GetMapping("/{id}")
	public ProductEntity findById(@PathVariable Long id) {
		return this.productService.findById(id);
	}
		
	@GetMapping("/mask/{mask}")
	public ProductEntity findByMask(@PathVariable String mask) {
		return this.productService.findByMask(mask);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return this.productService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ProductEntity update(@RequestBody ProductEntity product, @PathVariable Long id) {
		return this.productService.update(product, id);
	}
	
	@PostMapping("/{brandId}")
	public ResponseEntity<?> addProduct(@Valid  @RequestBody ProductEntity product, BindingResult result, @PathVariable Long brandId) {
		
		if(result.hasErrors()) {
			return this.validation.validation(result);
		}
		return ResponseEntity.ok(this.productService.addProduct(brandId, product));
	}
}
