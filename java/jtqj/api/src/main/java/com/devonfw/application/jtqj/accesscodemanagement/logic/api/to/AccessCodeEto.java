package com.devonfw.application.jtqj.accesscodemanagement.logic.api.to;

import java.sql.Timestamp;

import com.devonfw.application.jtqj.accesscodemanagement.common.api.AccessCode;
import com.devonfw.application.jtqj.general.common.api.Status;
import com.devonfw.module.basic.common.api.to.AbstractEto;

/**
 * Entity transport object of AccessCode
 */
public class AccessCodeEto extends AbstractEto implements AccessCode {

	private static final long serialVersionUID = 1L;

	private String code;
	private String uuid;
	private Timestamp createdDate;
	private Timestamp startTime;
	private Timestamp endTime;
	private Status status;

	private Long queueId;

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getUuid() {
		return uuid;
	}

	@Override
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public Timestamp getStartTime() {
		return startTime;
	}

	@Override
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Override
	public Timestamp getEndTime() {
		return endTime;
	}

	@Override
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public Long getQueueId() {
		return queueId;
	}

	@Override
	public void setQueueId(Long queueId) {
		this.queueId = queueId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.code == null) ? 0 : this.code.hashCode());
		result = prime * result + ((this.uuid == null) ? 0 : this.uuid.hashCode());
		result = prime * result + ((this.createdDate == null) ? 0 : this.createdDate.hashCode());
		result = prime * result + ((this.startTime == null) ? 0 : this.startTime.hashCode());
		result = prime * result + ((this.endTime == null) ? 0 : this.endTime.hashCode());
		result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());

		result = prime * result + ((this.queueId == null) ? 0 : this.queueId.hashCode());
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
		AccessCodeEto other = (AccessCodeEto) obj;
		if (this.code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!this.code.equals(other.code)) {
			return false;
		}
		if (this.uuid == null) {
			if (other.uuid != null) {
				return false;
			}
		} else if (!this.uuid.equals(other.uuid)) {
			return false;
		}
		if (this.createdDate == null) {
			if (other.createdDate != null) {
				return false;
			}
		} else if (!this.createdDate.equals(other.createdDate)) {
			return false;
		}
		if (this.startTime == null) {
			if (other.startTime != null) {
				return false;
			}
		} else if (!this.startTime.equals(other.startTime)) {
			return false;
		}
		if (this.endTime == null) {
			if (other.endTime != null) {
				return false;
			}
		} else if (!this.endTime.equals(other.endTime)) {
			return false;
		}
		if (this.status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!this.status.equals(other.status)) {
			return false;
		}

		if (this.queueId == null) {
			if (other.queueId != null) {
				return false;
			}
		} else if (!this.queueId.equals(other.queueId)) {
			return false;
		}
		return true;
	}
}
