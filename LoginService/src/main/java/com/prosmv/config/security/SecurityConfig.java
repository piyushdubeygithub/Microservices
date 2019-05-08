package com.prosmv.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.prosmv.constants.url.ApiUrl;
import com.prosmv.filter.TokenAuthenticationFilter;
import com.prosmv.repository.AuthenticationTokenRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationTokenRepository authenticationTokenRepository;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		// Implementing Token based authentication in this filter
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers(HttpMethod.POST, ApiUrl.LOGIN_URL).permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.PUT,ApiUrl.FORGOT_PASSWORD_URL).permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.PUT,ApiUrl.RESET_PASSWORD_URL).permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.PUT, ApiUrl.SWITCH_USER_URL).hasAnyRole("USER").anyRequest()
				.authenticated();
		http.authorizeRequests().antMatchers(ApiUrl.CHANGE_PASSWORD_URL).authenticated();
		http.authorizeRequests().antMatchers(ApiUrl.LOGOUT_URL).hasRole("USER").anyRequest().authenticated();
		// Implementing Token based authentication in this filter
		final TokenAuthenticationFilter tokenFilter = new TokenAuthenticationFilter(authenticationTokenRepository);
		http.addFilterBefore(tokenFilter, BasicAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs/**");
		web.ignoring().antMatchers("/swagger.json");
		web.ignoring().antMatchers("/swagger-ui.html");
		web.ignoring().antMatchers("/webjars/**");
		web.ignoring().antMatchers("/swagger-resources/**");
	}

}
