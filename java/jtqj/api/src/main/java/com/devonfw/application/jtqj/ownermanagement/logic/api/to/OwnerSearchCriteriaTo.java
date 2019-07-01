package com.devonfw.application.jtqj.ownermanagement.logic.api.to;

import com.devonfw.application.jtqj.general.common.api.to.AbstractSearchCriteriaTo;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;

/**
 * {@link SearchCriteriaTo} to find instances of
 * {@link com.devonfw.application.jtqj.ownermanagement.common.api.Owner}s.
 */
public class OwnerSearchCriteriaTo extends AbstractSearchCriteriaTo {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private Boolean userType;
	private StringSearchConfigTo usernameOption;
	private StringSearchConfigTo passwordOption;

	/**
	 * @return usernameId
	 */

	public String getUsername() {
		return username;
	}

	/**
	 * @param username setter for username attribute
	 */

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return passwordId
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * @param password setter for password attribute
	 */

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return userTypeId
	 */

	public Boolean getUserType() {
		return userType;
	}

	/**
	 * @param userType setter for userType attribute
	 */

	public void setUserType(Boolean userType) {
		this.userType = userType;
	}

	/**
	 * @return the {@link StringSearchConfigTo} used to search for
	 *         {@link #getUsername() username}.
	 */
	public StringSearchConfigTo getUsernameOption() {

		return this.usernameOption;
	}

	/**
	 * @param usernameOption new value of {@link #getUsernameOption()}.
	 */
	public void setUsernameOption(StringSearchConfigTo usernameOption) {

		this.usernameOption = usernameOption;
	}

	/**
	 * @return the {@link StringSearchConfigTo} used to search for
	 *         {@link #getPassword() password}.
	 */
	public StringSearchConfigTo getPasswordOption() {

		return this.passwordOption;
	}

	/**
	 * @param passwordOption new value of {@link #getPasswordOption()}.
	 */
	public void setPasswordOption(StringSearchConfigTo passwordOption) {

		this.passwordOption = passwordOption;
	}

}
