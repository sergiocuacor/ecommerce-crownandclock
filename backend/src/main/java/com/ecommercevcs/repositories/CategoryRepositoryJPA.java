package com.ecommercevcs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommercevcs.entities.CategoryEntity;

public interface CategoryRepositoryJPA extends JpaRepository<CategoryEntity, Long> {

}
