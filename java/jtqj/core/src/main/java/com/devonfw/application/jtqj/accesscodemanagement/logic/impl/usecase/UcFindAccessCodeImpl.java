package com.devonfw.application.jtqj.accesscodemanagement.logic.impl.usecase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.application.jtqj.accesscodemanagement.dataaccess.api.AccessCodeEntity;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeCto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeEto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeSearchCriteriaTo;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.usecase.UcFindAccessCode;
import com.devonfw.application.jtqj.accesscodemanagement.logic.base.usecase.AbstractAccessCodeUc;
import com.devonfw.application.jtqj.queuemanagement.logic.api.to.QueueEto;

/**
 * Use case implementation for searching, filtering and getting AccessCodes
 */
@Named
@Validated
@Transactional
public class UcFindAccessCodeImpl extends AbstractAccessCodeUc implements UcFindAccessCode {

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

	@Override
	public AccessCodeEto findUuidAccessCode(String uuid) {
		// Check if there is a code with such uuid
		// 1. get today's queue
		LOG.debug("Get AccessCodeCto with uuid {} from database.", uuid);
		AccessCodeSearchCriteriaTo criteria = new AccessCodeSearchCriteriaTo();
		criteria.setUuid(uuid);
		// AccessCodeEntity entity = getAccessCodeRepository().findByCriteria(criteria);
		// 1.1.2 create code
		// 1.2 we have Queue
		// 1.2.1 do we have code with such uuid and queue?
		// 1.2.1.1 we don't have code
		// 1.2.1.2 there is a code return it
		return new AccessCodeEto();
	}
}
