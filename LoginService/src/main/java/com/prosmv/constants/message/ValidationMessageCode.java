package com.prosmv.constants.message;

/**
 * This class is a constant class to hold all the validation messages.
 * 
 * @author piyush
 *
 */
public final class ValidationMessageCode {

	private ValidationMessageCode() {

	}

	public static final String INTERNAL_SERVER_ERROR = "internal.server.error";

	public static final String USER_NAME_CANNOT_BE_NULL = "username.required";

	public static final String PASSWORD_CANNOT_BE_NULL = "password.required";

	public static final String NEW_PASSWORD_CANNOT_BE_NULL = "new.password.required";
	
	public static final String RESET_PASSWORD_TOKEN_CANNOT_BE_NULL = "reset.password.token.required";
	
	public static final String CONFIRM_PASSWORD_CANNOT_BE_NULL = "confirm.password.required";

	public static final String CONFIRM_PASSWORD_DOES_NOT_MATCHED = "confirm.password.match.failed";
	
	public static final String CURRENT_PASSWORD_CANNOT_BE_NULL = "current.password.required";
	
}
