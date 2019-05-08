package com.prosmv.services.login;

import java.security.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prosmv.domain.Role;
import com.prosmv.domain.User;
import com.prosmv.repository.RoleRepository;
import com.prosmv.repository.UserRepository;

@Service
public class BootStrapService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public void saveSAUser() {
		long count = userRepository.countByUserType("ROLE_SUPERADMIN");
		if (count == 0) {
			User user = new User();
			String password = "123456";
			user.setActive(true);
			user.setEmail("shocky@gmail.com");
			user.setName("Shocky");
			user.setUsername("shocky123");
			user.setDeleted('N');
			Role role = new Role();
			role.setRoleName("SA");
			roleRepository.save(role);
			user.setRole(role);
			user.setUserType("ROLE_SUPERADMIN");
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
			user.setPassword(hashedPassword);
			userRepository.save(user);
		}
	}

}
