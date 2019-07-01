package com.devonfw.application.jtqj.ownermanagement.logic.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;

import com.devonfw.application.jtqj.general.logic.base.AbstractComponentFacade;
import com.devonfw.application.jtqj.ownermanagement.logic.api.Ownermanagement;
import com.devonfw.application.jtqj.ownermanagement.logic.api.to.OwnerEto;
import com.devonfw.application.jtqj.ownermanagement.logic.api.to.OwnerSearchCriteriaTo;
import com.devonfw.application.jtqj.ownermanagement.logic.api.usecase.UcFindOwner;
import com.devonfw.application.jtqj.ownermanagement.logic.api.usecase.UcManageOwner;

/**
 * Implementation of component interface of ownermanagement
 */
@Named
public class OwnermanagementImpl extends AbstractComponentFacade implements Ownermanagement {

	@Inject
	private UcFindOwner ucFindOwner;

	@Inject
	private UcManageOwner ucManageOwner;

	@Override
	public OwnerEto findOwner(long id) {

		return this.ucFindOwner.findOwner(id);
	}

	@Override
	public Page<OwnerEto> findOwners(OwnerSearchCriteriaTo criteria) {
		return this.ucFindOwner.findOwners(criteria);
	}

	@Override
	public OwnerEto saveOwner(OwnerEto owner) {

		return this.ucManageOwner.saveOwner(owner);
	}

	@Override
	public boolean deleteOwner(long id) {

		return this.ucManageOwner.deleteOwner(id);
	}
}
