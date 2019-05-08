package com.prosmv.services.login;

import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prosmv.constants.enums.TokenType;
import com.prosmv.domain.AuthenticationToken;
import com.prosmv.domain.User;
import com.prosmv.dto.LoginResponseDTO;
import com.prosmv.repository.AuthenticationTokenRepository;

/**
 * This service class is used for common services like login and logout
 * 
 * @author piyush
 *
 */
@Service
public class LoginServiceImplementation implements LoginService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImplementation.class);

	@Autowired
	private AuthenticationTokenRepository authenticationTokenRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoginResponseDTO login(User user) {
		String token = UUID.randomUUID().toString();
		AuthenticationToken authenticationToken = authenticationTokenRepository.findByUser(user);
		if (Objects.nonNull(authenticationToken)) {
			authenticationTokenRepository.delete(authenticationToken);
		}
		authenticationToken = new AuthenticationToken();
		authenticationToken.setToken(token);
		authenticationToken.setUser(user);
		authenticationToken.setTokenType(TokenType.LOGIN);
		authenticationTokenRepository.save(authenticationToken);
		return new LoginResponseDTO(token, user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean logout(String authenticationToken) {
		AuthenticationToken existingAuthenticationToken = authenticationTokenRepository
				.findByToken(authenticationToken);
		if (Objects.isNull(existingAuthenticationToken))
			return false;
		authenticationTokenRepository.delete(existingAuthenticationToken);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoginResponseDTO switchUser(User user) {
		String token = UUID.randomUUID().toString();
		AuthenticationToken authenticationToken = authenticationTokenRepository.findByUser(user);
		if (Objects.nonNull(authenticationToken)) {
			authenticationTokenRepository.delete(authenticationToken);
		}
		authenticationToken = new AuthenticationToken();
		authenticationToken.setToken(token);
		authenticationToken.setUser(user);
		authenticationToken.setTokenType(TokenType.SWITCH_USER);
		authenticationTokenRepository.save(authenticationToken);
		return new LoginResponseDTO(token, user);
	}

}
