package com.ecommercevcs.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
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

import com.ecommercevcs.dtos.BrandDTO;
import com.ecommercevcs.entities.BrandEntity;
import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.services.BrandService;
import com.ecommercevcs.validation.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/brands")
public class BrandController {
	
    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	ValidationUtils validation;
	
	
	@GetMapping("/all")
	public List<BrandDTO> findAllBrands(){
		return this.brandService.findAllBrands();
	}
	
	@GetMapping("/{brandId}")
	public BrandDTO findBrandById(@PathVariable Long brandId) {
		return this.brandService.findBrandById(brandId);
	}
	
	@GetMapping("/detailed/all")
	public List<BrandEntity> findAll() {
		return this.brandService.findAll();
	}
	
	@GetMapping("/detailed/{id}")
	public BrandEntity findById(@PathVariable Long id) {
		return this.brandService.findById(id);
	}
	
	 @GetMapping("/{brandName}/products")
	public ResponseEntity<List<ProductEntity>> findProductsByBrandName(@PathVariable("brandName") String brandName){
		
		 List<ProductEntity> products = brandService.findProductsByBrandName(brandName);
	        
	        if (products.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	        
	        return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PostMapping
	public BrandEntity add(@RequestBody BrandEntity brand) {
		return this.brandService.add(brand);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		return this.brandService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public BrandEntity update(@RequestBody BrandEntity brand, @PathVariable Long id) {
		return this.brandService.update(brand, id);
	}
	
}
