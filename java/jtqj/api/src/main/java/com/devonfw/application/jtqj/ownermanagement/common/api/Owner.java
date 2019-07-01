package com.devonfw.application.jtqj.ownermanagement.common.api;

import com.devonfw.application.jtqj.general.common.api.ApplicationEntity;

public interface Owner extends ApplicationEntity {

	/**
	 * @return usernameId
	 */

	public String getUsername();

	/**
	 * @param username setter for username attribute
	 */

	public void setUsername(String username);

	/**
	 * @return passwordId
	 */

	public String getPassword();

	/**
	 * @param password setter for password attribute
	 */

	public void setPassword(String password);

	/**
	 * @return userTypeId
	 */

	public Boolean getUserType();

	/**
	 * @param userType setter for userType attribute
	 */

	public void setUserType(Boolean userType);

}
