package com.ecommercevcs.services;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.ecommercevcs.dtos.BrandDTO;
import com.ecommercevcs.entities.BrandEntity;
import com.ecommercevcs.entities.ProductEntity;


public interface IBrandService {
	
	public List<BrandEntity> findAll();
	public BrandEntity findById(Long id);
	public List<ProductEntity> findProductsByBrandName(@Param("brandName") String brandName);
	public BrandEntity add(BrandEntity brand);
	public ResponseEntity<?> deleteById(Long id);
	public BrandEntity update(BrandEntity brand, Long id);
	public List<BrandDTO> findAllBrands();
	public BrandDTO findBrandById(Long brandId);
}
