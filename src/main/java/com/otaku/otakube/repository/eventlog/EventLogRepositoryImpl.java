package com.otaku.otakube.repository.eventlog;

import com.otaku.otakube.entity.log.EventLog;
import com.otaku.otakube.entity.log.EventLogStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.otaku.otakube.entity.log.QEventLog.eventLog;

@AllArgsConstructor
@Repository
public class EventLogRepositoryImpl implements EventLogRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsEventLogByEventAndUser(Long eventId, Long userId) {
        return queryFactory
                .selectFrom(eventLog)
                .where(
                        eventLog.event.eventId.eq(eventId),
                        eventLog.user.userId.eq(userId)
                )
                .fetchFirst() != null;
    }

    @Override
    public Optional<EventLog> findValidEventLogByEventAndUser(Long eventId, Long userId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(eventLog)
                .where(
                        eventLog.event.eventId.eq(eventId),
                        eventLog.user.userId.eq(userId),
                        eventLog.status.eq(EventLogStatus.EXPECTED)
                )
                .fetchFirst());
    }
}
