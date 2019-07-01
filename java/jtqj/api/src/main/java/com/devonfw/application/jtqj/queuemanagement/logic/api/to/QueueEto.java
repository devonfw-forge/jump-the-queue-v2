package com.devonfw.application.jtqj.queuemanagement.logic.api.to;

import java.sql.Timestamp;

import com.devonfw.application.jtqj.queuemanagement.common.api.Queue;
import com.devonfw.module.basic.common.api.to.AbstractEto;

/**
 * Entity transport object of Queue
 */
public class QueueEto extends AbstractEto implements Queue {

	private static final long serialVersionUID = 1L;

	private int minAttentionTime;
	private Boolean started;
	private Timestamp createdDate;

	@Override
	public int getMinAttentionTime() {
		return minAttentionTime;
	}

	@Override
	public void setMinAttentionTime(int minAttentionTime) {
		this.minAttentionTime = minAttentionTime;
	}

	@Override
	public Boolean getStarted() {
		return started;
	}

	@Override
	public void setStarted(Boolean started) {
		this.started = started;
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((Integer) minAttentionTime).hashCode();
		result = prime * result + ((this.started == null) ? 0 : this.started.hashCode());
		result = prime * result + ((this.createdDate == null) ? 0 : this.createdDate.hashCode());
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
		QueueEto other = (QueueEto) obj;
		if (this.minAttentionTime != other.minAttentionTime) {
			return false;
		}
		if (this.started == null) {
			if (other.started != null) {
				return false;
			}
		} else if (!this.started.equals(other.started)) {
			return false;
		}
		if (this.createdDate == null) {
			if (other.createdDate != null) {
				return false;
			}
		} else if (!this.createdDate.equals(other.createdDate)) {
			return false;
		}
		return true;
	}
}
