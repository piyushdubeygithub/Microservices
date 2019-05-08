package com.prosmv.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prosmv.annotation.sequence.ValidateSequence;
import com.prosmv.constants.url.ApiUrl;
import com.prosmv.dto.ResponseDTO;
import com.prosmv.forms.ChangePasswordForm;
import com.prosmv.forms.LoginForm;
import com.prosmv.forms.ResetPasswordForm;
import com.prosmv.forms.model.UserNameModel;
import com.prosmv.services.login.helper.LoginServiceHelper;
import com.prosmv.util.response.ResponseHandler;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This class is controller class to handle all the apis endpoint related to
 * login and logout services.
 * 
 * @author piyush
 *
 */
@RestController
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginServiceHelper loginServiceHelper;

	/**
	 * This api endpoint is used for login purpose.
	 * 
	 * @param loginForm
	 * @param bindingResult
	 * @return {@link ResponseEntity} with {@link ResponseDTO}.
	 */
	@ApiOperation(httpMethod = "POST", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "This api used for login purpose", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Login Successfully"),
			@ApiResponse(code = 400, message = "Please enter valid username."),
			@ApiResponse(code = 400, message = "Invalid Password"),
			@ApiResponse(code = 400, message = "Please provide the username."),
			@ApiResponse(code = 400, message = "Please provide the password."),
			@ApiResponse(code = 500, message = "An error has been occures while proccessing the request.") })
	@PostMapping(value = ApiUrl.LOGIN_URL)
	public ResponseEntity<ResponseDTO> login(
			@Validated(value = ValidateSequence.class) @ApiParam(type = "body", required = true) @RequestBody LoginForm loginForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.info("Validation Failed");
			return ResponseHandler.generateValidationResponse(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		return ResponseHandler.generateControllerResponse(loginServiceHelper.login(loginForm));
	}

	/**
	 * This api endpoint is used for logout purpose.
	 * 
	 * @param httpServletRequest {@link HttpServletRequest}
	 * @return {@link ResponseEntity} with {@link ResponseDTO}.
	 */
	@ApiOperation(httpMethod = "GET", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "This api is used for logout purpose", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Logout Successfully."),
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "An error has been occures while proccessing the request.") })
	@GetMapping(value = ApiUrl.LOGOUT_URL)
	public ResponseEntity<ResponseDTO> logout(HttpServletRequest httpServletRequest) {
		final String authenticationToken = httpServletRequest.getHeader("Authorization");
		return ResponseHandler.generateControllerResponse(loginServiceHelper.logout(authenticationToken));
	}

	/**
	 * This api endpoint is used for switch user functionality.
	 * 
	 * @param username name of user to be switched with
	 * @return {@link ResponseEntity} of {@link HttpStatus}
	 */
	@ApiOperation(httpMethod = "PUT", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "This api is used for switch user purpose.", response = ResponseDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Please enter valid username."),
			@ApiResponse(code = 200, message = "User switched successfully."),
			@ApiResponse(code = 500, message = "An error has been occures while proccessing the request.") })
	@PutMapping(value = ApiUrl.SWITCH_USER_URL)
	public ResponseEntity<ResponseDTO> switchUser(
			@Validated(value = ValidateSequence.class) @ModelAttribute UserNameModel userNameModel,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateValidationResponse(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		return ResponseHandler.generateControllerResponse(loginServiceHelper.switchUser(userNameModel.getUsername()));
	}

	/**
	 * This api endpoint is used to send forgot password mail.
	 * 
	 * @param username      username
	 * @param bindingResult {@link BindingResult}
	 * @return {@link ResponseEntity} of {@link ResponseDTO}
	 */
	@ApiOperation(httpMethod = "PUT", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "This api is used to send forgot password mail.", response = ResponseDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Forgot Password email has been sent to your registered email successfully."),
			@ApiResponse(code = 400, message = "Please enter valid username."),
			@ApiResponse(code = 400, message = "Please provide the username."),
			@ApiResponse(code = 500, message = "An error has been occures while proccessing the request.") })
	@PutMapping(value = ApiUrl.FORGOT_PASSWORD_URL)
	public ResponseEntity<ResponseDTO> forgotPassword(
			@Validated(value = ValidateSequence.class) @ModelAttribute UserNameModel userNameModel,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateValidationResponse(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		return ResponseHandler
				.generateControllerResponse(loginServiceHelper.forgotPassword(userNameModel.getUsername()));
	}

	/**
	 * This api endpoint is used to reset password.
	 * 
	 * @param resetPasswordForm {@link ResetPasswordForm}
	 * @param bindingResult     {@link BindingResult}
	 * @return {@link ResponseEntity} of {@link ResponseDTO}
	 */
	@PutMapping(value = ApiUrl.RESET_PASSWORD_URL)
	public ResponseEntity<ResponseDTO> resetForgotPassword(
			@Validated(value = ValidateSequence.class) @RequestBody ResetPasswordForm resetPasswordForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateValidationResponse(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		return ResponseHandler.generateControllerResponse(loginServiceHelper.resetPassword(resetPasswordForm));
	}

	/**
	 * This api end point is used to change password.
	 * 
	 * @param changePasswordForm {@link ChangePasswordForm}
	 * @param bindingResult      {@link BindingResult}
	 * @return {@link ResponseEntity} of {@link ResponseDTO}
	 */
	@PutMapping(value = ApiUrl.CHANGE_PASSWORD_URL)
	public ResponseEntity<ResponseDTO> changePassword(
			@Validated(value = ValidateSequence.class) @RequestBody ChangePasswordForm changePasswordForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHandler.generateValidationResponse(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		return ResponseHandler.generateControllerResponse(loginServiceHelper.changePassword(changePasswordForm));
	}

}
