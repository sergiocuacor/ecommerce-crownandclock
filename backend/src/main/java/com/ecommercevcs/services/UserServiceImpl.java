package com.ecommercevcs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercevcs.entities.UserEntity;
import com.ecommercevcs.entities.embeddable.Address;
import com.ecommercevcs.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

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

	@Override
	public UserEntity add(UserEntity user) {
		// TODO Auto-generated method stub
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
