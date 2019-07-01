package com.devonfw.application.jtqj.ownermanagement.service.impl.rest;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;

import com.devonfw.application.jtqj.ownermanagement.logic.api.Ownermanagement;
import com.devonfw.application.jtqj.ownermanagement.logic.api.to.OwnerEto;
import com.devonfw.application.jtqj.ownermanagement.logic.api.to.OwnerSearchCriteriaTo;
import com.devonfw.application.jtqj.ownermanagement.service.api.rest.OwnermanagementRestService;

/**
 * The service implementation for REST calls in order to execute the logic of
 * component {@link Ownermanagement}.
 */
@Named("OwnermanagementRestService")
public class OwnermanagementRestServiceImpl implements OwnermanagementRestService {

	@Inject
	private Ownermanagement ownermanagement;

	@Override
	public OwnerEto getOwner(long id) {
		return this.ownermanagement.findOwner(id);
	}

	@Override
	public OwnerEto saveOwner(OwnerEto owner) {
		return this.ownermanagement.saveOwner(owner);
	}

	@Override
	public void deleteOwner(long id) {
		this.ownermanagement.deleteOwner(id);
	}

	@Override
	public Page<OwnerEto> findOwners(OwnerSearchCriteriaTo searchCriteriaTo) {
		return this.ownermanagement.findOwners(searchCriteriaTo);
	}
}