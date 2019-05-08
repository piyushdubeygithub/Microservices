package com.prosmv.annotation.password;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Implementation of {@link ValidateConfirmPassword}
 * 
 * @author piyush
 *
 * @param <T>
 */
public class ValidateConfirmPasswordImplementation implements ConstraintValidator<ValidateConfirmPassword, Object> {

	private String className;

	@Override
	public void initialize(ValidateConfirmPassword constraintAnnotation) {
		className = constraintAnnotation.className();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {
			Class<?> classOfObject = (Class<?>) Class.forName(className);
			Object objectOfClass = classOfObject.cast(value);
			String newPassword = (String) new PropertyDescriptor("newPassword", classOfObject).getReadMethod()
					.invoke(objectOfClass);
			String confirmPassword = (String) new PropertyDescriptor("confirmPassword", classOfObject).getReadMethod()
					.invoke(objectOfClass);
			return confirmPassword.equals(newPassword);
		} catch (ClassNotFoundException e) {
			return false;
		} catch (IllegalAccessException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		} catch (InvocationTargetException e) {
			return false;
		} catch (IntrospectionException e) {
			return false;
		}
	}

}
