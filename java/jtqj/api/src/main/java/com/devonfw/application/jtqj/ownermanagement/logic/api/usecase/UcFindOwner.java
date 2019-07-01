package com.devonfw.application.jtqj.ownermanagement.logic.api.usecase;

import java.util.List;

import org.springframework.data.domain.Page;

import com.devonfw.application.jtqj.ownermanagement.logic.api.to.OwnerEto;
import com.devonfw.application.jtqj.ownermanagement.logic.api.to.OwnerSearchCriteriaTo;

public interface UcFindOwner {

	/**
	 * Returns a Owner by its id 'id'.
	 *
	 * @param id The id 'id' of the Owner.
	 * @return The {@link OwnerEto} with id 'id'
	 */
	OwnerEto findOwner(long id);

	/**
	 * Returns a paginated list of Owners matching the search criteria.
	 *
	 * @param criteria the {@link OwnerSearchCriteriaTo}.
	 * @return the {@link List} of matching {@link OwnerEto}s.
	 */
	Page<OwnerEto> findOwners(OwnerSearchCriteriaTo criteria);

}
