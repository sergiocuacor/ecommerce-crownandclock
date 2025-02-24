package com.ecommercevcs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercevcs.entities.BrandEntity;
import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.services.IBrandService;
import com.ecommercevcs.validation.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/brand")
public class BrandController {
	
	@Autowired
	IBrandService brandService;
	
	@Autowired
	ValidationUtils validation;
	
	@GetMapping
	public List<BrandEntity> findAll() {
		return this.brandService.findAll();
	}
	
	@GetMapping("/{id}")
	public BrandEntity findById(@PathVariable Long id) {
		return this.brandService.findById(id);
	}
	
	@PostMapping
	public BrandEntity add(@RequestBody BrandEntity category) {
		return this.brandService.add(category);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return this.brandService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public BrandEntity update(@RequestBody BrandEntity category, @PathVariable Long id) {
		return this.brandService.update(category, id);
	}
	
	@PostMapping("/product/{id}")
	public ResponseEntity<?> addProduct(@Valid  @RequestBody ProductEntity product, BindingResult result, @PathVariable Long id) {
		
		if(result.hasErrors()) {
			return this.validation.validation(result);
		}
		return ResponseEntity.ok(this.brandService.addProduct(id, product));
	}
}
