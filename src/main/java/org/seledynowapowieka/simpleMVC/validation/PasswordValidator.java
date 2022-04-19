package org.seledynowapowieka.simpleMVC.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;



public class PasswordValidator implements ConstraintValidator<PasswordMatch, Object> {
	private String firstFieldName;
    private String secondFieldName;
    private String message;
	
    @Override
    public void initialize(final PasswordMatch constraintAnnotation) {
	    	firstFieldName = constraintAnnotation.first();
	    	secondFieldName = constraintAnnotation.second();
	    	message = constraintAnnotation.message();
    }
	
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object firstObj = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
        Object secondObj = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);
        if(firstObj == null && secondObj == null) {
        	return false;
        }
        
        if(!firstObj.equals(secondObj)) {
        	  context.buildConstraintViolationWithTemplate(message)
              .addPropertyNode(firstFieldName)
              .addConstraintViolation()
              .disableDefaultConstraintViolation();
        	return false;
        }
        return true;
        
	}


}



/*
String matchingPassword = null;

RegisterController registerController = RegisterController.getInstance();

if(registerController.getUserModel() != null) {
	matchingPassword = registerController.getUserModel().getMatchingPassword();
}else {
	System.out.println("getUserModel() null!");
}


if (!password.equals(matchingPassword)) {
	return false;
}
return true;
*/