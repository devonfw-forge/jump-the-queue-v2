package com.devonfw.application.jtqj.accesscodemanagement.logic.api.to;

public class NextCodeCto {
	private AccessCodeEto accessCode;
	/**
	 * @return the accessCode
	 */
	public AccessCodeEto getAccessCode() {
		return accessCode;
	}
	/**
	 * @param accessCode the accessCode to set
	 */
	public void setAccessCode(AccessCodeEto accessCode) {
		this.accessCode = accessCode;
	}
	/**
	 * @return the remainingCodes
	 */
	public RemainingCodes getRemainingCodes() {
		return remainingCodes;
	}
	/**
	 * @param remainingCodes the remainingCodes to set
	 */
	public void setRemainingCodes(RemainingCodes remainingCodes) {
		this.remainingCodes = remainingCodes;
	}
	private RemainingCodes remainingCodes;
}
