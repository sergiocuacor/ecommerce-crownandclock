package com.ecommercevcs.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositiveValueValidation implements ConstraintValidator<IsPositiveValue, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if(value < 0) {
			return false;
		}
		return true;
	}

	
}
