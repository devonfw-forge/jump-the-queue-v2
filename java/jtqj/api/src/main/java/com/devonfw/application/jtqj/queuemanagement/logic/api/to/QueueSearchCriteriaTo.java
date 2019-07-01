package com.devonfw.application.jtqj.queuemanagement.logic.api.to;

import java.sql.Timestamp;

import com.devonfw.application.jtqj.general.common.api.to.AbstractSearchCriteriaTo;

/**
 * {@link SearchCriteriaTo} to find instances of
 * {@link com.devonfw.application.jtqj.queuemanagement.common.api.Queue}s.
 */
public class QueueSearchCriteriaTo extends AbstractSearchCriteriaTo {

	private static final long serialVersionUID = 1L;

	private Integer minAttentionTime;
	private Boolean started;
	private Timestamp createdDate;

	/**
	 * @return minAttentionTimeId
	 */

	public Integer getMinAttentionTime() {
		return minAttentionTime;
	}

	/**
	 * @param minAttentionTime setter for minAttentionTime attribute
	 */

	public void setMinAttentionTime(Integer minAttentionTime) {
		this.minAttentionTime = minAttentionTime;
	}

	/**
	 * @return startedId
	 */

	public Boolean getStarted() {
		return started;
	}

	/**
	 * @param started setter for started attribute
	 */

	public void setStarted(Boolean started) {
		this.started = started;
	}

	/**
	 * @return createdDateId
	 */

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate setter for createdDate attribute
	 */

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}
