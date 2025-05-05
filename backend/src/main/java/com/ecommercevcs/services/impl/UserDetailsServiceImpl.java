package com.ecommercevcs.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommercevcs.controllers.ReviewController;
import com.ecommercevcs.entities.UserEntity;
import com.ecommercevcs.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<UserEntity> userOptional = userRepository.findByEmail(email);
		if(!userOptional.isPresent()) {
			throw new UsernameNotFoundException("The email " + email + " doesn't exist");
		}
		UserEntity user = userOptional.orElseThrow();
		
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		
		
		return new User(user.getEmail(),
					user.getPassword(),
					user.isEnabled(),
					true,true,true,
					authorities);
	}
	
	

}
