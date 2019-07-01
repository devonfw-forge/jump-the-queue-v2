package com.devonfw.application.jtqj.ownermanagement.logic.api.to;

import com.devonfw.application.jtqj.ownermanagement.common.api.Owner;
import com.devonfw.module.basic.common.api.to.AbstractEto;

/**
 * Entity transport object of Owner
 */
public class OwnerEto extends AbstractEto implements Owner {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private Boolean userType;

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Boolean getUserType() {
		return userType;
	}

	@Override
	public void setUserType(Boolean userType) {
		this.userType = userType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
		result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
		result = prime * result + ((this.userType == null) ? 0 : this.userType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		// class check will be done by super type EntityTo!
		if (!super.equals(obj)) {
			return false;
		}
		OwnerEto other = (OwnerEto) obj;
		if (this.username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!this.username.equals(other.username)) {
			return false;
		}
		if (this.password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!this.password.equals(other.password)) {
			return false;
		}
		if (this.userType == null) {
			if (other.userType != null) {
				return false;
			}
		} else if (!this.userType.equals(other.userType)) {
			return false;
		}
		return true;
	}
}
