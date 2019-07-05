package com.devonfw.application.jtqj.accesscodemanagement.logic.api.to;

import java.sql.Timestamp;

import com.devonfw.application.jtqj.general.common.api.Status;
import com.devonfw.application.jtqj.general.common.api.to.AbstractSearchCriteriaTo;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;

/**
 * {@link SearchCriteriaTo} to find instances of
 * {@link com.devonfw.application.jtqj.accesscodemanagement.common.api.AccessCode}s.
 */
public class AccessCodeSearchCriteriaTo extends AbstractSearchCriteriaTo {

	private static final long serialVersionUID = 1L;

	private String code;

	private String uuid;

	private Timestamp createdDate;

	private Timestamp startTime;

	private Timestamp endTime;

	private Status status;

	private Long queueId;

	private StringSearchConfigTo codeOption;

	private StringSearchConfigTo uuidOption;

	/**
	 * @return codeId
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code setter for code attribute
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return uuidId
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid setter for uuid attribute
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	/**
	 * @return startTimeId
	 */
	public Timestamp getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime setter for startTime attribute
	 */
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return endTimeId
	 */
	public Timestamp getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime setter for endTime attribute
	 */
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return statusId
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status setter for status attribute
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * getter for queueId attribute
	 *
	 * @return queueId
	 */
	public Long getQueueId() {
		return queueId;
	}

	/**
	 * @param queue setter for queue attribute
	 */
	public void setQueueId(Long queueId) {
		this.queueId = queueId;
	}

	/**
	 * @return the {@link StringSearchConfigTo} used to search for {@link #getCode()
	 *         code}.
	 */
	public StringSearchConfigTo getCodeOption() {

		return this.codeOption;
	}

	/**
	 * @param codeOption new value of {@link #getCodeOption()}.
	 */
	public void setCodeOption(StringSearchConfigTo codeOption) {

		this.codeOption = codeOption;
	}

	/**
	 * @return the {@link StringSearchConfigTo} used to search for {@link #getUuid()
	 *         uuid}.
	 */
	public StringSearchConfigTo getUuidOption() {

		return this.uuidOption;
	}

	/**
	 * @param uuidOption new value of {@link #getUuidOption()}.
	 */
	public void setUuidOption(StringSearchConfigTo uuidOption) {

		this.uuidOption = uuidOption;
	}

}
