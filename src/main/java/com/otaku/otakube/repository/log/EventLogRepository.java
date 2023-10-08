package com.otaku.otakube.repository.log;

import com.otaku.otakube.entity.log.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepository extends JpaRepository<EventLog, Long> {
}
