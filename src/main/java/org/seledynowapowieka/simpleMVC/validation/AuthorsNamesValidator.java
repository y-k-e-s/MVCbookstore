package org.seledynowapowieka.simpleMVC.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AuthorsNamesValidator implements ConstraintValidator<AuthorsNamesCheck, List<String>> {

	@Override
	public boolean isValid(List<String> array, ConstraintValidatorContext context) {
		if (array != null) {
			for (String namePart : array) {
				if (namePart.isBlank()) {
					return false;
				}
			}
		}
		return true;

	}

}
