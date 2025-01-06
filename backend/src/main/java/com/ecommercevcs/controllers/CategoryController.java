package com.ecommercevcs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercevcs.entities.CategoryEntity;
import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.services.ICategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping
	public List<CategoryEntity> findAll() {
		return this.categoryService.findAll();
	}
	
	@GetMapping("/{id}")
	public CategoryEntity findById(@PathVariable Long id) {
		return this.categoryService.findById(id);
	}
	
	@PostMapping
	public CategoryEntity add(@RequestBody CategoryEntity category) {
		return this.categoryService.add(category);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return this.categoryService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public CategoryEntity update(@RequestBody CategoryEntity category, @PathVariable Long id) {
		return this.categoryService.update(category, id);
	}
	
	@PostMapping("/product/{id}")
	public ProductEntity addProduct(@PathVariable Long id, @RequestBody ProductEntity product) {
		return this.categoryService.addProduct(id, product);
	}
}
