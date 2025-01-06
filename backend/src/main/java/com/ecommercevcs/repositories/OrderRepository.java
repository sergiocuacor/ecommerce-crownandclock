package com.ecommercevcs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommercevcs.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

}
