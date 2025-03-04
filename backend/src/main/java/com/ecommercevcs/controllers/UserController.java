package com.ecommercevcs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercevcs.dtos.UserDTO;
import com.ecommercevcs.entities.UserEntity;
import com.ecommercevcs.services.IUserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	IUserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserEntity>> findAll(){
		
		return ResponseEntity.ok(userService.findAll()); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> findById(@PathVariable Long id) {
		
		return ResponseEntity.ok(userService.findById(id));
	}

	@PostMapping("/register")
	public ResponseEntity<UserEntity> add(@RequestBody UserEntity user){
		
		
		return ResponseEntity.ok(userService.add(user));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserEntity> update(@RequestBody UserEntity user, @PathVariable Long id){
		return ResponseEntity.ok(userService.update(user, id));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
	userService.deleteById(id);
	}
}
