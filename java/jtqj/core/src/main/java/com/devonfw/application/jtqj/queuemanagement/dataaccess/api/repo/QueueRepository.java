package com.devonfw.application.jtqj.queuemanagement.dataaccess.api.repo;

import static com.querydsl.core.alias.Alias.$;

import java.sql.Timestamp;
import java.util.Iterator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.devonfw.application.jtqj.queuemanagement.dataaccess.api.QueueEntity;
import com.devonfw.application.jtqj.queuemanagement.logic.api.to.QueueSearchCriteriaTo;
import com.devonfw.module.jpa.dataaccess.api.QueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.jpa.impl.JPAQuery;

/**
 * {@link DefaultRepository} for {@link QueueEntity}
 */
public interface QueueRepository extends DefaultRepository<QueueEntity> {

	/**
	 * @param criteria the {@link QueueSearchCriteriaTo} with the criteria to
	 *                 search.
	 * @return the {@link Page} of the {@link QueueEntity} objects that matched the
	 *         search. If no pageable is set, it will return a unique page with all
	 *         the objects that matched the search.
	 */
	default Page<QueueEntity> findByCriteria(QueueSearchCriteriaTo criteria) {

		QueueEntity alias = newDslAlias();
		JPAQuery<QueueEntity> query = newDslQuery(alias);

		Integer minAttentionTime = criteria.getMinAttentionTime();
		if (minAttentionTime != null) {
			query.where($(alias.getMinAttentionTime()).eq(minAttentionTime));
		}
		Boolean started = criteria.getStarted();
		if (started != null) {
			query.where($(alias.getStarted()).eq(started));
		}
		Timestamp createdDate = criteria.getCreatedDate();
		if (createdDate != null) {
			query.where($(alias.getCreatedDate()).eq(createdDate));
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
	public default void addOrderBy(JPAQuery<QueueEntity> query, QueueEntity alias, Sort sort) {
		if (sort != null && sort.isSorted()) {
			Iterator<Order> it = sort.iterator();
			while (it.hasNext()) {
				Order next = it.next();
				switch (next.getProperty()) {
				case "minAttentionTime":
					if (next.isAscending()) {
						query.orderBy($(alias.getMinAttentionTime()).asc());
					} else {
						query.orderBy($(alias.getMinAttentionTime()).desc());
					}
					break;
				case "started":
					if (next.isAscending()) {
						query.orderBy($(alias.getStarted()).asc());
					} else {
						query.orderBy($(alias.getStarted()).desc());
					}
					break;
				case "createdDate":
					if (next.isAscending()) {
						query.orderBy($(alias.getCreatedDate()).asc());
					} else {
						query.orderBy($(alias.getCreatedDate()).desc());
					}
					break;
				default:
					throw new IllegalArgumentException("Sorted by the unknown property '" + next.getProperty() + "'");
				}
			}
		}
	}

}