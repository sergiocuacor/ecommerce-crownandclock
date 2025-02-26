package com.ecommercevcs.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommercevcs.entities.RoleEntity;
import com.ecommercevcs.entities.UserEntity;
import com.ecommercevcs.entities.embeddable.Address;
import com.ecommercevcs.repositories.RoleRepository;
import com.ecommercevcs.repositories.UserRepository;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements IUserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	

	@Override
	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public UserEntity findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}
	
	@Transactional
	@Override
	public UserEntity add(UserEntity user) {
		
		logger.debug("Inicio del add de use");
		
		RoleEntity roleUser = this.roleRepository.findByName("ROLE_USER").orElseThrow(() -> {
		    logger.error("ROLE_USER no se ha encontrado");
		    return new RuntimeException("El rol ROLE_USER no existe en la base de datos");
		});

		
		List<RoleEntity> listRoles = new ArrayList<RoleEntity>();
		
		
		listRoles.add(roleUser);
		logger.info("LISTA DE ROLES ANTES DE ASIGNAR listRoles al user: {}", listRoles );
		user.setRoles(listRoles);
		logger.info("ROLES DEL USER: {}", user.getRoles() );
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		logger.info("PASSWORD DEL USER: {}", user.getPassword() );
		return userRepository.save(user);
	}

	@Override
	public UserEntity update(UserEntity updatedUser, Long id) {

		UserEntity existingUser = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found."));

		if (updatedUser.getName() != null) {
			existingUser.setName(updatedUser.getName());
		}
		if (updatedUser.getFirstName() != null) {
			existingUser.setFirstName(updatedUser.getFirstName());
		}
		if (updatedUser.getLastName() != null) {
			existingUser.setLastName(updatedUser.getLastName());
		}
		if (updatedUser.getEmail() != null) {
			existingUser.setEmail(updatedUser.getEmail());
		}
		if (updatedUser.getPhoneNumber() != null) {
			existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
		}

		if (updatedUser.getAddress() != null) {
			Address existingAddress = existingUser.getAddress();
			if (existingAddress == null) {
				existingAddress = new Address();
			}

			Address newAddress = updatedUser.getAddress();
			if (newAddress.getStreetAddress() != null) {
				existingAddress.setStreetAddress(newAddress.getStreetAddress());
			}
			if (newAddress.getCity() != null) {
				existingAddress.setCity(newAddress.getCity());
			}
			if (newAddress.getState() != null) {
				existingAddress.setState(newAddress.getState());
			}
			if (newAddress.getCountry() != null) {
				existingAddress.setCountry(newAddress.getCountry());
			}
			if (newAddress.getPostalCode() != null) {
				existingAddress.setPostalCode(newAddress.getPostalCode());
			}

			existingUser.setAddress(existingAddress);
		}
		return userRepository.save(existingUser);
	}

	@Override
	public void deleteById(Long id) {

		userRepository.deleteById(id);

	}

	

}
