package com.ecommercevcs.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Constraint(validatedBy = PositiveValueDoubleValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsPositiveValueDouble {
	String message() default "the value is any number less than zero!";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
