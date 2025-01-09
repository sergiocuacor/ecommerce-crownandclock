package com.ecommercevcs.validation.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ValidationUtils {
	public ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errors = new HashMap<>(); // se crea un map vacio que va a contener los errores
		
		result.getFieldErrors().forEach(err -> {  // Si existen errores almacena el campo donde haya error y su mensaje
			errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage() + " tipo Error: " + err.getCode()); 
		});
		
		// es 400  ya que lo hemos controlado antes en el entity
		return ResponseEntity.badRequest().body(errors);
	}
}