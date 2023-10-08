package com.otaku.otakube.repository.log;

import com.otaku.otakube.entity.log.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventLogRepository extends JpaRepository<EventLog, Long> {

    @Query("select el from EventLog el where el.user.userId = :userId and el.event.eventId = :eventId")
    EventLog findEventLog(@Param("userId") Long userId, @Param("eventId") Long eventId);
}
