package com.messagescheduler.validators;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.messagescheduler.annotations.ValidDateTime;



public class LocalDateTimeValidator implements ConstraintValidator<ValidDateTime, String> {
	 public boolean isValid(String value, ConstraintValidatorContext context) {
	        try {
	            LocalDateTime.parse(value);
	        } catch (Exception e) {
	            return false;
	        }
	        finally{
				System.out.println("finally block");
			}
	        return true;
	    }
}
