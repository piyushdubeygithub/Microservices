package com.prosmv.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.prosmv.domain.Company;
import com.prosmv.domain.Factory;
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FactoryDTO {
	
	private Long   id;
	private String name;
	private String mobileNumber;
	private String address;
	private String email;
	private Timestamp licenseExpDate;
	private Timestamp licenseIssueDate;
	private boolean isActive;
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public FactoryDTO(Factory factory) {
		if(factory != null && !factory.isDeleted() && factory.isActive()) {
			this.id = factory.getId();
			this.name = factory.getName();
			this.isActive = factory.isActive();
		}
	}
	
	public FactoryDTO(String name) {
		this.name = name;
	}
	
	public FactoryDTO(String name, Long id) {
	this.id = id;
	this.name = name;
	}

	public FactoryDTO(Factory factory, Company company) {
		if(factory != null) {
			this.id = factory.getId();
			this.name = factory.getName();
			this.mobileNumber = factory.getMobileNumber();
			this.address = factory.getAddress();
			this.email = factory.getEmail();
			this.licenseExpDate = factory.getLicenseExpDate();
			this.licenseIssueDate = factory.getLicenseIssueDate();
			this.isActive = factory.isActive();
		}
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getLicenseExpDate() {
		return licenseExpDate;
	}
	public void setLicenseExpDate(Timestamp licenseExpDate) {
		this.licenseExpDate = licenseExpDate;
	}
	public Timestamp getLicenseIssueDate() {
		return licenseIssueDate;
	}
	public void setLicenseIssueDate(Timestamp licenseIssueDate) {
		this.licenseIssueDate = licenseIssueDate;
	}

}
