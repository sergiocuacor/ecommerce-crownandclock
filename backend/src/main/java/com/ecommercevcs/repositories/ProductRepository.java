package com.ecommercevcs.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercevcs.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	 ProductEntity findByMask(String mask);
	 ProductEntity findByName(String name);
	 Page<ProductEntity> findByBrandId(Pageable pageable,Long brandId);
}
