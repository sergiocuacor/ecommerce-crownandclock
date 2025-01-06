package com.ecommercevcs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercevcs.entities.ProductEntity;

@Repository
public interface ProductRepositoryJPA extends JpaRepository<ProductEntity, Long> {
	

}
