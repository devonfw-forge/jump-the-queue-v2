package com.devonfw.application.jtqj.accesscodemanagement.logic.impl.usecase;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeCto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeEto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeSearchCriteriaTo;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.usecase.UcFindAccessCode;
import com.devonfw.application.jtqj.accesscodemanagement.logic.base.usecase.AbstractAccessCodeUc;
import com.devonfw.application.jtqj.general.common.api.Status;
import com.devonfw.application.jtqj.queuemanagement.logic.api.Queuemanagement;
import com.devonfw.application.jtqj.queuemanagement.logic.api.to.QueueEto;

/**
 * Use case implementation for searching, filtering and getting AccessCodes
 */
@Named
@Validated
@Transactional
public class UcFindAccessCodeImpl extends AbstractAccessCodeUc implements UcFindAccessCode {

	private static final String QUEUE_LETTER_CODE = "Q";
	private static final String FIRST_CODE_IN_QUEUE = "001";
	private static final String LAST_CODE_IN_QUEUE = "999";
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(UcFindAccessCodeImpl.class);

	@Override
	public AccessCodeCto findAccessCodeCto(long id) {
		LOG.debug("Get AccessCodeCto with id {} from database.", id);
		AccessCodeEntity entity = getAccessCodeRepository().find(id);
		AccessCodeCto cto = new AccessCodeCto();
		cto.setAccessCode(getBeanMapper().map(entity, AccessCodeEto.class));
		cto.setQueue(getBeanMapper().map(entity.getQueue(), QueueEto.class));
		return cto;
	}

	@Override
	public Page<AccessCodeCto> findAccessCodeCtos(AccessCodeSearchCriteriaTo criteria) {
		Page<AccessCodeEntity> accesscodes = getAccessCodeRepository().findByCriteria(criteria);
		List<AccessCodeCto> ctos = new ArrayList<>();
		for (AccessCodeEntity entity : accesscodes.getContent()) {
			AccessCodeCto cto = new AccessCodeCto();
			cto.setAccessCode(getBeanMapper().map(entity, AccessCodeEto.class));
			cto.setQueue(getBeanMapper().map(entity.getQueue(), QueueEto.class));
			ctos.add(cto);
		}
		Pageable pagResultTo = PageRequest.of(criteria.getPageable().getPageNumber(),
				criteria.getPageable().getPageSize());

		return new PageImpl<>(ctos, pagResultTo, accesscodes.getTotalElements());
	}

	@Inject
	Queuemanagement queueManagement;
	@Override
	public AccessCodeCto findUuidAccessCode(String uuid) {
		AccessCodeSearchCriteriaTo criteria = new AccessCodeSearchCriteriaTo();
		AccessCodeCto visitorCode = new AccessCodeCto();
		// Get today's queue
		QueueEto dailyQueue = queueManagement.findDailyQueue();
		// Get code associated with uuid and today's queue
		criteria.setUuid(uuid);
		criteria.setQueueId(dailyQueue.getId());
		Page<AccessCodeEntity> codes = getAccessCodeRepository().findByCriteria(criteria);
		// create code if user hasn't for today
		if (codes.getContent().isEmpty()) {
			AccessCodeEto newCode = new AccessCodeEto();
			newCode.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			newCode.setUuid(uuid);
			newCode.setQueueId(dailyQueue.getId());
			if (dailyQueue.getStarted()) {
				newCode.setStatus(Status.WAITING);
			} else {
				newCode.setStatus(Status.NOTSTARTED);
			}
			visitorCode.setQueue(dailyQueue);
			AccessCodeEto lastCodeInQueue = getLastCodeInQueue(dailyQueue.getId());
			// is queue empty?
			if(lastCodeInQueue.getCreatedDate() == null) {
				newCode.setCode(QUEUE_LETTER_CODE + FIRST_CODE_IN_QUEUE);
			} else {
				newCode.setCode(nextCodeString(lastCodeInQueue.getCode()));
			}
			AccessCodeEntity savedCode = getAccessCodeRepository().save(getBeanMapper().map(newCode, AccessCodeEntity.class));
			visitorCode.setAccessCode(getBeanMapper().map(savedCode, AccessCodeEto.class));
		} else {
			visitorCode.setAccessCode(getBeanMapper().map(codes.getContent().get(0), AccessCodeEto.class));
			visitorCode.setQueue(dailyQueue);
		}
		return visitorCode;
	}

	// Given a code, gives next
	// Example: Input: Q009 Output: Q010
	private String nextCodeString(String codeString) {
		String nextCode = QUEUE_LETTER_CODE + FIRST_CODE_IN_QUEUE;
		if (!codeString.equals(QUEUE_LETTER_CODE + LAST_CODE_IN_QUEUE)) {
			String numbers = codeString.substring(1);
			int number = Integer.parseInt(numbers);
			number = number + 1;
			numbers = String.valueOf(number);
			while (numbers.length() < 3) {
				numbers = "0" + numbers;
			}
			nextCode = QUEUE_LETTER_CODE + numbers;
		}
		return nextCode;
	}

	private AccessCodeEto getLastCodeInQueue(long queueId) {
		AccessCodeEto lastCode = new AccessCodeEto();
		AccessCodeSearchCriteriaTo criteria = new AccessCodeSearchCriteriaTo();
		criteria.setQueueId(queueId);
		criteria.setPageable(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC,"createdDate")));
		Page<AccessCodeEntity> accessCodes = getAccessCodeRepository().findByCriteria(criteria);
		if (accessCodes.getContent().size() == 1) {
			lastCode = getBeanMapper().map(accessCodes.getContent().get(0), AccessCodeEto.class);
		}
		return lastCode;
	}
}
