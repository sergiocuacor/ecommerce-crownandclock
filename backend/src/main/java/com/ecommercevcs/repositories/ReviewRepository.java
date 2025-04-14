package com.ecommercevcs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.entities.ReviewEntity;
import com.ecommercevcs.entities.UserEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
	List<ReviewEntity> findByProductId(Long productId);
	List<ReviewEntity> findByUserId(Long userId);
	Optional<ReviewEntity> findByProductAndUser(ProductEntity product, UserEntity user);
	boolean existsByProductAndUser(ProductEntity product, UserEntity user);
}
