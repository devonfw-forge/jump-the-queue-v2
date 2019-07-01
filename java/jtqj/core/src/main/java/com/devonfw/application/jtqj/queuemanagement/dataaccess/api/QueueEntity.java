package com.devonfw.application.jtqj.queuemanagement.dataaccess.api;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Queue")
public class QueueEntity {

	private int minAttentionTime;

	private Boolean started;

	/**
	 * @return the minAttentionTime
	 */
	public int getMinAttentionTime() {
		return minAttentionTime;
	}

	/**
	 * @param minAttentionTime the minAttentionTime to set
	 */
	public void setMinAttentionTime(int minAttentionTime) {
		this.minAttentionTime = minAttentionTime;
	}

	/**
	 * @return the started
	 */
	public Boolean getStarted() {
		return started;
	}

	/**
	 * @param started the started to set
	 */
	public void setStarted(Boolean started) {
		this.started = started;
	}

}
