package com.ecommercevcs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommercevcs.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
