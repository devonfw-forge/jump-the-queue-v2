package com.devonfw.application.jtqj.ownermanagement.dataaccess.api.repo;

import static com.querydsl.core.alias.Alias.$;

import java.util.Iterator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.devonfw.application.jtqj.ownermanagement.dataaccess.api.OwnerEntity;
import com.devonfw.application.jtqj.ownermanagement.logic.api.to.OwnerSearchCriteriaTo;
import com.devonfw.module.jpa.dataaccess.api.QueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.jpa.impl.JPAQuery;

/**
 * {@link DefaultRepository} for {@link OwnerEntity}
 */
public interface OwnerRepository extends DefaultRepository<OwnerEntity> {

	/**
	 * @param criteria the {@link OwnerSearchCriteriaTo} with the criteria to
	 *                 search.
	 * @return the {@link Page} of the {@link OwnerEntity} objects that matched the
	 *         search. If no pageable is set, it will return a unique page with all
	 *         the objects that matched the search.
	 */
	default Page<OwnerEntity> findByCriteria(OwnerSearchCriteriaTo criteria) {

		OwnerEntity alias = newDslAlias();
		JPAQuery<OwnerEntity> query = newDslQuery(alias);

		String username = criteria.getUsername();
		if (username != null && !username.isEmpty()) {
			QueryUtil.get().whereString(query, $(alias.getUsername()), username, criteria.getUsernameOption());
		}
		String password = criteria.getPassword();
		if (password != null && !password.isEmpty()) {
			QueryUtil.get().whereString(query, $(alias.getPassword()), password, criteria.getPasswordOption());
		}
		Boolean userType = criteria.getUserType();
		if (userType != null) {
			query.where($(alias.getUserType()).eq(userType));
		}
		if (criteria.getPageable() == null) {
			criteria.setPageable(PageRequest.of(0, Integer.MAX_VALUE));
		} else {
			addOrderBy(query, alias, criteria.getPageable().getSort());
		}

		return QueryUtil.get().findPaginated(criteria.getPageable(), query, true);
	}

	/**
	 * Add sorting to the given query on the given alias
	 * 
	 * @param query to add sorting to
	 * @param alias to retrieve columns from for sorting
	 * @param sort  specification of sorting
	 */
	public default void addOrderBy(JPAQuery<OwnerEntity> query, OwnerEntity alias, Sort sort) {
		if (sort != null && sort.isSorted()) {
			Iterator<Order> it = sort.iterator();
			while (it.hasNext()) {
				Order next = it.next();
				switch (next.getProperty()) {
				case "username":
					if (next.isAscending()) {
						query.orderBy($(alias.getUsername()).asc());
					} else {
						query.orderBy($(alias.getUsername()).desc());
					}
					break;
				case "password":
					if (next.isAscending()) {
						query.orderBy($(alias.getPassword()).asc());
					} else {
						query.orderBy($(alias.getPassword()).desc());
					}
					break;
				case "userType":
					if (next.isAscending()) {
						query.orderBy($(alias.getUserType()).asc());
					} else {
						query.orderBy($(alias.getUserType()).desc());
					}
					break;
				default:
					throw new IllegalArgumentException("Sorted by the unknown property '" + next.getProperty() + "'");
				}
			}
		}
	}

}