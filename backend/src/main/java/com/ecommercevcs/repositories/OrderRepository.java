package com.ecommercevcs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommercevcs.dtos.DiscountDTO;
import com.ecommercevcs.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	@Query("SELECT DISTINCT o.discountApplied FROM OrderEntity o WHERE o.user.id = :userId AND o.discountApplied IS NOT NULL AND o.discountApplied <> ''")
	List<String> findAllDiscountNamesAppliedByUser(@Param("userId") Long userId);
}
