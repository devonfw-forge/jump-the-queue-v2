package com.devonfw.application.jtqj.ownermanagement.dataaccess.api;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.devonfw.application.jtqj.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.application.jtqj.ownermanagement.common.api.Owner;

@Entity
@Table(name = "Owner")
public class OwnerEntity extends ApplicationPersistenceEntity implements Owner {

	private String username;

	private String password;

	private Boolean userType;

	private static final long serialVersionUID = 1L;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userType
	 */
	public Boolean getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(Boolean userType) {
		this.userType = userType;
	}

}
