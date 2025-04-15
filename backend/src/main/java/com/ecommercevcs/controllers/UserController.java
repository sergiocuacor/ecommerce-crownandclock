package com.ecommercevcs.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercevcs.dtos.DiscountDTO;
import com.ecommercevcs.dtos.UserDTO;
import com.ecommercevcs.entities.UserEntity;
import com.ecommercevcs.services.IUserService;

// @CrossOrigin(origins = "https://localhost/...." o tambien) podemos poner la ruta del front 
// @CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	IUserService userService;
	
	@GetMapping("/all")
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
	
//	@GetMapping("/availableDiscounts")
//	public ResponseEntity<List<DiscountDTO>> getAvailableDiscounts(@PathVariable Long id){
//		return ResponseEntity.ok(userService.getAvailableDiscounts(id));
//	}
}
