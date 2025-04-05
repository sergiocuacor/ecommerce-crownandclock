package com.ecommercevcs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommercevcs.entities.BrandEntity;

public interface BrandRepositoryJPA extends JpaRepository<BrandEntity, Long> {

}
