package com.prosmv.services.login;

import com.prosmv.domain.User;
import com.prosmv.dto.LoginResponseDTO;

/**
 * This interface will have all the methods declared for the services related to
 * login logout
 * 
 * @author piyush
 *
 */
public interface LoginService {

	/**
	 * This method is used for login purpose.
	 * 
	 * @param user {@link User}
	 * @return {@link LoginResponseDTO}
	 */
	public LoginResponseDTO login(User user);

	/**
	 * This method is used for logout purpose.
	 * 
	 * @param authenticationToken authentication token.
	 * @return {@code true} or {@code false}
	 */
	public boolean logout(String authenticationToken);

	/**
	 * This method is used for switch user response.
	 * @param user {@link User}
	 * @return {@link LoginResponseDTO}
	 */
	public LoginResponseDTO switchUser(User user);
	
}
