package com.devonfw.application.jtqj.ownermanagement.logic.impl.usecase;

import java.util.Objects;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.application.jtqj.ownermanagement.dataaccess.api.OwnerEntity;
import com.devonfw.application.jtqj.ownermanagement.logic.api.to.OwnerEto;
import com.devonfw.application.jtqj.ownermanagement.logic.api.usecase.UcManageOwner;
import com.devonfw.application.jtqj.ownermanagement.logic.base.usecase.AbstractOwnerUc;

/**
 * Use case implementation for modifying and deleting Owners
 */
@Named
@Validated
@Transactional
public class UcManageOwnerImpl extends AbstractOwnerUc implements UcManageOwner {

	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(UcManageOwnerImpl.class);

	@Override
	public boolean deleteOwner(long ownerId) {

		OwnerEntity owner = getOwnerRepository().find(ownerId);
		getOwnerRepository().delete(owner);
		LOG.debug("The owner with id '{}' has been deleted.", ownerId);
		return true;
	}

	@Override
	public OwnerEto saveOwner(OwnerEto owner) {

		Objects.requireNonNull(owner, "owner");

		OwnerEntity ownerEntity = getBeanMapper().map(owner, OwnerEntity.class);

		// initialize, validate ownerEntity here if necessary
		OwnerEntity resultEntity = getOwnerRepository().save(ownerEntity);
		LOG.debug("Owner with id '{}' has been created.", resultEntity.getId());
		return getBeanMapper().map(resultEntity, OwnerEto.class);
	}
}
