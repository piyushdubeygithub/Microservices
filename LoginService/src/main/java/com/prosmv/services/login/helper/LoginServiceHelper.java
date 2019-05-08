package com.prosmv.services.login.helper;

import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prosmv.constants.enums.TokenType;
import com.prosmv.constants.message.ServiceMessageCode;
import com.prosmv.domain.AuthenticationToken;
import com.prosmv.domain.User;
import com.prosmv.dto.LoginResponseDTO;
import com.prosmv.dto.ResponseDTO;
import com.prosmv.forms.ChangePasswordForm;
import com.prosmv.forms.LoginForm;
import com.prosmv.forms.ResetPasswordForm;
import com.prosmv.repository.AuthenticationTokenRepository;
import com.prosmv.repository.UserRepository;
import com.prosmv.services.login.LoginService;
import com.prosmv.util.email.EmailTemplateService;
import com.prosmv.util.response.ResponseHandler;

/**
 * This service helper class is used for business logic manulpilation with
 * response to controller.
 * 
 * @author piyush
 *
 */
@Service
public class LoginServiceHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceHelper.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailTemplateService emailTemplateService;

	@Autowired
	private AuthenticationTokenRepository authenticationTokenRepository;

	/**
	 * This method is used to return login response.
	 * 
	 * @param loginForm
	 * @return
	 */
	public ResponseDTO login(LoginForm loginForm) {
		User user = userRepository.findByUsernameAndIsActiveAndDeleted(loginForm.getUsername(),true,'N');
		if (Objects.isNull(user)) {
			return ResponseHandler.generateServiceResponse(null, ServiceMessageCode.INVALID_USERNAME, null, false,
					HttpStatus.BAD_REQUEST);
		}
		boolean isPasswordMatches = new BCryptPasswordEncoder().matches(loginForm.getPassword(), user.getPassword());
		if (!isPasswordMatches) {
			return ResponseHandler.generateServiceResponse(null, ServiceMessageCode.INVALID_PASSWORD, null, false,
					HttpStatus.BAD_REQUEST);
		}
		try {
			LoginResponseDTO loginResponseDTO = loginService.login(user);
			return ResponseHandler.generateServiceResponse(loginResponseDTO, ServiceMessageCode.LOGIN_SUCCESS, null,
					true, HttpStatus.OK);
		} catch (Exception exception) {
			return ResponseHandler.generateExceptionResponse(exception);
		}
	}

	/**
	 * This method is used to return logout response.
	 * 
	 * @param authenticationToken
	 * @return
	 */
	public ResponseDTO logout(String authenticationToken) {
		boolean isLogoutSuccessfull = false;
		try {
			isLogoutSuccessfull = loginService.logout(authenticationToken);
		} catch (Exception exception) {
			return ResponseHandler.generateExceptionResponse(exception);
		}
		if (isLogoutSuccessfull) {
			return ResponseHandler.generateServiceResponse(null, ServiceMessageCode.LOGOUT_SUCCESS, null, true,
					HttpStatus.OK);
		}
		return ResponseHandler.generateServiceResponse(null, ServiceMessageCode.LOGOUT_FAILURE, null, false,
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method is used to return switch user response.
	 * 
	 * @param username username to switch
	 * @return {@link ResponseDTO}
	 */
	public ResponseDTO switchUser(String username) {
		User user = userRepository.findByUsernameAndIsActiveAndDeleted(username, true, 'N');
		if (Objects.isNull(user)) {
			return ResponseHandler.generateServiceResponse(null, ServiceMessageCode.INVALID_USERNAME, null, false,
					HttpStatus.BAD_REQUEST);
		}
		try {
			LoginResponseDTO loginResponseDTO = loginService.switchUser(user);
			return ResponseHandler.generateServiceResponse(loginResponseDTO,
					ServiceMessageCode.USER_SWITCHED_SUCCESSFULLY, null, true, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseHandler.generateExceptionResponse(e);
		}
	}

	/**
	 * This method is used to return forgot password response.
	 * 
	 * @param username name of user who forgot his/her password.
	 * @return {@link ResponseDTO}
	 */
	public ResponseDTO forgotPassword(String username) {
		User user = userRepository.findByUsernameAndIsActiveAndDeleted(username, true, 'N');
		if (Objects.isNull(user)) {
			return ResponseHandler.generateServiceResponse(null, ServiceMessageCode.INVALID_USERNAME, null, false,
					HttpStatus.BAD_REQUEST);
		}
		try {
			String forgotPasswordToken = UUID.randomUUID().toString();
			AuthenticationToken authenticationToken = authenticationTokenRepository.findByUser(user);
			if (Objects.nonNull(authenticationToken)) {
				authenticationTokenRepository.delete(authenticationToken);
			}
			authenticationToken = new AuthenticationToken();
			authenticationToken.setToken(forgotPasswordToken);
			authenticationToken.setUser(user);
			authenticationToken.setTokenType(TokenType.FORGOT_PASSWORD);
			authenticationTokenRepository.save(authenticationToken);
			emailTemplateService.sendForgotPasswordEmail(user, forgotPasswordToken);
			return ResponseHandler.generateServiceResponse(null, ServiceMessageCode.FORGOT_PASSWORD_MAIL_SENT, null,
					true, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Exception has been occures while sending forgot password mail {} ", e);
			return ResponseHandler.generateExceptionResponse(e);
		}
	}

	/**
	 * This helper method is used to reset password.
	 * 
	 * @param resetPasswordForm {@link ResetPasswordForm}
	 * @return {@link ResponseDTO}
	 */
	public ResponseDTO resetPassword(ResetPasswordForm resetPasswordForm) {
		AuthenticationToken authenticationToken = authenticationTokenRepository
				.findByToken(resetPasswordForm.getResetPasswordToken());
		if (Objects.nonNull(authenticationToken)
				&& authenticationToken.getTokenType().equals(TokenType.FORGOT_PASSWORD)) {
			User user = authenticationToken.getUser();
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(resetPasswordForm.getNewPassword());
			user.setPassword(hashedPassword);
			userRepository.save(user);
			authenticationTokenRepository.delete(authenticationToken);
			return ResponseHandler.generateServiceResponse(null, ServiceMessageCode.PASSWORD_RESET_SUCCESSFULLY, null,
					true, HttpStatus.OK);
		}
		return ResponseHandler.generateServiceResponse(null, ServiceMessageCode.INVALID_TOKEN_TO_RESET_PASSWORD, null,
				false, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This helper method is used to change password.
	 * 
	 * @param changePasswordForm {@link ChangePasswordForm}
	 * @return {@link ResponseDTO}
	 */
	public ResponseDTO changePassword(ChangePasswordForm changePasswordForm) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(5);
		if (!bCryptPasswordEncoder.matches(changePasswordForm.getCurrentPassword(), user.getPassword())) {
			return ResponseHandler.generateServiceResponse(null, ServiceMessageCode.INVALID_CURRENT_PASSWORD, null,
					false, HttpStatus.BAD_REQUEST);
		}
		user.setPassword(bCryptPasswordEncoder.encode(changePasswordForm.getConfirmPassword()));
		try {
			userRepository.save(user);
			return ResponseHandler.generateServiceResponse(null, ServiceMessageCode.PASSWORD_CHANGED_SUCCESSFULLY, null,
					true, HttpStatus.OK);
		} catch (Exception exception) {
			return ResponseHandler.generateExceptionResponse(exception);
		}
	}

}
