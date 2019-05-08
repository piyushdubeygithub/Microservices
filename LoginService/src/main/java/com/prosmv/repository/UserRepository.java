package com.prosmv.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prosmv.domain.Factory;
import com.prosmv.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {

	public User findByEmail(String email);

	public User findByUsernameAndIsActiveAndDeleted(String username,boolean isActive,Character deleted);

	public User findByUsernameAndFactories(String username, List<Factory> factories);

	public long countByUserType(String userType);
}
