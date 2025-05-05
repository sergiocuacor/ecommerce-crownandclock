package com.ecommercevcs.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositiveValueIntegerValidation implements ConstraintValidator<IsPositiveValueInteger, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if(value < 0) {
			return false;
		}
		return true;
	}

	
}
