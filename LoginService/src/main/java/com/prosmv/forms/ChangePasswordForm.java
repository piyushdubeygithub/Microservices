package com.prosmv.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prosmv.annotation.groups.ConfirmPasswordGroup;
import com.prosmv.annotation.groups.NotNullGroup;
import com.prosmv.annotation.password.ValidateConfirmPassword;
import com.prosmv.constants.message.ValidationMessageCode;

/**
 * This class is used as json body for change password api.
 * 
 * @author piyush
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ValidateConfirmPassword(className = "com.prosmv.forms.ChangePasswordForm", groups = ConfirmPasswordGroup.class)
public class ChangePasswordForm {

	@NotNull(message = ValidationMessageCode.CURRENT_PASSWORD_CANNOT_BE_NULL, groups = NotNullGroup.class)
	@NotEmpty(message = ValidationMessageCode.CURRENT_PASSWORD_CANNOT_BE_NULL, groups = NotNullGroup.class)
	private String currentPassword;

	@NotNull(message = ValidationMessageCode.NEW_PASSWORD_CANNOT_BE_NULL, groups = NotNullGroup.class)
	@NotEmpty(message = ValidationMessageCode.NEW_PASSWORD_CANNOT_BE_NULL, groups = NotNullGroup.class)
	private String newPassword;

	@NotNull(message = ValidationMessageCode.CONFIRM_PASSWORD_CANNOT_BE_NULL, groups = NotNullGroup.class)
	@NotEmpty(message = ValidationMessageCode.CONFIRM_PASSWORD_CANNOT_BE_NULL, groups = NotNullGroup.class)
	private String confirmPassword;

	/**
	 * @return the currentPassword
	 */
	public String getCurrentPassword() {
		return currentPassword;
	}

	/**
	 * @param currentPassword the currentPassword to set
	 */
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * 
	 */
	public ChangePasswordForm() {
		super();
	}

	/**
	 * @param currentPassword
	 * @param newPassword
	 * @param confirmPassword
	 */
	public ChangePasswordForm(
			@NotNull(message = "current.password.required", groups = NotNullGroup.class) @NotEmpty(message = "current.password.required", groups = NotNullGroup.class) String currentPassword,
			@NotNull(message = "new.password.required", groups = NotNullGroup.class) @NotEmpty(message = "new.password.required", groups = NotNullGroup.class) String newPassword,
			@NotNull(message = "confirm.password.required", groups = NotNullGroup.class) @NotEmpty(message = "confirm.password.required", groups = NotNullGroup.class) String confirmPassword) {
		super();
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "ChangePasswordForm [currentPassword=" + currentPassword + ", newPassword=" + newPassword
				+ ", confirmPassword=" + confirmPassword + "]";
	}

}
