package com.ecommercevcs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommercevcs.entities.BrandEntity;
import com.ecommercevcs.entities.ProductEntity;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
	BrandEntity findByName(String name);
	
	 @Query("SELECT p FROM ProductEntity p WHERE p.brand.name = :brandName")
    List<ProductEntity> findProductsByBrandName(@Param("brandName") String brandName);
}
