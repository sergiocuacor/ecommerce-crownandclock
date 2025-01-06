package com.ecommercevcs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommercevcs.entities.CategoryEntity;
import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.repositories.CategoryRepositoryJPA;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	@Autowired
	CategoryRepositoryJPA categoryRepository;

	@Override
	public List<CategoryEntity> findAll() {
		
		return this.categoryRepository.findAll();
	}

	@Override
	public CategoryEntity findById(Long id) {
		
		return this.categoryRepository.findById(id).get();
	}

	@Override
	public CategoryEntity add(CategoryEntity category) {
		
		return this.categoryRepository.save(category);
	}

	@Override
	public ResponseEntity<?> deleteById(Long id) {
		this.categoryRepository.deleteById(id);
		return ResponseEntity.ok("Category with id " + id + " was eliminated");
	}

	@Override
	public CategoryEntity update(CategoryEntity category, Long id) {
		Optional<CategoryEntity> categoryUpdated = this.categoryRepository.findById(id);
		if(categoryUpdated.isPresent()) {
			categoryUpdated.get().setName(category.getName());
		}
		
		return this.categoryRepository.save(categoryUpdated.get());
	}

	@Override
	public ProductEntity addProduct(Long id, ProductEntity product) {
		Optional<CategoryEntity> category = this.categoryRepository.findById(id);
		
		if(category.isPresent()) {
			category.get().addProduct(product);
			this.categoryRepository.save(category.get());
		}
		return product;
	}

}
