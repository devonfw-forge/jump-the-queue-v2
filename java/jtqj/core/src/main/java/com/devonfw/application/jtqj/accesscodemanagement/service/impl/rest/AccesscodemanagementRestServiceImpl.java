package com.devonfw.application.jtqj.accesscodemanagement.service.impl.rest;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;

import com.devonfw.application.jtqj.accesscodemanagement.logic.api.Accesscodemanagement;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeCto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeEto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeSearchCriteriaTo;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.Uuid;
import com.devonfw.application.jtqj.accesscodemanagement.service.api.rest.AccesscodemanagementRestService;

/**
 * The service implementation for REST calls in order to execute the logic of
 * component {@link Accesscodemanagement}.
 */
@Named("AccesscodemanagementRestService")
public class AccesscodemanagementRestServiceImpl implements AccesscodemanagementRestService {

	@Inject
	private Accesscodemanagement accesscodemanagement;

	@Override
	public AccessCodeCto getAccessCodeCto(long id) {
		return this.accesscodemanagement.findAccessCodeCto(id);
	}

	@Override
	public Page<AccessCodeCto> findAccessCodeCtos(AccessCodeSearchCriteriaTo searchCriteriaTo) {
		return this.accesscodemanagement.findAccessCodeCtos(searchCriteriaTo);
	}

	@Override
	public AccessCodeEto getAccessCode(long id) {
		return this.accesscodemanagement.findAccessCode(id);
	}

	@Override
	public AccessCodeEto saveAccessCode(AccessCodeEto accesscode) {
		return this.accesscodemanagement.saveAccessCode(accesscode);
	}

	@Override
	public void deleteAccessCode(long id) {
		this.accesscodemanagement.deleteAccessCode(id);
	}

	@Override
	public Page<AccessCodeEto> findAccessCodes(AccessCodeSearchCriteriaTo searchCriteriaTo) {
		return this.accesscodemanagement.findAccessCodes(searchCriteriaTo);
	}

	@Override
	public AccessCodeCto findUuidAccessCode(Uuid uuid) {
		return this.accesscodemanagement.findUuidAccessCode(uuid);
	}

	@Override
	public AccessCodeEto callNextCode() {
		return this.accesscodemanagement.callNextCode();
	}

}
