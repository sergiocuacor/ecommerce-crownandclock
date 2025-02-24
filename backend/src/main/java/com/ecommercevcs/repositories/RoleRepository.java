package com.ecommercevcs.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommercevcs.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	
	Optional <RoleEntity> findByName(String name);
}
