package com.prosmv.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.prosmv.domain.AuthenticationToken;
import com.prosmv.repository.AuthenticationTokenRepository;
import com.prosmv.security.UserPrincipal;

public class TokenAuthenticationFilter extends GenericFilterBean {

	private AuthenticationTokenRepository authenticationTokenRepository;

	public TokenAuthenticationFilter(AuthenticationTokenRepository authenticationTokenRepository) {
		this.authenticationTokenRepository = authenticationTokenRepository;
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;

		// extract token from header
		final String accessToken = httpRequest.getHeader("Authorization");

		if (!Objects.isNull(accessToken) && !StringUtils.isEmpty(accessToken)) {
			AuthenticationToken auth = authenticationTokenRepository.findByToken(accessToken);
			// get and check whether token is valid ( from DB or file wherever you are
			// storing the token)

			// Populate SecurityContextHolder by fetching relevant information using token
			UserPrincipal userPrincipal = new UserPrincipal(auth.getUser());
			final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					auth.getUser(), null, userPrincipal.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		chain.doFilter(request, response);
	}

}
