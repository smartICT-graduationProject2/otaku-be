package com.otaku.otakube.repository.eventlog;

import com.otaku.otakube.entity.log.EventLog;

import java.util.Optional;

public interface EventLogRepositoryCustom {
    boolean existsEventLogByEventAndUser(final Long eventId, final Long userId);

    Optional<EventLog> findValidEventLogByEventAndUser(final Long eventId, final Long userId);

    Optional<EventLog> findValidEventLogByApproval(final Long approvalId);
}
