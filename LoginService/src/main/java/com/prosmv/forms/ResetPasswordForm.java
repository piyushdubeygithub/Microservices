package com.prosmv.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prosmv.annotation.groups.ConfirmPasswordGroup;
import com.prosmv.annotation.groups.NotNullGroup;
import com.prosmv.annotation.password.ValidateConfirmPassword;
import com.prosmv.constants.message.ValidationMessageCode;

/**
 * This class is used as json body for reset password api payload.
 * 
 * @author piyush
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ValidateConfirmPassword(className = "com.prosmv.forms.ResetPasswordForm", groups = ConfirmPasswordGroup.class)
public class ResetPasswordForm {

	@NotNull(message = ValidationMessageCode.RESET_PASSWORD_TOKEN_CANNOT_BE_NULL, groups = NotNullGroup.class)
	@NotEmpty(message = ValidationMessageCode.RESET_PASSWORD_TOKEN_CANNOT_BE_NULL, groups = NotNullGroup.class)
	private String resetPasswordToken;

	@NotNull(message = ValidationMessageCode.NEW_PASSWORD_CANNOT_BE_NULL, groups = NotNullGroup.class)
	@NotEmpty(message = ValidationMessageCode.NEW_PASSWORD_CANNOT_BE_NULL, groups = NotNullGroup.class)
	private String newPassword;

	@NotNull(message = ValidationMessageCode.CONFIRM_PASSWORD_CANNOT_BE_NULL, groups = NotNullGroup.class)
	@NotEmpty(message = ValidationMessageCode.CONFIRM_PASSWORD_CANNOT_BE_NULL, groups = NotNullGroup.class)
	private String confirmPassword;

	/**
	 * @return the resetPasswordToken
	 */
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	/**
	 * @param resetPasswordToken the resetPasswordToken to set
	 */
	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
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
	public ResetPasswordForm() {
		super();
	}

	/**
	 * @param resetPasswordToken
	 * @param newPassword
	 * @param confirmPassword
	 */
	public ResetPasswordForm(
			@NotNull(message = "reset.password.token.required", groups = NotNullGroup.class) @NotEmpty(message = "reset.password.token.required", groups = NotNullGroup.class) String resetPasswordToken,
			@NotNull(message = "new.password.required", groups = NotNullGroup.class) @NotEmpty(message = "new.password.required", groups = NotNullGroup.class) String newPassword,
			@NotNull(message = "confirm.password.required", groups = NotNullGroup.class) @NotEmpty(message = "confirm.password.required", groups = NotNullGroup.class) String confirmPassword) {
		super();
		this.resetPasswordToken = resetPasswordToken;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "ResetPasswordForm [resetPasswordToken=" + resetPasswordToken + ", newPassword=" + newPassword
				+ ", confirmPassword=" + confirmPassword + "]";
	}

}
