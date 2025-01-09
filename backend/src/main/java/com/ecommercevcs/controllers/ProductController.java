package com.ecommercevcs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.services.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	IProductService productService;
	
	@GetMapping
	public Page<?> pageProduct(@PageableDefault(page = 0, size = 4, sort = "name") Pageable pageable) {
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
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return this.productService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ProductEntity update(@RequestBody ProductEntity product, @PathVariable Long id) {
		return this.productService.update(product, id);
	}
	
	
}
