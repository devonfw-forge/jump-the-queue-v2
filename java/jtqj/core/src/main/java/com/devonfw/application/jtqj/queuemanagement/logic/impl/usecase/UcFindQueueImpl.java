package com.devonfw.application.jtqj.queuemanagement.logic.impl.usecase;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.application.jtqj.queuemanagement.dataaccess.api.QueueEntity;
import com.devonfw.application.jtqj.queuemanagement.logic.api.Queuemanagement;
import com.devonfw.application.jtqj.queuemanagement.logic.api.to.QueueEto;
import com.devonfw.application.jtqj.queuemanagement.logic.api.to.QueueSearchCriteriaTo;
import com.devonfw.application.jtqj.queuemanagement.logic.api.usecase.UcFindQueue;
import com.devonfw.application.jtqj.queuemanagement.logic.base.usecase.AbstractQueueUc;

/**
 * Use case implementation for searching, filtering and getting Queues
 */
@Named
@Validated
@Transactional
public class UcFindQueueImpl extends AbstractQueueUc implements UcFindQueue {

	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(UcFindQueueImpl.class);

	@Override
	public QueueEto findQueue(long id) {
		LOG.debug("Get Queue with id {} from database.", id);
		Optional<QueueEntity> foundEntity = getQueueRepository().findById(id);
		if (foundEntity.isPresent())
			return getBeanMapper().map(foundEntity.get(), QueueEto.class);
		else
			return null;
	}

	@Override
	public Page<QueueEto> findQueues(QueueSearchCriteriaTo criteria) {
		Page<QueueEntity> queues = getQueueRepository().findByCriteria(criteria);
		return mapPaginatedEntityList(queues, QueueEto.class);
	}

	@Inject
	Queuemanagement queuemanagement;
	@Override
	public QueueEto findDailyQueue() {
		// Return today's queue (create if not exists)
		Boolean todayQueue = false;
		QueueSearchCriteriaTo criteria = new QueueSearchCriteriaTo();
		criteria.setPageable(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC,"createdDate")));
		Page<QueueEntity> queues = getQueueRepository().findByCriteria(criteria);
		QueueEto respQueue = new QueueEto();

		if (!queues.getContent().isEmpty()) {
			// Check if we have today's queue
			QueueEto lastQueue =  getBeanMapper().map(queues.getContent().get(0), QueueEto.class);
			todayQueue = queueToday(lastQueue.getCreatedDate());
			respQueue = lastQueue;
		}
		if(!todayQueue) {
			// Create a queue for today
			QueueEto newQueue = new QueueEto();
			QueueEto savedQueue = queuemanagement.saveQueue(newQueue);
			respQueue = savedQueue;
		}
		return respQueue;
	}

	private Boolean queueToday(Timestamp queueTimestamp) {
		Boolean haveQueue = false;
		LocalDate queueTimeParsed = queueTimestamp.toLocalDateTime().toLocalDate();
		LocalDate today = LocalDate.now();
		haveQueue = queueTimeParsed.getYear() == today.getYear() && queueTimeParsed.getDayOfYear() == today.getDayOfYear();
		return haveQueue;
	}
}
