package com.devonfw.application.jtqj.queuemanagement.common.api;

import java.sql.Timestamp;

import com.devonfw.application.jtqj.general.common.api.ApplicationEntity;

public interface Queue extends ApplicationEntity {

	/**
	 * @return minAttentionTimeId
	 */

	public int getMinAttentionTime();

	/**
	 * @param minAttentionTime setter for minAttentionTime attribute
	 */

	public void setMinAttentionTime(int minAttentionTime);

	/**
	 * @return startedId
	 */

	public Boolean getStarted();

	/**
	 * @param started setter for started attribute
	 */

	public void setStarted(Boolean started);

	/**
	 * @return createdDateId
	 */

	public Timestamp getCreatedDate();

	/**
	 * @param createdDate setter for createdDate attribute
	 */

	public void setCreatedDate(Timestamp createdDate);

}
