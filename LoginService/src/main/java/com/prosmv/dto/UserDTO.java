package com.prosmv.dto;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.prosmv.domain.Factory;
import com.prosmv.domain.User;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8016732383376138864L;
	private Long id;
	private String username;
	private String name;
	private String password;
	private String email;
	private boolean isActive;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String mobileNumber;
	private String roleName;
	private String userType;
	private List<FactoryDTO> factories;

	public UserDTO(User user) {
		if (user != null) {
			this.id = user.getId();
			this.username = user.getUsername();
			this.name = user.getName();
			this.email = user.getEmail();
			this.isActive = user.isActive();
			this.userType = user.getUserType();
			if(user.getRole() != null) {
				this.roleName = user.getRole().getRoleName();
			}	
		  List<FactoryDTO> factoryList = new ArrayList<>();
			if(user.getFactories() !=null) {
				for(Factory factory: user.getFactories()) {
					factoryList.add(new FactoryDTO(factory));
				}
			}
			this.factories = factoryList;
		}
	}

	public UserDTO(User user, String username) {
		if(user != null) {
			this.username = user.getUsername();
			this.id = user.getId();
			if(user.getRole() != null) {
				this.roleName = user.getRole().getRoleName();
			}
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<FactoryDTO> getFactories() {
		return factories;
	}

	public void setFactories(List<FactoryDTO> factories) {
		this.factories = factories;
	}

}

