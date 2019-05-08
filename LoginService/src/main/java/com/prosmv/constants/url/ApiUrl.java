package com.prosmv.constants.url;

/**
 * This class is a constant class which contains all the api end points url.
 * 
 * @author piyush
 *
 */
public final class ApiUrl {

	private ApiUrl() {

	}

	private static final String BASE_URL = "/api/v1";

	public static final String LOGIN_URL = BASE_URL + "/user/login";

	public static final String LOGOUT_URL = BASE_URL + "/user/logout";

	public static final String SWITCH_USER_URL =  BASE_URL + "/user/switchUser";

	public static final String FORGOT_PASSWORD_URL = BASE_URL + "/user/forgotpassword";
	
	public static final String RESET_PASSWORD_URL = BASE_URL + "/user/resetpassword";
	
	public static final String CHANGE_PASSWORD_URL = BASE_URL + "/user/changepassword";
	
}
