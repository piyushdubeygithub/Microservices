package com.prosmv.forms.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.prosmv.annotation.groups.NotNullGroup;
import com.prosmv.constants.message.ValidationMessageCode;

/**
 * This class is {@link ModelAttribute} class used for switch user api.
 * 
 * @author piyush
 *
 */
public class UserNameModel {

	@NotNull(message = ValidationMessageCode.USER_NAME_CANNOT_BE_NULL, groups = NotNullGroup.class)
	@NotEmpty(message = ValidationMessageCode.USER_NAME_CANNOT_BE_NULL, groups = NotNullGroup.class)
	private String username;

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
	 * @param username
	 */
	public UserNameModel(
			@NotNull(message = "username.required", groups = NotNullGroup.class) @NotEmpty(message = "username.required", groups = NotNullGroup.class) String username) {
		super();
		this.username = username;
	}

	/**
	 * 
	 */
	public UserNameModel() {
		super();
	}

}
