package com.devonfw.application.jtqj.accesscodemanagement.dataaccess.api.repo;

import static com.querydsl.core.alias.Alias.$;

import java.sql.Timestamp;
import java.util.Iterator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.devonfw.application.jtqj.accesscodemanagement.dataaccess.api.AccessCodeEntity;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeSearchCriteriaTo;
import com.devonfw.application.jtqj.general.common.api.Status;
import com.devonfw.module.jpa.dataaccess.api.QueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.jpa.impl.JPAQuery;

/**
 * {@link DefaultRepository} for {@link AccessCodeEntity}
 */
public interface AccessCodeRepository extends DefaultRepository<AccessCodeEntity> {

	/**
	 * @param criteria the {@link AccessCodeSearchCriteriaTo} with the criteria to
	 *                 search.
	 * @return the {@link Page} of the {@link AccessCodeEntity} objects that matched
	 *         the search. If no pageable is set, it will return a unique page with
	 *         all the objects that matched the search.
	 */
	default Page<AccessCodeEntity> findByCriteria(AccessCodeSearchCriteriaTo criteria) {

		AccessCodeEntity alias = newDslAlias();
		JPAQuery<AccessCodeEntity> query = newDslQuery(alias);

		String code = criteria.getCode();
		if (code != null && !code.isEmpty()) {
			QueryUtil.get().whereString(query, $(alias.getCode()), code, criteria.getCodeOption());
		}
		String uuid = criteria.getUuid();
		if (uuid != null && !uuid.isEmpty()) {
			QueryUtil.get().whereString(query, $(alias.getUuid()), uuid, criteria.getUuidOption());
		}
		Timestamp createdDate = criteria.getCreatedDate();
		if (createdDate != null) {
			query.where($(alias.getCreatedDate()).eq(createdDate));
		}
		Timestamp startTime = criteria.getStartTime();
		if (startTime != null) {
			query.where($(alias.getStartTime()).eq(startTime));
		}
		Timestamp endTime = criteria.getEndTime();
		if (endTime != null) {
			query.where($(alias.getEndTime()).eq(endTime));
		}
		Status status = criteria.getStatus();
		if (status != null) {
			query.where($(alias.getStatus()).eq(status));
		}
		Long queue = criteria.getQueueId();
		if (queue != null) {
			query.where($(alias.getQueue().getId()).eq(queue));
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
	public default void addOrderBy(JPAQuery<AccessCodeEntity> query, AccessCodeEntity alias, Sort sort) {
		if (sort != null && sort.isSorted()) {
			Iterator<Order> it = sort.iterator();
			while (it.hasNext()) {
				Order next = it.next();
				switch (next.getProperty()) {
				case "code":
					if (next.isAscending()) {
						query.orderBy($(alias.getCode()).asc());
					} else {
						query.orderBy($(alias.getCode()).desc());
					}
					break;
				case "uuid":
					if (next.isAscending()) {
						query.orderBy($(alias.getUuid()).asc());
					} else {
						query.orderBy($(alias.getUuid()).desc());
					}
					break;
				case "createdDate":
					if (next.isAscending()) {
						query.orderBy($(alias.getCreatedDate()).asc());
					} else {
						query.orderBy($(alias.getCreatedDate()).desc());
					}
					break;
				case "startTime":
					if (next.isAscending()) {
						query.orderBy($(alias.getStartTime()).asc());
					} else {
						query.orderBy($(alias.getStartTime()).desc());
					}
					break;
				case "endTime":
					if (next.isAscending()) {
						query.orderBy($(alias.getEndTime()).asc());
					} else {
						query.orderBy($(alias.getEndTime()).desc());
					}
					break;
				case "status":
					if (next.isAscending()) {
						query.orderBy($(alias.getStatus()).asc());
					} else {
						query.orderBy($(alias.getStatus()).desc());
					}
					break;
				case "queue":
					if (next.isAscending()) {
						query.orderBy($(alias.getQueue().getId()).asc());
					} else {
						query.orderBy($(alias.getQueue().getId()).desc());
					}
					break;
				default:
					throw new IllegalArgumentException("Sorted by the unknown property '" + next.getProperty() + "'");
				}
			}
		}
	}

}