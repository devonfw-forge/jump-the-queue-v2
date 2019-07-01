package com.devonfw.application.jtqj.ownermanagement.logic.impl.usecase;

import java.util.Optional;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.application.jtqj.ownermanagement.dataaccess.api.OwnerEntity;
import com.devonfw.application.jtqj.ownermanagement.logic.api.to.OwnerEto;
import com.devonfw.application.jtqj.ownermanagement.logic.api.to.OwnerSearchCriteriaTo;
import com.devonfw.application.jtqj.ownermanagement.logic.api.usecase.UcFindOwner;
import com.devonfw.application.jtqj.ownermanagement.logic.base.usecase.AbstractOwnerUc;

/**
 * Use case implementation for searching, filtering and getting Owners
 */
@Named
@Validated
@Transactional
public class UcFindOwnerImpl extends AbstractOwnerUc implements UcFindOwner {

	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(UcFindOwnerImpl.class);

	@Override
	public OwnerEto findOwner(long id) {
		LOG.debug("Get Owner with id {} from database.", id);
		Optional<OwnerEntity> foundEntity = getOwnerRepository().findById(id);
		if (foundEntity.isPresent())
			return getBeanMapper().map(foundEntity.get(), OwnerEto.class);
		else
			return null;
	}

	@Override
	public Page<OwnerEto> findOwners(OwnerSearchCriteriaTo criteria) {
		Page<OwnerEntity> owners = getOwnerRepository().findByCriteria(criteria);
		return mapPaginatedEntityList(owners, OwnerEto.class);
	}

}
