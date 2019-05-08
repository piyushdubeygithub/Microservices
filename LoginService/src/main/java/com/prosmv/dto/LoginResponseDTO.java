package com.prosmv.dto;

import com.prosmv.domain.User;

/**
 * This class is used as DTO
 * 
 * @author piyush
 *
 */
public class LoginResponseDTO {

	private String authenticationToken;

	
	private UserDTO user;

	/**
	 * @return the authenticationToken
	 */
	public String getAuthenticationToken() {
		return authenticationToken;
	}

	/**
	 * @param authenticationToken the authenticationToken to set
	 */
	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}



	
	public LoginResponseDTO(String authenticationToken, User user) {
		this.authenticationToken = authenticationToken;
		if(user != null) {
			this.user = new UserDTO(user);
		}
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	

}
