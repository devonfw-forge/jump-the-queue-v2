package com.devonfw.application.jtqj.queuemanagement.dataaccess.api;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name = "Queue")
public class QueueEntity {

	private int minAttentionTime;

	private Boolean started;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp createdDate;

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

	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}
