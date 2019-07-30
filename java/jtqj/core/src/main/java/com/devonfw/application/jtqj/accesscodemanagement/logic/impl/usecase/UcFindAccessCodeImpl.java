package com.devonfw.application.jtqj.accesscodemanagement.logic.impl.usecase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.devonfw.application.jtqj.accesscodemanagement.dataaccess.api.AccessCodeEntity;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.Accesscodemanagement;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeCto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeEto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeSearchCriteriaTo;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.NextCodeCto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.RemainingCodes;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.Uuid;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.usecase.UcFindAccessCode;
import com.devonfw.application.jtqj.accesscodemanagement.logic.base.usecase.AbstractAccessCodeUc;
import com.devonfw.application.jtqj.accesscodemanagement.service.impl.rest.ServerSse;
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

	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(UcFindAccessCodeImpl.class);

	@Inject
	Queuemanagement queueManagement;

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
	Accesscodemanagement accessCodeManagement;

	@Override
	public AccessCodeCto findUuidAccessCode(Uuid uuid) {
		AccessCodeSearchCriteriaTo criteria = new AccessCodeSearchCriteriaTo();
		AccessCodeCto visitorCode = new AccessCodeCto();
		// Get today's queue
		QueueEto dailyQueue = queueManagement.findDailyQueue();
		visitorCode.setQueue(dailyQueue);
		// Get code associated with uuid and today's queue

		criteria.setQueueId(dailyQueue.getId());
		criteria.setUuid(uuid.getUuid());
		Page<AccessCodeEntity> codes = getAccessCodeRepository().findByCriteria(criteria);
		// create code if user hasn't for today
		if (codes.getContent().isEmpty()) {
			AccessCodeEto newCode = new AccessCodeEto();
			newCode.setUuid(uuid.getUuid());
			newCode.setQueueId(dailyQueue.getId());
			if (dailyQueue.getStarted()) {
				newCode.setStatus(Status.WAITING);
			} else {
				newCode.setStatus(Status.NOTSTARTED);
			}
			AccessCodeEto lastCodeInQueue = getLastCodeInQueue(dailyQueue.getId());
			// is queue empty?
			if (lastCodeInQueue.getCreatedDate() == null) {
				newCode.setCode(QUEUE_LETTER_CODE + FIRST_CODE_IN_QUEUE);
			} else {
				newCode.setCode(nextCodeString(lastCodeInQueue.getCode()));
			}
			AccessCodeEto savedCode = accessCodeManagement.saveAccessCode(newCode);
			visitorCode.setAccessCode(savedCode);

			// SSE that a new code is in the queue
	        List<SseEmitter> sseEmitterListToRemove = new ArrayList<>();
	        ServerSse.emitters.forEach((SseEmitter emitter) -> {
	            try {
	            	emitter.send(SseEmitter.event().data(savedCode, MediaType.APPLICATION_JSON).name("NEW_CODE_ADDED"));
	            } catch (IOException e) {
	                emitter.complete();
	                sseEmitterListToRemove.add(emitter);
	                LOG.error(e.toString());
	            }
	        });
	        ServerSse.emitters.removeAll(sseEmitterListToRemove);
		} else {
			visitorCode.setAccessCode(getBeanMapper().map(codes.getContent().get(0), AccessCodeEto.class));
			visitorCode.setQueue(dailyQueue);
		}
		return visitorCode;
	}

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
		criteria.setPageable(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "createdDate")));
		Page<AccessCodeEntity> accessCodes = getAccessCodeRepository().findByCriteria(criteria);
		if (accessCodes.getContent().size() == 1) {
			lastCode = getBeanMapper().map(accessCodes.getContent().get(0), AccessCodeEto.class);
		}
		return lastCode;
	}

	@Override
	public AccessCodeEto findAccessCode(long id) {
		LOG.debug("Get AccessCode with id {} from database.", id);
		Optional<AccessCodeEntity> foundEntity = getAccessCodeRepository().findById(id);
		if (foundEntity.isPresent())
			return getBeanMapper().map(foundEntity.get(), AccessCodeEto.class);
		else
			return null;
	}

	@Override
	public Page<AccessCodeEto> findAccessCodes(AccessCodeSearchCriteriaTo criteria) {
		Page<AccessCodeEntity> accesscodes = getAccessCodeRepository().findByCriteria(criteria);
		return mapPaginatedEntityList(accesscodes, AccessCodeEto.class);
	}

	@Override
	public List<AccessCodeEto> findByQueue(long queueId) {
		List<AccessCodeEto> etos = new ArrayList<>();
		AccessCodeSearchCriteriaTo criteria = new AccessCodeSearchCriteriaTo();
		criteria.setQueueId(queueId);
		Page<AccessCodeEntity> accessCodes = getAccessCodeRepository().findByCriteria(criteria);
		for (AccessCodeEntity entity : accessCodes.getContent()) {
			etos.add(getBeanMapper().map(entity, AccessCodeEto.class));
		}
		return etos;
	}

	@Override
	public AccessCodeEto findCurrentCode() {
		QueueEto dailyQueue = queueManagement.findDailyQueue();
		AccessCodeEto currentCode = new AccessCodeEto();
		AccessCodeSearchCriteriaTo criteria = new AccessCodeSearchCriteriaTo();
		criteria.setQueueId(dailyQueue.getId());
		criteria.setStatus(Status.ATTENDING);
		Page<AccessCodeEntity> accessCode = getAccessCodeRepository().findByCriteria(criteria);
		// Check if we have a current code
		if (accessCode.getContent().size() == 1) {
			currentCode = getBeanMapper().map(accessCode.getContent().get(0), AccessCodeEto.class);
		}
		return currentCode;
	}

	@Override
	public NextCodeCto findNextCode(long queueId) {
		NextCodeCto nextCodeCto = new NextCodeCto();
		RemainingCodes remainingCodes = new RemainingCodes();
		AccessCodeSearchCriteriaTo criteria = new AccessCodeSearchCriteriaTo();
		criteria.setQueueId(queueId);
		criteria.setStatus(Status.WAITING);
		criteria.setPageable(PageRequest.of(0,Integer.MAX_VALUE, Sort.by(Sort.Direction.ASC, "createdDate")));
		Page<AccessCodeEntity> accessCode = getAccessCodeRepository().findByCriteria(criteria);
		remainingCodes.setRemainingCodes(accessCode.getContent().size());
		nextCodeCto.setRemainingCodes(remainingCodes);
		if (!accessCode.getContent().isEmpty()) {
			nextCodeCto.setAccessCode(getBeanMapper().map(accessCode.getContent().get(0), AccessCodeEto.class));
		}
		return nextCodeCto;
	}

	@Override
	public RemainingCodes findRemainingCodes() {
		RemainingCodes remaining = new RemainingCodes();
		QueueEto dailyQueue = queueManagement.findDailyQueue();
		AccessCodeSearchCriteriaTo criteria = new AccessCodeSearchCriteriaTo();
		criteria.setQueueId(dailyQueue.getId());
		criteria.setStatus(Status.WAITING);
		Page<AccessCodeEntity> accessCodes = getAccessCodeRepository().findByCriteria(criteria);
		remaining.setRemainingCodes(accessCodes.getContent().size());
		return remaining;
	}
}
