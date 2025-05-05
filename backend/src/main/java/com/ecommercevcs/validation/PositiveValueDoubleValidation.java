package com.ecommercevcs.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositiveValueDoubleValidation implements ConstraintValidator<IsPositiveValueDouble, Double> {

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		if(value < 0.0) {
			return false;
		}
		return true;
	}

	
}
