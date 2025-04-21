package com.ecommercevcs.repositories;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.ecommercevcs.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	@Query("SELECT DISTINCT o.discountApplied FROM OrderEntity o WHERE o.user.id = :userId AND o.discountApplied IS NOT NULL AND o.discountApplied <> ''")
	List<String> findAllDiscountNamesAppliedByUser(@Param("userId") Long userId);
	
	@Query("SELECT EXISTS (SELECT 1 FROM OrderEntity o JOIN o.orderDetails od " +
		       "WHERE o.user.id = :userId AND od.product.id = :productId)")
	boolean existsByUserIdAndProductId(Long userId, Long productId);
	
	List<OrderEntity> findByOrderDate(LocalDateTime startDate, LocalDateTime endDate);
}
