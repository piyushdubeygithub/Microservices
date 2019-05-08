package com.prosmv.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prosmv.domain.AuthenticationToken;
import com.prosmv.domain.User;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Serializable> {

	public AuthenticationToken findByToken(String accessToken);

	public AuthenticationToken findByUser(User user);

}
