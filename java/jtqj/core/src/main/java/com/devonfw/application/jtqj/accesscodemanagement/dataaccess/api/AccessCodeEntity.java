package com.devonfw.application.jtqj.accesscodemanagement.dataaccess.api;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.devonfw.application.jtqj.general.common.impl.Status;
import com.devonfw.application.jtqj.queuemanagement.dataaccess.api.QueueEntity;

@Entity
@Table(name = "AccessCode")
public class AccessCodeEntity {

	@Size(min = 4, max = 4)
	private String code;

	private String uuid;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp startTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp endTime;

	private Status status;

	private QueueEntity queue;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the created
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the startTime
	 */
	public Timestamp getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Timestamp getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the queue
	 */
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "idQueue")
	public QueueEntity getQueue() {
		return queue;
	}

	/**
	 * @param queue the queue to set
	 */
	public void setQueue(QueueEntity queue) {
		this.queue = queue;
	}

}
