package com.devonfw.application.jtqj.accesscodemanagement.common.api;

import java.sql.Timestamp;

import com.devonfw.application.jtqj.general.common.api.ApplicationEntity;
import com.devonfw.application.jtqj.general.common.api.Status;

public interface AccessCode extends ApplicationEntity {

	/**
	 * @return codeId
	 */

	public String getCode();

	/**
	 * @param code setter for code attribute
	 */

	public void setCode(String code);

	/**
	 * @return uuidId
	 */

	public String getUuid();

	/**
	 * @param uuid setter for uuid attribute
	 */

	public void setUuid(String uuid);

	/**
	 * @return createdDateId
	 */

	public Timestamp getCreatedDate();

	/**
	 * @param createdDate setter for createdDate attribute
	 */

	public void setCreatedDate(Timestamp createdDate);

	/**
	 * @return startTimeId
	 */

	public Timestamp getStartTime();

	/**
	 * @param startTime setter for startTime attribute
	 */

	public void setStartTime(Timestamp startTime);

	/**
	 * @return endTimeId
	 */

	public Timestamp getEndTime();

	/**
	 * @param endTime setter for endTime attribute
	 */

	public void setEndTime(Timestamp endTime);

	/**
	 * @return statusId
	 */

	public Status getStatus();

	/**
	 * @param status setter for status attribute
	 */

	public void setStatus(Status status);

	/**
	 * getter for queueId attribute
	 *
	 * @return queueId
	 */

	public Long getQueueId();

	/**
	 * @param queue setter for queue attribute
	 */

	public void setQueueId(Long queueId);

}
