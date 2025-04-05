package com.ecommercevcs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommercevcs.dtos.DiscountDTO;
import com.ecommercevcs.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	Optional<UserEntity> findByEmail(String email);
	
	List<DiscountDTO> getAvailableDiscounts(Long id);
}
