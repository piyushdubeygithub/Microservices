package com.prosmv.annotation.password;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.prosmv.constants.message.ValidationMessageCode;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER,ElementType.TYPE })
@Constraint(validatedBy = ValidateConfirmPasswordImplementation.class)
public @interface ValidateConfirmPassword {

	String message() default ValidationMessageCode.CONFIRM_PASSWORD_DOES_NOT_MATCHED;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String className();

}
