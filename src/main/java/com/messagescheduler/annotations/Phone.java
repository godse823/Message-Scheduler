package com.messagescheduler.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.messagescheduler.validators.PhoneNumberValidator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD,METHOD})
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface Phone {
	String message() default "phone is invalid!!";
	Class <?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
