package com.ecommercevcs.services;

import java.util.List;
import java.util.Optional;

import com.ecommercevcs.dtos.DiscountDTO;
import com.ecommercevcs.entities.UserEntity;

public interface IUserService{

	public List<UserEntity> findAll();
//	public List<DiscountDTO> getAvailableDiscounts(Long id);
	public UserEntity findById(Long id);
	public UserEntity add(UserEntity user);
	public UserEntity update(UserEntity user, Long id);
	public void deleteById(Long id);
	UserEntity findByEmail(String email);
}
