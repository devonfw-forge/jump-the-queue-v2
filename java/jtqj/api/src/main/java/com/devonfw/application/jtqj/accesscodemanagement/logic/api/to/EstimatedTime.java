package com.devonfw.application.jtqj.accesscodemanagement.logic.api.to;

public class EstimatedTime {
	private long miliseconds;
	private long defaultTimeByUserInMs;


	/**
	 * @return the defaultTimeByUserInMs
	 */
	public long getDefaultTimeByUserInMs() {
		return defaultTimeByUserInMs;
	}

	/**
	 * @param defaultTimeByUserInMs the defaultTimeByUserInMs to set
	 */
	public void setDefaultTimeByUserInMs(long defaultTimeByUserInMs) {
		this.defaultTimeByUserInMs = defaultTimeByUserInMs;
	}

	/**
	 * @return the estimated
	 */
	public long getMiliseconds() {
		return miliseconds;
	}

	/**
	 * @param estimated the estimated to set
	 */
	public void setMiliseconds(long miliseconds) {
		this.miliseconds = miliseconds;
	}
}
