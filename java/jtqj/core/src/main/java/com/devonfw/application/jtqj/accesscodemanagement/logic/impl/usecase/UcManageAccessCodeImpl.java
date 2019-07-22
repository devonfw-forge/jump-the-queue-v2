package com.devonfw.application.jtqj.accesscodemanagement.logic.impl.usecase;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.application.jtqj.accesscodemanagement.dataaccess.api.AccessCodeEntity;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.Accesscodemanagement;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeCto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeEto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeSearchCriteriaTo;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.EstimatedTime;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.NextCodeCto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.usecase.UcManageAccessCode;
import com.devonfw.application.jtqj.accesscodemanagement.logic.base.usecase.AbstractAccessCodeUc;
import com.devonfw.application.jtqj.general.common.api.Status;
import com.devonfw.application.jtqj.queuemanagement.logic.api.Queuemanagement;
import com.devonfw.application.jtqj.queuemanagement.logic.api.to.QueueEto;

/**
 * Use case implementation for modifying and deleting AccessCodes
 */
@Named
@Validated
@Transactional
public class UcManageAccessCodeImpl extends AbstractAccessCodeUc implements UcManageAccessCode {

	private static final int DEFAULT_ESTIMATED_TIME_PER_USER_IN_MILISECONDS = 120000;
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(UcManageAccessCodeImpl.class);

	@Override
	public boolean deleteAccessCode(long accessCodeId) {

		AccessCodeEntity accessCode = getAccessCodeRepository().find(accessCodeId);
		getAccessCodeRepository().delete(accessCode);
		LOG.debug("The accessCode with id '{}' has been deleted.", accessCodeId);
		return true;
	}

	@Override
	public AccessCodeEto saveAccessCode(AccessCodeEto accessCode) {

		Objects.requireNonNull(accessCode, "accessCode");

		AccessCodeEntity accessCodeEntity = getBeanMapper().map(accessCode, AccessCodeEntity.class);

		if (accessCodeEntity.getCreatedDate() == null) {
			accessCodeEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		}
		// Initialize, validate accessCodeEntity here if necessary
		AccessCodeEntity resultEntity = getAccessCodeRepository().save(accessCodeEntity);
		LOG.debug("AccessCode with id '{}' has been created.", resultEntity.getId());
		return getBeanMapper().map(resultEntity, AccessCodeEto.class);
	}

	@Inject
	Accesscodemanagement accessCodeManagement;

	@Override
	public void updateCodesOnStartQueue(long queueId) {
		List<AccessCodeEto> accessCodes = accessCodeManagement.findByQueue(queueId);
		for (AccessCodeEto eto : accessCodes) {
			AccessCodeEntity entity = getBeanMapper().map(eto, AccessCodeEntity.class);
			entity.setStatus(Status.WAITING);
			getAccessCodeRepository().save(entity);
		}
	}

	@Inject
	Queuemanagement queueManagement;

	@Override
	public NextCodeCto callNextCode() {
		// Get daily queues so we get codes associated with it
		long dailyQueueId = queueManagement.findDailyQueue().getId();

		// Check if we have current code being attended
		AccessCodeEto currentCode = accessCodeManagement.findCurrentCode(dailyQueueId);
		if (currentCode.getStatus() == Status.ATTENDING) {
			// Update current code
			currentCode.setStatus(Status.ATTENDED);
			currentCode.setEndTime(new Timestamp(System.currentTimeMillis()));
			getAccessCodeRepository().save(getBeanMapper().map(currentCode, AccessCodeEntity.class));
		}
		// Check if there is a next code else go 2.2
		NextCodeCto nextCodeCto = accessCodeManagement.findNextCode(dailyQueueId);
		if (nextCodeCto.getAccessCode() != null && nextCodeCto.getAccessCode().getStatus() == Status.WAITING) {
			// Update this code to attending and startDate and return it
			nextCodeCto.getAccessCode().setStatus(Status.ATTENDING);
			nextCodeCto.getAccessCode().setStartTime(new Timestamp(System.currentTimeMillis()));
			getAccessCodeRepository().save(getBeanMapper().map(nextCodeCto.getAccessCode(), AccessCodeEntity.class));
			// Remove above code from remaining codes
			nextCodeCto.getRemainingCodes().setRemainingCodes(nextCodeCto.getRemainingCodes().getRemainingCodes() - 1);
		}
		return nextCodeCto;
	}

	@Override
	public EstimatedTime calculateEstimatedTime(AccessCodeEto accessCode) {
		EstimatedTime estimated = new EstimatedTime();
		// Get how many codes are ahead of given code
		AccessCodeSearchCriteriaTo criteria = new AccessCodeSearchCriteriaTo();
		criteria.setQueueId(accessCode.getQueueId());
		criteria.setStatus(Status.WAITING);
		criteria.setPageable(PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.ASC, "createdDate")));
		Page<AccessCodeEntity> result = getAccessCodeRepository().findByCriteria(criteria);

		// TODO: Refactor this
		for (AccessCodeEntity entity: result.getContent()) {
			if (entity.getId().equals(accessCode.getId())) {
				int index = result.getContent().indexOf(entity);
				index = index + 1; // + attending code
				long estimatedInMs = index * DEFAULT_ESTIMATED_TIME_PER_USER_IN_MILISECONDS;
				estimated.setMiliseconds(estimatedInMs);
				estimated.setDefaultTimeByUserInMs(DEFAULT_ESTIMATED_TIME_PER_USER_IN_MILISECONDS);
				return estimated;
			}
		}
		return estimated;
	}
}
