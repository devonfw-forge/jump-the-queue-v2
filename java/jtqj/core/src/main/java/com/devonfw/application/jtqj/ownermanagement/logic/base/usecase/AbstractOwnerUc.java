package com.devonfw.application.jtqj.ownermanagement.logic.base.usecase;

import javax.inject.Inject;

import com.devonfw.application.jtqj.general.logic.base.AbstractUc;
import com.devonfw.application.jtqj.ownermanagement.dataaccess.api.repo.OwnerRepository;

/**
 * Abstract use case for Owners, which provides access to the commonly necessary
 * data access objects.
 */
public class AbstractOwnerUc extends AbstractUc {

	/** @see #getOwnerRepository() */
	@Inject
	private OwnerRepository ownerRepository;

	/**
	 * Returns the field 'ownerRepository'.
	 * 
	 * @return the {@link OwnerRepository} instance.
	 */
	public OwnerRepository getOwnerRepository() {

		return this.ownerRepository;
	}

}
