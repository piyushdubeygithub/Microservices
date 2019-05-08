package com.prosmv.constants.message;

/**
 * This class is a constant class contains all the constant related to the
 * service messages.
 * 
 * @author piyush
 *
 */
public final class ServiceMessageCode {

	private ServiceMessageCode() {
		
	}
	
	public static final String INVALID_USERNAME = "invalid.username";
	
	public static final String INVALID_PASSWORD = "invalid.password";
	
	public static final String LOGIN_SUCCESS = "login.success";

	public static final String LOGOUT_SUCCESS = "logout.success";

	public static final String LOGOUT_FAILURE = "logout.failure";
	
	public static final String USER_SWITCHED_SUCCESSFULLY = "user.switch.success";

	public static final String FORGOT_PASSWORD_MAIL_SENT = "forgot.password.mail.sent.success";
	
	public static final String INVALID_TOKEN_TO_RESET_PASSWORD = "reset.password.token.invalid";

	public static final String PASSWORD_RESET_SUCCESSFULLY = "reset.password.success";
	
	public static final String INVALID_CURRENT_PASSWORD = "current.password.invalid";
	
	public static final String PASSWORD_CHANGED_SUCCESSFULLY = "password.change.success";
	
}
