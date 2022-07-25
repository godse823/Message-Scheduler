package com.messagescheduler.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.i18n.phonenumbers.PhoneNumberUtil;

import com.messagescheduler.annotations.Phone;

//public class PhoneNumberValidator implements ConstraintValidator<Phone, String>{
public class PhoneNumberValidator implements ConstraintValidator<Phone, String>{
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
		//	PhoneNumberUtil
			PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            return phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(value, "IN"));
		}
		catch(Exception e) {
			return false;
		}
		finally{
			System.out.println("finally block");
		}
	} 

}
