package com.ecommercevcs.services;

import java.util.List;

import com.ecommercevcs.entities.UserEntity;

public interface IUserService{

	public List<UserEntity> findAll();
	
	public UserEntity findById(Long id);
	public UserEntity add(UserEntity user);
	public UserEntity update(UserEntity user, Long id);
	public void deleteById(Long id);
}
