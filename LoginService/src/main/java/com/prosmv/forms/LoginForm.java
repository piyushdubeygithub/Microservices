package com.prosmv.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prosmv.annotation.groups.NotNullGroup;
import com.prosmv.constants.message.ValidationMessageCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This class is used to hold the json body of Login Api
 * 
 * @author piyush
 *
 */
@ApiModel(description = "This class is used as json body for Login Api Endpoint", value = "loginForm")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginForm {
	@NotNull(message = ValidationMessageCode.USER_NAME_CANNOT_BE_NULL, groups = NotNullGroup.class)
	@NotEmpty(message = ValidationMessageCode.USER_NAME_CANNOT_BE_NULL, groups = NotNullGroup.class)
	@ApiModelProperty(dataType = "string", name = "username", required = true, value = "This property will hold the username.")
	private String username;
	@NotNull(message = ValidationMessageCode.PASSWORD_CANNOT_BE_NULL, groups = NotNullGroup.class)
	@NotEmpty(message = ValidationMessageCode.PASSWORD_CANNOT_BE_NULL, groups = NotNullGroup.class)
	@ApiModelProperty(dataType = "string", name = "password", required = true, value = "This property will hold the password.")
	private String password;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 */
	public LoginForm() {
		super();
	}

	/**
	 * @param username
	 * @param password
	 */
	public LoginForm(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginForm [username=" + username + ", password=" + password + "]";
	}

}
