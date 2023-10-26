package com.otaku.otakube.repository.eventlog;

public interface EventLogRepositoryCustom {
    boolean existsEventLogByEventAndUser(final Long eventId, final Long userId);
}
