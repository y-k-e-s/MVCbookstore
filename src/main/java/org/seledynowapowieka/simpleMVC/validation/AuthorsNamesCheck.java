package org.seledynowapowieka.simpleMVC.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=AuthorsNamesValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface AuthorsNamesCheck {
	
	public String message() default "one or more name fields werent specified";
	
	public Class<?>[] groups() default{};
	
	public Class<? extends Payload>[] payload() default{};

}
